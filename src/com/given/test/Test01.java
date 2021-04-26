package com.given.test;

import java.util.Scanner;

/**
 * @author : givencui
 * @date: 2021/4/18 - 04 - 18 - 11:26 上午
 * @Description: com.given.test
 * @version: 1.0
 */
public class Test01 {
    public static void main(String[] args) {
//        char c = '1' + '3';
//        System.out.println('1' + 0); // 49
//        System.out.println('3' + 0); // 51
//        System.out.println(c);

        Scanner scanner = new Scanner(System.in);

        String word1 = scanner.next(); // "This"
        String line1 = scanner.nextLine(); // " is a simple"
        String word2 = scanner.next(); // "multiline"
        String word3 = scanner.next(); // "input,"
        String line2 = scanner.nextLine(); // ""

        System.out.println("word1 = |" + word1 + "|");
        System.out.println("line1 = |" + line1 + "|");
        System.out.println("word2 = |" + word2 + "|");
        System.out.println("word3 = |" + word3 + "|");
        System.out.println("line2 = |" + line2 + "|");

    }
}
