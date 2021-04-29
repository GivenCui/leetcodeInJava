package given.leetcode.editor.cn;
//斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是： 
//
// 
//F(0) = 0，F(1) = 1
//F(n) = F(n - 1) + F(n - 2)，其中 n > 1
// 
//
// 给你 n ，请计算 F(n) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：2
//输出：1
//解释：F(2) = F(1) + F(0) = 1 + 0 = 1
// 
//
// 示例 2： 
//
// 
//输入：3
//输出：2
//解释：F(3) = F(2) + F(1) = 1 + 1 = 2
// 
//
// 示例 3： 
//
// 
//输入：4
//输出：3
//解释：F(4) = F(3) + F(2) = 2 + 1 = 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 30 
// 
// Related Topics 数组 
// 👍 270 👎 0


// 509 斐波那契数
public class FibonacciNumber_509 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new FibonacciNumber_509().new Solution();

        System.out.println(solution.fib(3)); // 2
        System.out.println(solution.fib(5)); // 5
        System.out.println(solution.fib(7)); // 13
        System.out.println(solution.fib(10)); // 55
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // F(0) = 0，F(1) = 1
   // F(n) = F(n - 1) + F(n - 2)，其中 n > 1
    // 给你 n ，请计算 F(n) 。
    //   执行耗时:0 ms,击败了100.00% 的Java用户
    //	 内存消耗:35.1 MB,击败了77.00% 的Java用户
    class Solution {
        public int fib(int n) {
            if (n == 0) return 0;
            if (n == 1) return 1;
            int[] dp = new int[]{0, 1};

            int res = 0;
            while(2 <= n) {
                res = dp[0] + dp[1];
                dp[0] = dp[1];
                dp[1] = res;
                n--;
            }

           return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
    // 方法一: 递归
    class Solution1 {
        public int fib(int n) {
            if (n == 0) return 0;
            if (n == 1) return 1;

            return fib(n - 1) + fib(n - 2);
        }
    }
}