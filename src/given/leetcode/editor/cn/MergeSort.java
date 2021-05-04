package given.leetcode.editor.cn;

import java.util.Arrays;

/**
 * @author : givencui
 * @date: 2021/5/3 - 05 - 03 - 9:02 下午
 * @Description: 归并排序
 * 参考  https://mubu.com/app/edit/home/3LYV5pgrYS#o-1bhnTjoyKh
 * 1.
 * @version: 1.0
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums1 = {1, 5, 8, 9, 15, 2, 3, 6, 10};

        // 方法一: 修改数组本身
        // mergeSort_1(nums1);
        System.out.println(Arrays.toString(nums1));

        // 返回新数组
        int[] newArr = mergeSort_2(nums1);
        System.out.println(Arrays.toString(newArr));
    }
    // 解法二: 视频
    // 返回的是新数组
    public static int[]  mergeSort_2(int[] nums) {
        int len = nums.length;
        if(len <= 1) return nums;

        int mid = len / 2;
        int[] leftArr = Arrays.copyOfRange(nums, 0, mid); // 注意左闭右开
        mergeSort_2(leftArr);

        int[] rightArr = Arrays.copyOfRange(nums, mid, len);
        mergeSort_2(rightArr);

        return merge_2(leftArr, rightArr);
    }
    // 合并两个有序数组为一个有序数组
    public static int[] merge_2(int[] leftArr, int[] rightArr) {
        int[] res = new int[leftArr.length + rightArr.length];

        int p = 0, q = 0;
        for (int i = 0; i < res.length; i++) {
            if(p < leftArr.length && q < rightArr.length) {
                if(leftArr[p] <= rightArr[q]) { // <-- 这里等号保证了稳定性 (左边区域一定要在右边区域的前面)
                    res[i] = leftArr[p];
                    p++;
                } else {
                    res[i] = rightArr[q];
                    q++;
                }
            } else if (p < leftArr.length) {
                res[i] = leftArr[p];
                p++;
            } else {
                res[i] = rightArr[q];
                q++;
            }
        }

        return res;
    }

    // 解法一: 归并排序
    // 1. 递归的拆分
    // 2. 合并
    public static void mergeSort_1(int[] nums) {
        mergeSort_1_help(nums, 0, nums.length - 1);
    }

    public static void mergeSort_1_help(int[] nums, int l, int r) {
        if (l >= r) return;

        int m = (l + r) / 2;

        // 1
        mergeSort_1_help(nums, l, m);
        mergeSort_1_help(nums, m + 1, r);

        // 2
        merge_1(nums, l, m, m + 1, r);
    }

    public static void merge_1(int[] nums, int l1, int r1, int l2, int r2) {
        int len = r2 - l1 + 1;
        int[] tmp = new int[len]; // 临时数组

        // 按从小到大取出到 tmp数组
        int p = l1, q = l2;
        for (int i = 0; i < len; i++) {
            if (p <= r1 && q <= r2) {
                if (nums[p] <= nums[q]) {
                    tmp[i] = nums[p];
                    p++;
                } else {
                    tmp[i] = nums[q];
                    q++;
                }
            } else if (p <= r1) {
                tmp[i] = nums[p];
                p++;
            } else {
                tmp[i] = nums[q];
                q++;
            }
        }

        // 复制到 nums数组
        for (int i = 0; i < len; i++) {
            nums[l1 + i] = tmp[i];
        }
    }
}
