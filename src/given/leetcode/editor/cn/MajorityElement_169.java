package given.leetcode.editor.cn;
//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//
// 进阶： 
//
// 
// 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
// 
// Related Topics 位运算 数组 分治算法 
// 👍 972 👎 0


import java.util.Arrays;

// 169 多数元素
public class MajorityElement_169 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new MajorityElement_169().new Solution();

        int[] nums1 = {2, 2, 1, 1, 1, 2, 2};
        int[] nums2 = {3, 2, 3};

        System.out.println(solution.majorityElement(nums1));
        System.out.println(solution.majorityElement(nums2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 方法二: 投票法
    // 执行耗时:1 ms,击败了100.00% 的Java用户
    // 内存消耗:41.8 MB,击败了58.37% 的Java用户
    class Solution {
        public int majorityElement(int[] nums) {
            int candidate = nums[0]; // 候选人
            int count = 1;

            for (int num : nums) {
                if (candidate == num) {
                    count++;
                } else if (--count == 0) {
                    candidate = num;
                    count = 1;
                }
            }
            return candidate;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    // 方法一: 排序 + 取中间数
    // 执行耗时:2 ms,击败了65.74% 的Java用户
    // 内存消耗:44.3 MB,击败了11.01% 的Java用户
    class Solution1 {
        public int majorityElement(int[] nums) {
            Arrays.sort(nums);
            return nums[nums.length / 2];
        }
    }
}