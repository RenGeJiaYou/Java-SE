package com.genic;

import java.util.Arrays;

public class gen_7 {
    public static void main(String[] args) {
        // 一、extends 可读不可写
        // 举例：
        //  Array<? extends Number> a
        //      用在变量赋值时，可接收 Array<Number>、Array<Integer>、Array<Double> 等类型的实例对象，并 读取其值
        //      用在函数形参时，可接收 Array<Number>、Array<Integer>、Array<Double> 等类型的实参，编译时类型擦除为 Number (而非 Object )因此可以在函数内部调用 Number 类的方法。
        DynamicArray<Integer> ints = new DynamicArray<>();

//        DynamicArray<Number> nums = ints;         // 编译失败，因为 DynamicArray<Number> 不是 DynamicArray<Integer> 的父类，不能向上转型
        DynamicArray<? extends Number> nums = ints; // 编译成功.因为 ? extends Number 允许【读取】 Number 及其子类的变量
        Integer a = 100;
//        nums.add(1);                              // 报错
//        nums.add((Object) 2);                     // 报错
//        nums.add((Number) 3);                     // 报错
//        nums.add((Integer) 4);                    // 报错

        // ↑ 如果在声明泛型类的容器 nums 时，泛型类的参数使用了 <? extends Number> ,表明该变量可以接收并【读取】任何类型为 Number 及其子类的实例容器(见第 10 行)。
        // 但是，该变量不能【写入】 Number 及其子类的实例值。

        // 为了理解 Java 为什么要这么设计，不妨想想如果放开了写入的限制，会发生什么风险：
        // nums 作为一个 Number 类型的容器，将能被写入各种各样的基于 Number 类型的数据（Double、Integer....）
        // -> 违背了类型安全承诺


        // 但这种限制有时会阻碍合理的写入，为此可用 <? super E> 完成【写入】数据
        // 二、super 可写不可读
        DynamicArray<Integer> i = new DynamicArray<Integer>();
        i.add(3);
        i.add(100);

        // 此时希望将 DynamicArray<Integer> 中的 Integer 元素传入一个 DynamicArray<Number> 容器。
        DynamicArray<Number> n =new DynamicArray<Number>();
//        i.copyTo(n);    // 编译失败
        i.copyTo2(n);   // 编译成功。DynamicArray<Integer> 对象调用成员方法，该方法形参为 DynamicArray<? super E>
                        // 可以传入一个 DynamicArray<Number> 对象（或其它任何 Integer 的祖先类），
                        // 并【写入】这个对象以 DynamicArray<Integer> 类型的值

    }
}


class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity >= minCapacity) {
            return;
        }
        int newCapacity = oldCapacity * 2;
        if (newCapacity < minCapacity)
            newCapacity = minCapacity;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    // 将当前容器中的值传入到目标容器中
    public void copyTo(DynamicArray<E> dest){
        for(int i=0; i<size; i++){
            dest.add(get(i));
        }
    }
    // ※  E 将被替换为哪一个的类型：
    // 1.该函数作为 DynamicArray<Integer> 的实例方法调用；
    // 2.传入的实参是 DynamicArray<Number> 实例
    // E 将被替换为 Integer （1）. 即谁调用，擦除为谁的类型
    public void copyTo2(DynamicArray<? super E> dest){
        for(int i=0; i<size; i++){
            dest.add(get(i));
        }
    }
}