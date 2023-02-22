package com.test.array;

import java.util.Arrays;

//包名必须为英文，否则报错。但是任何英文均可（Go中的main.go 必须在 Main 包下）
public class Array {
    public static void main(String[] args) {
      int[] arr = {1,56,2,177,4,7,34,44};
/*
* 一、遍历
* */
      //1.常见用法
      for (int i=0;i<arr.length;i++){
          System.out.println(arr[i]);
      }

      //2.当遍历一个可迭代对象时，使用 for-each 用法更简洁
        for(int a:arr){
            System.out.print(a+", ");
        }
        System.out.println();
/*
* 二、排序
* */
    //1.冒泡排序
        for (int i = 0; i < arr.length-1; i++) {        // 外循环控制执行 n-1 次内循环
            for (int j = 0; j < arr.length-1-i; j++) {  // 由于上一趟内循环已经将最大的元素放到待解决数组的最后一位，待解决数组长度-1
                if(arr[j]>arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        for (int a:arr) {
            System.out.print(a+" - ");
        }
        /*此处可以延伸一点对 Java 数据结构的理解：
        * Java 的数组变量可看作是一个（C++）指针数组，每个指针的值指向堆内存中的一个数组元素。当排序时，指针的指向变了，但堆内存中的元素固定不变
        * */

/*
* 三、多维数组
* 
* */
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        int[] m0 = mat[0];
        System.out.println(mat.length);
        System.out.println(m0.length);
        // 使用标准库完成二维数组的读取
        System.out.println(Arrays.deepToString(mat));

/*
* 四、命令行参数
* */
        for (String arg:args) {
            System.out.println(arg);
        }
    }

}
