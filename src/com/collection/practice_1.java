package com.collection;

import java.util.ArrayList;
import java.util.List;

public class practice_1 {
    public static void main(String[] args) {
        // 构造从start到end的序列：
        final int start = 10;
        final int end = 20;
        List<Integer> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i);
        }
        // 随机删除List中的一个元素:
        int removed = list.remove((int) (Math.random() * list.size()));
        int found = findMissingNumber(start, end, list);
        System.out.println(list.toString());
        System.out.println("missing number: " + found);
        System.out.println(removed == found ? "测试成功" : "测试失败");
    }

    // 考虑到 contains() 内部实现也用到了一层循环,本算法的时间复杂度为O(n^2)。
    // 可以考虑双指针法
    static int findMissingNumber(int s, int e, List<Integer> list) {
        Integer found = null;
        for (Integer i = s; i <= e; i++) {
            if (!list.contains(i)){
                found = i;
                return found;   // 一旦发现，立即终止
            }
        }
        return found;
    }
}
