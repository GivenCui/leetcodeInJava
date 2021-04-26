package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/10 - 04 - 10 - 2:23 下午
 * @Description: com.given.test
 * @version: 1.0
 */
public class NegativeMIN_VALUE {
    public static void main(String[] args) {
        // 溢出问题
        // 以 int 类型举例
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        System.out.println(max); // 2147483647
        System.out.println(min); // -2147483648

        System.out.println(max + 1 == min); // true

        System.out.println(min - 1 == max); // true
        System.out.println(Math.abs(min) == min); // true
        System.out.println(min == min); // true
    }
}
