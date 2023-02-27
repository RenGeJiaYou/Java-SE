package com.thread;

import java.util.concurrent.*;

// Futher 表示一个未来可能会返回的结果
// 新建线程时，采用实现 Runnable 接口的 void run() 方法的问题在于没有返回值
//              实现 Callable 泛型接口的 call() 可解决此问题
// 有了返回值，就能用 Futher 接收
public class th_18 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Tasks t = new Tasks();

        // 提交任务（给线程池）并获得Future:
        Future<String> future = executor.submit(t);

        // 异步操作：获得返回值
        System.out.println(future.get());   // 可能阻塞

        executor.shutdown();    // 不加这一句，线程池无法退出。
    }
}

class Tasks implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "实现 Callable 的 call()";
    }
}
