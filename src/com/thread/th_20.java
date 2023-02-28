package com.thread;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// Fork/Join 线程池，能把大任务拆成多个小任务并行执行
public class th_20 {
    public static void main(String[] args) {
        //举例：大数组求和
        // 1.单线程写法
        long[] arr = new long[200000];
        long exceptedSum = 0;
        long startTimeSingle = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            arr[i] = random();
            exceptedSum += arr[i];
        }
        long endTimeSingle = System.currentTimeMillis();
        System.out.println("single sum: " + exceptedSum + " in " + (endTimeSingle - startTimeSingle) + " ms");


        // 2.ForkJoin 写法
        ForkJoinTask<Long> task = new SumTask(arr, 0, arr.length);
        long startTime = System.currentTimeMillis();

        long res = ForkJoinPool.commonPool().invoke(task);

        long endTime = System.currentTimeMillis();
        System.out.println("fork sum: " + res + " in " + (endTime - startTime) + " ms");
    }

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(10000);
    }
}

class SumTask extends RecursiveTask<Long> {
    static final int THRESHOLD = 500;
    long[] array;
    int start;
    int end;

    SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 实际是一个递归算法
        // 分类讨论，数据量小直接单线程；数据量大开始拆分
        if (end - start < THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += this.array[i];
                // 故意放慢计算速度:
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            }
            return sum;
        }

        // 拆分任务
        int mid = (end + start) / 2;
        System.out.printf("split %d ~ %d ==> %d ~ %d , %d ~ %d%n", start, end, start, mid, mid, end);
        SumTask sub_1 = new SumTask(this.array, start, mid);
        SumTask sub_2 = new SumTask(this.array, mid, end);

        invokeAll(sub_1, sub_2); // 递归执行两个子任务

        Long res_1 = sub_1.join();
        Long res_2 = sub_2.join();
        Long res = res_1 + res_2;

        System.out.println("result = " + res_1 + " + " + res_2 + " ==> " + res);
        return res;
    }
}
