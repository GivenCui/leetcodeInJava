package com.given.test;

import java.util.*;

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

        // 3. 普通数组
        int[] arr1 = {1, 2, 3, 4, 5};
        Integer[] arr2 = {1, 2, 3, 4, 5};

        // Arrays.asList(T... a)
        // 1. 如果 类型匹配, Arrays.asList(T[] a)
        // 2. 如果是 int[], 不能转为 T[], 只能转为 T<int[]>, 因为 int[]整体是引用类型
        List<int[]> test1 = Arrays.asList(arr1); // 容器只能放引用类型, 所以整体变为 list[0], size() 为1
        final List<Integer> test2 = Arrays.asList(arr2);// size() 为 5

        // System.out.println(Collections.min(test1)); // 报错
        System.out.println(Collections.min(test2)); // 1

        System.out.println(test1.size()); // 1
        System.out.println(test2.size()); // 5


        // 4. 可变参数
        testModifyParams(arr1); // int[] 无法转型为 Object[], 因而被当作一个单纯的数组对象
        testModifyParams(arr2);

    }

    public static void testModifyParams(Object...arr){
    // public static void testModifyParams(Object[] arr){
        System.out.print("内容："+Arrays.toString(arr));
        System.out.println("\t\t数组长度："+arr.length+" ");
    }
}
