package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/10 - 04 - 10 - 1:14 下午
 * @Description: com.given.test
 * @version: 1.0
 */
public class Int2String {
    public static void main(String[] args) {
        // 数字转字符串
        int num = 123;
        
        String numStr = String.valueOf(num);

        System.out.println("numStr = " + numStr); // numStr = 123
        System.out.println(getType(numStr)); // class java.lang.String

        // 字符串转int
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.valueOf("123"));

        // 字符串转long
        System.out.println(Long.parseLong("123"));
        System.out.println(Long.valueOf("123"));
    }

    public static String getType(Object o){ //获取变量类型方法
        return o.getClass().toString(); //使用int类型的getClass()方法
    }
}
