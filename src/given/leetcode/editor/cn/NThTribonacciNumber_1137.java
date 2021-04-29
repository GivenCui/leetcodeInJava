package given.leetcode.editor.cn;
//泰波那契序列 Tn 定义如下： 
//
// T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2 
//
// 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。 
//
// 
//
// 示例 1： 
//
// 输入：n = 4
//输出：4
//解释：
//T_3 = 0 + 1 + 1 = 2
//T_4 = 1 + 1 + 2 = 4
// 
//
// 示例 2： 
//
// 输入：n = 25
//输出：1389537
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 37 
// 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。 
// 
// Related Topics 递归 
// 👍 67 👎 0


// 1137 第 N 个泰波那契数
public class NThTribonacciNumber_1137 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new NThTribonacciNumber_1137().new Solution();
        System.out.println(solution.tribonacci(4) == 4);
        ;
        System.out.println(solution.tribonacci(25) == 1389537);
        ;
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int tribonacci(int n) {
            // 解法一: 超时
            // if (n == 0) return 0;
            // if (n == 1) return 1;
            // if (n == 2) return 1;
            //
            // return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);

            // 解法二:
            // 执行耗时:0 ms,击败了100.00% 的Java用户
            // 内存消耗:35.3 MB,击败了28.21% 的Java用户
            if (n < 0) return 0;
            int[] dp = new int[]{0, 1, 1};
            if (n < 3) return dp[n];

            int res = 0;
            while(3 <= n) {
                res = dp[2] + dp[1] + dp[0];
                dp[0] = dp[1];
                dp[1] = dp[2];
                dp[2] = res;
                n--;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}