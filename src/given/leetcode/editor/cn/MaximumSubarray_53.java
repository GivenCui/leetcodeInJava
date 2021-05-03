package given.leetcode.editor.cn;
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 3192 👎 0


// 53 最大子序和
public class MaximumSubarray_53 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new MaximumSubarray_53().new Solution();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(solution.maxSubArray(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法一 +: 动态规范优化
    class Solution {
        public int maxSubArray(int[] nums) {
            int ans = nums[0];
            int sum = 0;
            for (int num : nums) {
                if (sum > 0) {
                    sum += num;
                } else {
                    sum = num;
                }
                System.out.println(sum);
                ans = Math.max(ans, sum);
            }
            return ans;
        }
        // 解法一: 动态规范
        public int maxSubArray1(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(dp[i- 1] + nums[i], nums[i]);
                if (max < dp[i]) {
                    max = dp[i];
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}