package com.given.test;

import java.util.Arrays;

/**
 * @author : givencui
 * @date: 2021/5/4 - 05 - 04 - 3:21 下午
 * @Description: 归并排序的 合并函数专门练习
 * 参考:
 * 1. 哨兵法 https://blog.csdn.net/springbounce/article/details/94010710
 * @version: 1.0
 */
public class MergeFunction {
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 8, 9, 15, 2, 3, 6, 10};
        // merge(nums1, 0, 4, nums1.length - 1);
        mergeWithSentry(nums1, 0, 4, nums1.length - 1);

        System.out.println(Arrays.toString(nums1));
    }
    // 方法一: 不使用哨兵法
    // [l, m] 左部分数组, [m+1, r] 右部分数组
    public static void merge(int[] nums, int l, int m, int r) {
        int[] tmp = new int[r - l + 1];

        int p = l, q = m + 1, index = 0;

        // 都存在, 比较大小, 小的存入
        while(p <= m && q <= r) {
            if(nums[p] <= nums[q]) {
                tmp[index++] = nums[p++];
            } else {
                tmp[index++] = nums[q++];
            }
        }
        // 存在的存入
        while(p <= m) {
            tmp[index++] = nums[p++];
        }
        while(q <= r) {
            tmp[index++] = nums[q++];
        }

        // tmp临时数组中的存入nums
        for (int i = 0; i < tmp.length; i++) {
            nums[l + i] = tmp[i];
        }
    }
    // 方法二: 使用哨兵法
    // [l, m] 左部分数组, [m+1, r] 右部分数组
    public static void mergeWithSentry(int[] nums, int l, int m, int r) {
        int leftSize = m - l + 1;
        int rightSize = r - m;
        int[] leftArr = new int[leftSize + 1];
        int[] rightArr = new int[rightSize + 1];

        // 复制左部分区域到 leftArr
        int i = 0;
        for (; i < leftSize; i++) {
            leftArr[i] = nums[i + l];
        }
        leftArr[i] = Integer.MAX_VALUE; // 哨兵
        // 复制右部分区域到 rightArr
        for(i=0; i < rightSize; i++) {
            rightArr[i] = nums[i + m + 1];
        }
        rightArr[i] = Integer.MAX_VALUE; // 哨兵

        // 依次比较 leftArr 和 rightArr, 较小的 存入 nums数组
        for (int p = 0, q = 0, index = 0; p < leftSize || q < rightSize; index++) {
            if(leftArr[p] <= rightArr[q]) {
                nums[index] = leftArr[p++];
            } else {
                nums[index] = rightArr[q++];
            }
        }
    }
}
