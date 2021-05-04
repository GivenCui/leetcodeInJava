package com.given.test;

/**
 * @author : givencui
 * @date: 2021/5/4 - 05 - 04 - 5:20 下午
 * @Description: i++和++i详解, 参考: https://blog.csdn.net/android_cai_niao/article/details/106027313
 * @version: 1.0
 */
public class PlusOneAndOnePlus {
    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        // test4();
        test5();
    }
    public static void test1() {
        int i = 0;
        i = i++;
        // i = ++i;
        System.out.println("i = " + i); // i = 0
    }
    public static void test2() {
        int a = 2;
        int b = 3 * a++ + a;
        System.out.println(b); // 9
    }
    public static void test3() {
        int a = 2;
        int b = a + 3 * a++; // 表达式的顺序一直都是从左到右
        // int b = (1 * a) + (3 * a++);  // 等价为
        System.out.println(b); // 8
    }
    public static void test4() {
        int i = 1;
        int j = 1;
        int k = i++ + ++i + ++j + j++; // 1 + 3 + 2 + 2
        System.out.println(k); // 8
    }
    public static void test5() {
        int a = 0;
        int b = 0;
        a = a++; // 1. a先自增, a = 1, 表达式值为 0;  2. 0 赋值给 a, 之前的值被覆盖
        b = a++;

        System.out.println(a + " " + b); // 1 0
    }
}
