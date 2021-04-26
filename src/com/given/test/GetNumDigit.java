package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/10 - 04 - 10 - 4:28 下午
 * @Description: 依次获取数字的每一位并输出
 * @version: 1.0
 */
public class GetNumDigit {
    public static void main(String[] args) {
        getDigit(123);
        getDigit(-123);
    }
    public static void getDigit(int num) {

        while(num != 0) {
            System.out.println(num % 10);
            num /= 10;
        }
    }
}
