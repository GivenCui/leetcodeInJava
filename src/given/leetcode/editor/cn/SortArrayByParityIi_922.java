package given.leetcode.editor.cn;
//给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。 
//
// 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。 
//
// 你可以返回任何满足上述条件的数组作为答案。 
//
// 
//
// 示例： 
//
// 输入：[4,2,5,7]
//输出：[4,5,2,7]
//解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length <= 20000 
// A.length % 2 == 0 
// 0 <= A[i] <= 1000 
// 
//
// 
// Related Topics 排序 数组 
// 👍 203 👎 0


import java.util.Arrays;

// 922 按奇偶排序数组 II
public class SortArrayByParityIi_922 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new SortArrayByParityIi_922().new Solution();
        int[] arr1 = new int[]{4,2,5,7};
        int[] arr2 = new int[]{3,1,6,8};

        System.out.println(Arrays.toString(solution.sortArrayByParityII(arr1)));
        System.out.println(Arrays.toString(solution.sortArrayByParityII(arr2)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法二: 不是原地, 但是可以保留相对位置
    // 拉钩要求保持奇数或偶数的相对顺序
    // 执行耗时:3 ms,击败了81.78% 的Java用户
    // 内存消耗:41.2 MB,击败了39.23% 的Java用户
    class Solution {
        public int[] sortArrayByParityII(int[] nums) {
            int len = nums.length;
            int[] res = new int[len];

            for(int i=0,m = 0, n = 1; i < len; i++) {
                int cur = nums[i];
                if(nums[i] % 2 == 0) {
                    res[m] = cur;
                    m+=2;
                } else {
                    res[n] = cur;
                    n+=2;
                }
            }
            return res;
        }

        public boolean isOdd(int a) {
            return a % 2 != 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
// 解法一:
// 执行耗时:4 ms,击败了30.54% 的Java用户
// 内存消耗:39.5 MB,击败了85.46% 的Java用户
class Solution1 {
    public int[] sortArrayByParityII(int[] nums) {
        int ii = 0, i = 1;
        int len = nums.length;
        while (i < len && ii < len) {
            while(i < len && isOdd(nums[i])) {
                i+=2;
            }
            if(i >= len) break;
            while(ii < len && !isOdd(nums[ii])) {
                ii+=2;
            }
            if(ii >= len) break;

            // 如果没退出, 说明存在需要交换的
            int tmp = nums[ii];
            nums[ii] = nums[i];
            nums[i] = tmp;

            i+=2;
            ii+=2;
        }

        return nums;
    }

    public boolean isOdd(int a) {
        return a % 2 != 0;
    }
}
}