package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/30 - 04 - 30 - 10:07 上午
 * @Description: 快排两种写法, 思路是一致的
 * 幕布: https://mubu.com/app/edit/home/3LYV5pgrYS#o-hxH9K3BfNd
 * @version: 1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums1= new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        // quickSort1(nums1, 0, nums1.length - 1);
        quickSort2(nums1, 0, nums1.length - 1);

        // 测试
        System.out.println("---- 结果 ----");
        System.out.println("13 27 38 49 49 65 76 97");
        for (int i : nums1) {
            System.out.print(i + " ");
        }
    }
    // 参考王道
    // 思路清晰, 代码整洁, 但是执行中会有多余步骤
    public static void quickSort1(int[] nums, int low, int high) {
        if (nums == null || nums.length <= 1) return;
        if(low >= high) return;

        int mid = partition(nums, low, high); // 划分
        quickSort1(nums, low, mid-1); // 划分左子区间
        quickSort1(nums, mid+1, high); // 划分右子区间
    }
    public static int partition(int[] nums, int low, int high) {
        int pivot = nums[low];

        while(low < high) {
            while(low < high && nums[high] >= pivot) --high;
            nums[low] = nums[high];
            while(low < high && nums[low] <= pivot) ++low;
            nums[high] = nums[low];
        }

        nums[low] = pivot;
        return low;
    }

    // 参考拉钩
    // 更多的if判断, 提前结束, 避免无效的函数调用
    public static void quickSort2(int[] nums, int low, int high) {
        if(low >= high) return;

        // 定位基准 i;
        // TIPS: 指针最好不要和初始变量混淆, 多声明变量, 最后再优化
        int i = low, j = high;
        int pivot = nums[i];
        while(i < j) {
            while(i < j && nums[j] >= pivot) j--;
            if (i < j) nums[i++] = nums[j];
            while(i < j && nums[i] <= pivot) i++;
            if(i < j) nums[j--] = nums[i];
        }
        nums[i] = pivot;
        // 划分子区间1
        if(low < i - 1) quickSort2(nums, low, i - 1);
        // 划分子区间2
        if(high > i + 1) quickSort2(nums, i + 1, high);
    }
}
