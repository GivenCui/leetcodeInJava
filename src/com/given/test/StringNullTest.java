package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/26 - 04 - 26 - 11:32 上午
 * @Description: com.given.test
 * @version: 1.0
 */
public class StringNullTest {
    public static void main(String[] args) {
        System.out.println(test(null));
        System.out.println(test(""));
    }
    public static int test(String s) {
        if (s == null) return 0;
        return s.length();
    }
}
