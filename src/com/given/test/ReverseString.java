package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/10 - 04 - 10 - 5:39 下午
 * @Description: 字符串反转
 * @version: 1.0
 */
public class ReverseString {
    public static void main(String[] args) {
        System.out.println(reverse("123a456"));
    }

    public static String reverse(String s) {
        char[] chars = s.toCharArray();

        int p = 0;
        int q = chars.length - 1;

        while(p < q) {
            char tmp = chars[p];
            chars[p] = chars[q];
            chars[q] = tmp;

            p++;
            q--;
        }

        return String.valueOf(chars);
    }
}
