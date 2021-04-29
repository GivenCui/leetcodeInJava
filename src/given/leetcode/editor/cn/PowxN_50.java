package given.leetcode.editor.cn;
//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
// Related Topics 数学 二分查找 
// 👍 641 👎 0


// 50 Pow(x, n)
public class PowxN_50 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new PowxN_50().new Solution();

        System.out.println(solution.myPow(2.00000, 10));
        System.out.println(solution.myPow(2.00000, -2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法三: 快速幂 + 迭代  (分治的思想)
    // 执行耗时:1 ms,击败了98.82% 的Java用户
    // 内存消耗:36.5 MB,击败了92.79% 的Java用户

    class Solution {
        public double myPow(double x, int n) {
            long N = n;
            return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
        }

        public double quickMul(double x, long N) {
            double ans = 1.0;
            // 贡献的初始值为 x
            double x_contribute = x;
            // 在对 N 进行二进制拆分的同时计算答案
            while (N > 0) {
                if (N % 2 == 1) {
                    // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                    ans *= x_contribute;
                }
                // 将贡献不断地平方
                x_contribute *= x_contribute;
                // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
                N /= 2;
            }
            return ans;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    // 解法一: 直接递归
    // 报错
    class Solution1 {
        public double myPow(double x, int n) {
            if (n == 0) return 1.0;

            int sign = n < 0 ? -1 : 1;
            n = sign * n;

            if (n == 1) return x;
            double res = myPow(x, n - 1) * x;
            return sign == -1 ? 1.0 /res : res;
        }
    }
    // 解法二: 快速幂 + 递归  (分治的思想)
    // 执行耗时:1 ms,击败了98.82% 的Java用户
    // 内存消耗:37.8 MB,击败了30.14% 的Java用户

    class Solution2 {
        public double myPow(double x, int n) {
            long N = n;
            return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
        }

        public double quickMul(double x, long N) {
            if (N == 0) {
                return 1.0;
            }
            double y = quickMul(x, N / 2);
            return N % 2 == 0 ? y * y : y * y * x;
        }
    }

}