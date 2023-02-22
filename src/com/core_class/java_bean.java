package com.core.str;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

// Java Bean 是一种符合特殊规范的 class
// 这种规范是：该 class 有若干 private 字段和对应的 public getter/setter 方法（称为属性），
// 1.如果只有 get 方法，称为只读属性；只有 set 方法，称为只写属性
// 2.属性不一定要有对应的字段
// 3.使用 Introspector 枚举一个Bean 的属性
public class java_bean {
    public static void main(String[] args) throws Exception{
        BeanInfo bi = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor pd :bi.getPropertyDescriptors()){
            System.out.println(pd.getName());
            System.out.println(pd.getReadMethod());
            System.out.println(pd.getWriteMethod());
        }
    }



}

class Person{
    private int abc;
    private String xyz;
    private boolean tf;

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }

    public int getAbc() {
        return abc;
    }

    public void setAbc(int abc) {
        this.abc = abc;
    }

    public String getXyz() {
        return xyz;
    }

    public void setXyz(String xyz) {
        this.xyz = xyz;
    }
}
