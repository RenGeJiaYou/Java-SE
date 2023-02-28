package com.thread;

import java.util.concurrent.CompletableFuture;

// Future 是同步的。缺点是用阻塞方法 get() 请求若干时间后可能返回的结果时，主线程也得等待
// CompletableFuture 改进为异步（联想到 JS 的 promise）。针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
public class th_19 {
    public static void main(String[] args) throws InterruptedException {
//        // 一、异步地执行静态方法 fetchPrice()
//        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(th_19::fetchPrice); // supplyAsync() 要求传入一个实现了 Supplier 接口的函数
//
//        // 1.执行成功，接收函数的返回值
//        cf.thenAccept((result) -> {
//            System.out.println("price: " + result);
//        });
//
//        // 2.执行失败，接收函数的抛出异常
//        cf.exceptionally((err) -> {
//            err.printStackTrace();
//            return null;
//        });
//
//        // 主线程不要立刻结束，否则 CompletableFuture 默认使用的线程池会立刻关闭:
//        Thread.sleep(2000);


        // 二、多个 CompletableFuture 串行执行
        // 例：第一个实例根据证券名称查找股票代码，第二个实例根据股票代码返回股票价格。串行操作
        // 第一个任务:
//        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> queryCode("中国石油"));
//
//        // cf1成功后继续执行cf2:
//        CompletableFuture<Double> cf2 = cf1.thenApplyAsync((code) -> fetchPrices(code));
//
//        // cf2 成功后打印结果
//        cf2.thenAccept((res) -> {
//            System.out.println("price: " + res);
//        });
//
//        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
//        Thread.sleep(2000);


        // 三、多个 CompletableFuture 并行执行
        // 多个 queryCode 操作或多个 fetchPrices 内可以异步执行。而在 queryCode 和 fetchPrices 间是同步操作
        // 1. 两个 CompletableFuture 执行异步 queryCode1 :
        CompletableFuture<String> cf_sina = CompletableFuture.supplyAsync(() -> {
            // 再重复一次，语法上是传入了一个函数，实际是通过 lambda 简化的一个类对象，这个对象实现了一个单方法接口
            return queryCode1("中国石油", "https://finance.sina.com.cn/code/");
        });

        CompletableFuture<String> cf_163 = CompletableFuture.supplyAsync(() -> {
            return queryCode1("中国石油", "https://money.163.com/code/");
        });

        // 2.用 anyOf 合并为一个新的 CompletableFuture:
        CompletableFuture<Object> cf_any = CompletableFuture.anyOf(cf_sina, cf_163);

        // 3.两个 CompletableFuture 执行异步 fetchPrice1 :
        CompletableFuture<Double> cf_sina_1 = cf_any.thenApplyAsync((code) -> {
            return fetchPrice1((String) code, "https://finance.sina.com.cn/price/");
        });

        CompletableFuture<Double> cf_163_1 = cf_any.thenApplyAsync((code) -> {
            return fetchPrice1((String) code, "https://money.163.com/price/");
        });

        // 4.用 anyOf 合并为一个新的 CompletableFuture:
        CompletableFuture<Object> cf_any_1 = CompletableFuture.anyOf(cf_sina_1,cf_163_1);

        // 5.最终结果
        cf_any_1.thenAccept((res)->{
            System.out.println("price: "+res);
        });

        Thread.sleep(2000);

//        上述逻辑实现的异步查询规则实际上是：
//
//┌─────────────┐ ┌─────────────┐
//│ Query Code  │ │ Query Code  │
//│  from sina  │ │  from 163   │
//└─────────────┘ └─────────────┘
//       │               │
//       └───────┬───────┘
//               ▼
//        ┌─────────────┐
//        │    anyOf    │
//        └─────────────┘
//               │
//       ┌───────┴────────┐
//       ▼                ▼
//┌─────────────┐  ┌─────────────┐
//│ Query Price │  │ Query Price │
//│  from sina  │  │  from 163   │
//└─────────────┘  └─────────────┘
//       │                │
//       └────────┬───────┘
//                ▼
//         ┌─────────────┐
//         │    anyOf    │
//         └─────────────┘
//                │
//                ▼
//         ┌─────────────┐
//         │Display Price│
//         └─────────────┘

        // anyOf() 要求传入的所有 CompletableFuture 只需成功一个；allOf() 要求传入的所有都得成功
        // thenAccept()处理正常结果；
        // exceptional()处理异常结果；
        // thenApplyAsync()用于串行化另一个 CompletableFuture；
        // anyOf() 和 allOf() 用于并行化多个 CompletableFuture。
    }

    // 本静态方法的签名符合 Supplier 接口的定义（除了方法名外）。
    static Double fetchPrice() {
        try {
            Thread.sleep(1000); // 表示这是一个若干时间后才会返回结果的任务

        } catch (InterruptedException e) {
        }
        if (Math.random() > 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);       // 模拟线程时延
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "601857";
    }

    static Double fetchPrices(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }

    static String queryCode1(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice1(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}


