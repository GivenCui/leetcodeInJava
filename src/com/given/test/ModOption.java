package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/26 - 04 - 26 - 11:43 上午
 * @Description: 对于 2^n 取余的优化, 可以转为 x & (2^n - 1), 应用场景: 3 无重复字符的最长子串
 * @version: 1.0
 */
public class ModOption {
    public static void main(String[] args) {
        int N = (int)Math.pow(2, 7);   // 128

        System.out.println(4 % N);       // 4
        System.out.println(4 & (N - 1)); // 4

        System.out.println(15 % N);       // 15
        System.out.println(15 & (N - 1)); // 15

        System.out.println(97 % N);        // 97
        System.out.println(97 & (N - 1));  // 97

        System.out.println(513 % N);        // 1
        System.out.println(513 & (N - 1));  // 1
    }
}
