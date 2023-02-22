package com.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 访问方法
/*
 * Method getMethod(name, Class...)：获取某个public的Method（包括父类）
 * Method getDeclaredMethod(name, Class...)：获取当前类的某个Method（不包括父类）,包括非 public 方法
 * Method[] getMethods()：获取所有public的Method（包括父类）
 * Method[] getDeclaredMethods()：获取当前类的所有Method（不包括父类）
 * */
public class ref_3 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class cls = Student1.class;
        Method m1 = cls.getMethod("getScore", String.class);
        Method m2 = cls.getDeclaredMethod("getGrade", int.class);
        Method m3 = cls.getMethod("getName");
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m3);

        // 一、Method 对象包含一个函数的所有信息
        System.out.println("方法名  " + m1.getName());                                 // 方法名
        System.out.println("返回值  " + m1.getReturnType());                           // 返回值
        System.out.println("参数类型  " + m1.getParameterTypes()[0].toString());       // 参数类型
        System.out.println("参数个数  " + m1.getParameterCount());                     // 参数个数
        System.out.println("修饰符代码  " + m1.getModifiers());                        // 修饰符代码


        // 二、调用实例方法，invoke() 第一个参数是实例对象
        // 1.获取 substring(int).
        Method m4 = String.class.getMethod("substring", int.class);

        // 2.对一个字符串变量调用该方法
        String s = "Hello World";
        String res = (String) m4.invoke(s, 4); //使用 invoke() 传参并完成调用

        System.out.println(res);


        // 三、调用静态方法，invoke() 第一个参数是 null
        Method m5 = Integer.class.getMethod("parseInt", String.class);
        Integer num = (Integer) m5.invoke(null, "123456");
        System.out.println(num);


        // 四、调用非 public 方法
        // 1.需要 getDeclaredMethod(),否则编辑器报错
        // 2.需要  m6.setAccessible(true) 开启访问权限
        Person1 p = new Person1();
        Method m6 = p.getClass().getDeclaredMethod("setName", String.class); // 获取到  private 方法 setName(String name)
        m6.setAccessible(true); // Method 对象开启访问权限
        m6.invoke(p, "daisn");
        System.out.println(p.getName());


        // 五、多态。从基类.class获取的 Method，作用于超类实例时，调用的是超类的方法
    }
}

class Student1 extends Person1 {

    public int getScore(String type) {
        return 99;
    }

    private int getGrade(int year) {
        return 1;
    }


}

class Person1 {
    String name;

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }
}