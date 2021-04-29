package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/29 - 04 - 29 - 1:17 下午
 * @Description: 阶乘 n!=(n-1)!×n
 * @version: 1.0
 */
public class Factorial {
    public static void main(String[] args) {
        Factorial f = new Factorial();
        System.out.println(f.fact(3));
        System.out.println(f.fact(8)); // 40320
        System.out.println(f.fact(10)); // 3628800
    }
    public int fact(int n){
        // 在此处写入代码
        if (n == 0) {
            return 1;
        }
        return fact(n-1) * n;
    }
}
