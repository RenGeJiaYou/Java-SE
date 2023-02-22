package com.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 动态代理
public class ref_6 {
    public static void main(String[] args) {
        // interface 是不能实例化的，因此 interface 变量总是通过某个实例向上转型并赋值给接口类型变量的。
        CharSequence cs = new StringBuilder();

        // 为了实现直接在运行期创建某个 interface 的实例，Java 提供了动态代理机制
        // 一、先看什么是静态代理：
        Hello h = new HelloWorld();     // 让一个（实现了接口的类）的实例，向上转型赋值给接口变量。
        h.morning("a");           // 调用

        // 动态代理：不必编写实现接口的类，而通过 JDK 提供的一个 Proxy.newProxyInstance() 在运行期动态创建了一个 Hello 接口对象。
        //     定义 handler
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if(method.getName().equals("morning")){
                    System.out.println("Good morning, " + args[0]);
                }
                return null;
            }
        };

        Hello h2 = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),       // 传入ClassLoader
                new Class[]{Hello.class},           // 传入要实现的接口
                handler                             // 传入处理调用方法的InvocationHandler
        );
        h2.morning("动态代理");

    }

    public interface Hello{
        void morning(String name);
    }

    // HelloWorld 类实现了 Hello 接口
    public static class HelloWorld implements Hello{
        @Override
        public void morning(String name) {
            System.out.println("Hello "+name);
        }
    }
}


