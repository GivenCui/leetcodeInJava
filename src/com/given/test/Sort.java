package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/29 - 04 - 29 - 4:53 下午
 * @Description: 排序, 均从小到大
 * @version: 1.0
 * 1. 冒泡排序 ✅
 * 2. 选择排序 ✅
 * 3. 插入排序 ✅
 */
public class Sort {
    static int count = 0;

    public static void main(String[] args) {
        int[] nums1 = new int[]{5, 4, 3, 2, 8, 9, 1, 2, 7, 6};
        int[] nums2 = new int[]{49, 38, 65, 97, 76, 13, 27, 49};

        // bubbleSort(nums1); // 冒泡排序
        // selectSort(nums1); // 选择排序
        // insertSort(nums1); // 插入排序
        quickSort(nums2); // 快速排序

        // 测试
        System.out.println("1 2 2 3 4 5 6 7 8 9");
        for (int i : nums1) {
            System.out.print(i + " ");
        }
        System.out.println("次数: " + count);

    }

    // 冒泡排序
    // 交换,比较, 每一轮选出最大的到右侧
    // 稳定
    // n = 10, 45次, flag优化 42次
    public static void bubbleSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        for (int i = 0; i < nums.length - 1; i++) {
            boolean noMove = true;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    noMove = false;
                }
                // count++;
            }
            if (noMove) break;
        }
    }

    // 选择排序
    // 每轮选择最小的, 和当前指针处交换, 不稳定, 不能优化
    public static void selectSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        for (int i = 0, len = nums.length; i < len - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[minIdx] > nums[j]) {
                    minIdx = j;
                }
                // count++;
            }

            // i处为最小不用自己和自己交换了
            if (minIdx != i) {
                int tmp = nums[minIdx];
                nums[minIdx] = nums[i];
                nums[i] = tmp;
            }
        }
    }

    // 插入排序
    // 一个有序的数组，我们往里面添加一个新的数据后，如何继续保持数据有序呢？
    // 只要遍历数组，找到数据应该插入的位置将其插入即可
    // 27次
    public static void insertSort(int[] nums) {
        int len;
        if (nums == null || (len = nums.length) <= 1) return;
        int i, j, cur;
        for (i = 1; i < len; i++) {
            cur = nums[i];
            // 查找插入位置
            for (j = i - 1; j >= 0 && nums[j] > cur; j--) {
                nums[j + 1] = nums[j]; // 数据移动
                // count++;
            }
            nums[j + 1] = cur; // 插入数据
        }
    }

    // 插入排序的拓展: 有序数组中插入新元素, 仍有序
    public static void sortedInsertNewValue() {
        int[] nums = new int[]{1,2,5,7,8};

        int newValue = 3;

        int[] arr = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }

        // 利用插入排序的思路, 处理
        int j = arr.length - 2;
        for(; j >= 0; j--) {
            if (arr[j] > newValue) {
                arr[j + 1] = arr[j]; // 移动数据
            } else {
                break;
            }
        }
        arr[j + 1] = newValue; // 插入数据
        // end --

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    // 快速排序
    // 参考王道考研: https://www.bilibili.com/video/BV1b7411N798?p=81
    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    public static void quickSort(int[] nums, int low, int high) {
        // 1. 终止条件 <-- bug2 忘记了写终止条件
        if(low >= high) return;

        int mid = partition(nums, low, high); // 划分
        quickSort(nums, low, mid - 1); // 划分左子表
        quickSort(nums, mid + 1, high); // 划分右子表
    }
    public static int partition (int[] nums, int low, int high) {
        int pivot = nums[low]; // 取最左侧为 基准
        // low,high指针扫描整个数组, 找到基准的正确位置
        while (low < high) {
            while(low < high && nums[high] >= pivot) high--; // 左基准已存, 有空, 所以右指针先行
            nums[low] = nums[high]; // 比基准小的移到左边
            while(low < high && nums[low] <= pivot) low++;
            nums[high] = nums[low]; // 比基准大的移到右边
        }
        nums[low] = pivot;  // 基准插入最终的正确位置, 此时low=high  <-- bug1 忘记了赋值回来
        return low; // 返回基准
    }
}
