package given.leetcode.editor.cn;
//给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定不同的子数组为好子数组。 
//
// （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。） 
//
// 返回 A 中好子数组的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：A = [1,2,1,2,3], K = 2
//输出：7
//解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
// 
//
// 示例 2： 
//
// 
//输入：A = [1,2,1,3,4], K = 3
//输出：3
//解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 20000 
// 1 <= A[i] <= A.length 
// 1 <= K <= A.length 
// 
// Related Topics 哈希表 双指针 Sliding Window 
// 👍 295 👎 0


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

// 992 K 个不同整数的子数组
public class SubarraysWithKDifferentIntegers_992 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new SubarraysWithKDifferentIntegers_992().new Solution();

        System.out.println(solution.subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2)); // 7
//        System.out.println(solution.subarraysWithKDistinct(new int[]{1,2,1,3,4}, 3)); // 3
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 注意关键条件
    // 1 <= A[i] <= A.length
    // 1 <= K <= A.length
    // 解法二: 解法一的基础上改写, 用 HashMap
    // 求存在 K 个不同整数的子数组的个数  ----> (最多存在 K 个不同整数的子区间的个数 - 最多存在 K 个不同整数的子区间的个数)
    // 目的: 求最多存在 K 个不同整数的子区间的个数 subarraysWithMostKDistinct
    // 参考: https://leetcode-cn.com/problems/subarrays-with-k-different-integers/solution/cong-zui-jian-dan-de-wen-ti-yi-bu-bu-tuo-7f4v/
    // Time Limit Exceeded ??
    class Solution {
        public int subarraysWithKDistinct(int[] A, int K) {
            return subarraysWithMostKDistinct(A, K) - subarraysWithMostKDistinct(A, K - 1);
        }

        /**
         * @param A
         * @param K
         * @return 最多包含 K 个不同整数的子区间(子数组)的个数
         */
        private int subarraysWithMostKDistinct(int[] A, int K) {
            int len = A.length;
            if (K == len) return 1;

            int count = 0;
            HashMap hashMap = new HashMap<Integer, Integer>();
            int left = 0, right = 0;

            while(right < len) {
//                hashMap.put(A[right++], right);  // bug1
                hashMap.put(A[right], right++);

                if (hashMap.size() > K) {
                    int minIdx = (int)Collections.min(hashMap.values());
                    left = Math.max(minIdx, left);
                    left++;
                    hashMap.remove(A[minIdx]);
                }
//                System.out.println(Arrays.toString(Arrays.copyOfRange(A, left, right)));
                count += right - left;
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
    // 解法一: 官方解法
    // 注意关键条件
    // 1 <= A[i] <= A.length
    // 1 <= K <= A.length

    // 求存在 K 个不同整数的子数组的个数  ----> (最多存在 K 个不同整数的子区间的个数 - 最多存在 K 个不同整数的子区间的个数)
    // 目的: 求最多存在 K 个不同整数的子区间的个数 subarraysWithMostKDistinct
    // 参考: https://leetcode-cn.com/problems/subarrays-with-k-different-integers/solution/cong-zui-jian-dan-de-wen-ti-yi-bu-bu-tuo-7f4v/
//    执行耗时:5 ms,击败了72.71% 的Java用户
//    内存消耗:41.5 MB,击败了87.38% 的Java用户
    class Solution1 {
        public int subarraysWithKDistinct(int[] A, int K) {
            return subarraysWithMostKDistinct(A, K) - subarraysWithMostKDistinct(A, K - 1);
        }

        /**
         * @param A
         * @param K
         * @return 最多包含 K 个不同整数的子区间(子数组)的个数
         */
        private int subarraysWithMostKDistinct(int[] A, int K) {
            int len = A.length;
            // 1 <= A[i] <= A.length
            // int数组的元素默认值为 0
            int[] freqArr = new int[len + 1];

            int left = 0;
            int right = 0;
            // [left, right) 里不同整数的个数
            int count = 0;
            int res = 0;
            // [left, right) 包含不同整数的个数小于等于 K
            while (right < len) {
                if (freqArr[A[right]] == 0) { // 元素第一次出现
                    count++;
                }
                freqArr[A[right]]++; // 更新元素出现的次数
                right++;

                while (count > K) {
                    freqArr[A[left]]--;
                    if (freqArr[A[left]] == 0) {
                        count--;
                    }
                    left++;
                }
                // [left, right) 区间的长度就是对结果的贡献
//                System.out.println(Arrays.toString(Arrays.copyOfRange(A, left, right)));
//                System.out.println(right - left);
                // 这里用了动态规划的思路:
                // 当满足条件的子数组从 [A,B,C] 增加到 [A,B,C,D] 时，新子数组的长度为 4
                // 同时增加的子数组为 [D], [C,D], [B,C,D], [A,B,C,D] 也为 4
                res += right - left;
            }
            return res;
        }
    }
}