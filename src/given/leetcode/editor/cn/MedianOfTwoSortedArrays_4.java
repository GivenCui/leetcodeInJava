package given.leetcode.editor.cn;
//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治算法 
// 👍 4050 👎 0


// 4 寻找两个正序数组的中位数
public class MedianOfTwoSortedArrays_4 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new MedianOfTwoSortedArrays_4().new Solution();

        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2})); // 2.0
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4})); // 2.5
        System.out.println(solution.findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0})); // 0.0
        System.out.println(solution.findMedianSortedArrays(new int[]{}, new int[]{1})); // 0.0
        System.out.println(solution.findMedianSortedArrays(new int[]{2}, new int[]{})); // 0.0
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法一: 数数法, 两个有序数组合并为一个有序数组的套路
    // 执行耗时:3 ms,击败了82.29% 的Java用户
    // 内存消耗:40 MB,击败了6.76% 的Java用户
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;
            int len = len1 + len2;

            int p = 0, q = 0, m1 = 0, m2 = 0;
            for (int i = 0; i <= len / 2; i++) {
                m1 = m2;
                // if (p < len1 && q < len2) {
                //     if (nums1[p] <= nums2[q]) {
                //         m2 = nums1[p++];
                //     } else {
                //         m2 = nums2[q++];
                //     }
                // } else if (p < len1) {
                //     m2 = nums1[p++];
                // } else {
                //     m2 = nums2[q++];
                // }
                if(p < len1 && (q >=len2 || nums1[p] <= nums2[q])) {
                    m2 = nums1[p++];
                } else {
                    m2 = nums2[q++];
                }
            }

            return len % 2 == 0 ? (m1 + m2) / 2.0 : m2; // bug1 结果期望是浮点数, 应该 / 2.0,  而不是 2
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}