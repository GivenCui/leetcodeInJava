package com.given.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author : givencui
 * @date: 2021/4/28 - 04 - 28 - 11:14 上午
 * @Description: Collections api 练习
 * @version: 1.0
 */
public class CollectionAPI {
    public static void main(String[] args) {
       // Collections.min
        // 1. 去数组 ArrayList中的最小值
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        System.out.println(Collections.min(arrayList)); // 1

        // 2. 取HashMap的value中的最小值
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("a1", 10);
        hashMap.put("a2", 100);
        hashMap.put("b1", 8);

        System.out.println(Collections.min(hashMap.values())); // 8


    }
}
