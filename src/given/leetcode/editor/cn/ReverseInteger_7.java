package given.leetcode.editor.cn;
//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。 
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。 
//假设环境不允许存储 64 位整数（有符号或无符号）。
// 示例 1：
//输入：x = 123
//输出：321
// 
//
// 示例 2： 
//输入：x = -123
//输出：-321
// 
//
// 示例 3： 
//输入：x = 120
//输出：21
//
// 示例 4： 
// 输入：x = 0
// 输出：0
//
// 提示： 
// -2^31 <= x <= 2^31 - 1
// 
// Related Topics 数学 
// 👍 2686 👎 0


import java.util.Arrays;

// 7 整数反转
public class ReverseInteger_7 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new ReverseInteger_7().new Solution();

        // 测试数据
        int[] testCase = {1234, -123, 120, 0, Integer.MAX_VALUE, Integer.MIN_VALUE};

        for (int i : testCase) {
            System.out.println(solution.reverse(i));
        }

//            System.out.println(solution.reverse(Integer.MIN_VALUE));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 变化: 假设不能用超过4字节的类型接收
         *
         * @param x 指定整数
         * @return 反转后的整数，或0
         */
        public int reverse(int x) {
            // 32 位的有符号整数 [-2^31, 2^31 - 1]
            int maxSlice = Integer.MAX_VALUE / 10;
            int ret = 0;
            int last;

            // 退出循环时, last 是最高位
            while((last = x % 10) != x) {
                ret = 10 * ret + last;
                x /= 10;
            }

            // 单独处理最高位, 应为只有此时才可能溢出
            // 大于 Integer.MAX_VALUE
            if (ret > maxSlice || (ret == maxSlice && last > 7) ) {
                return 0;
            }
            // 小于 Integer.MIN_VALUE
            if (ret < -1 * maxSlice || (ret == -1 * maxSlice && last < -8)) {
                return 0;
            }

            return ret * 10 + last;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        /**
         * 解法一，暴力解法
         * 时间复杂度: O(n)
         * 空间复杂度: O(n)
         *
         * 思路:
         * 1.整数转字符串，再转字符数组
         * 2.反向遍历字符数组，并将元素存储到新数组中
         * 3.将新数组转成字符串，再转成整数输出
         *
         * 注意事项: 边界问题
         *    数组索引越界
         *       数值溢出边界:溢出则返回0
         *    细节问题
         *          首位不为0
         *          符号处理
         *    @param x 指定整数
         *    @return 反转后的整数，或0
         */
        public int reverse(int x) {
            int max = Integer.MAX_VALUE; // 2147483647
            int min = Integer.MIN_VALUE; // -2147483648
            if (x == min) return 0;
            // 细节1: 处理负数
            int sign = x < 0 ? -1 : 1;
//            x = Math.abs(x); // Math.abs(Integer.MIN_VALUE) == Integer.MIN_VALUE  ??
            x = x < 0 ? -x : x;

            // 1. int -> string -> char[]
            String s = String.valueOf(x);
            char[] sArr = s.toCharArray();

            // 2. char[]逆序输出到新数组 ret[]
            int len = sArr.length;
            char[] retArr = new char[len];

            for (int i = 0; i < len; i++) {
                retArr[i] = sArr[len - 1 - i];
            }

            // 3. ret[] -> string -> int
            long ret = Long.parseLong(String.valueOf(retArr));

            // 细节2: 处理越界
            if (ret > max || ret < min) return 0;


            return (int)ret * sign;
        }
    }
    class Solution2 {
        /**
         * 解法二，优化解法
         * 时间复杂度: O(n)
         * 空间复杂度: O(n)
         * 思路:
         * 1.整数转字符串，再转字符数组
         * 2.交换首位(start)和末位(end)数字
         * 3.循环操作:依次交换第二(start++)和倒数第二个(end--) 直到数组剩下1个或0个元素
         * 4.将原数组转成字符串，再转成整数输出
         *
         * 注意事项: 边界问题
         * 1. 数组索引越界:
         *  数组长度为偶数，反转完成标志为start>end;
         *  为奇数时反转完成标志为start==end
         *
         * 2. 数值溢出边界:溢出则返回0
         *
         * 细节问题
         * 1. 首位不为0
         * 2. 符号处理
         *
         * @param x 指定整数
         * @return 反转后的整数，或0
         */
        public int reverse(int x) {
            int max = Integer.MAX_VALUE; // 2147483647
            int min = Integer.MIN_VALUE; // -2147483648
            if (x == min) return 0;
            // 细节1: 处理负数
            int sign = x < 0 ? -1 : 1;
            x = x < 0 ? -x : x;

            // 1.整数转字符串，再转字符数组
            String s = String.valueOf(x);
            char[] sArr = s.toCharArray();

            // 2. 交换首位(start)和末位(end)数字
            int p = 0;
            int q = sArr.length - 1;

            while(p < q) {
                char tmp = sArr[p];
                sArr[p] = sArr[q];
                sArr[q] = tmp;
                p++;
                q--;
            }
            // 3. ret[] -> string -> int
            long ret = Long.parseLong(String.valueOf(sArr));

            // 细节2: 处理越界
            if (ret > max || ret < min) return 0;


            return (int)ret * sign;
        }
    }
    class Solution3 {
        /**
         * 最优解法 数学解法
         *
         * 思路:
         * 1.尝试拿个位数字: 对10取模运算得到个位数字
         * 2.让每一位数字变成个位数字: 先除以10，再对10取模得到十位数字
         *   循环上述操作
         * 3.将每一位数字计算累加: 将上次累加结果 * 10 + 新数字
         *
         * 注意事项:
         *
         * 循环边界:
         * 数值溢出边界: 溢出则返回0
         *    用long类型存放，溢出int则返回0
         *
         * 细节问题:
         *   首位不为0
         *   符号处理
         *
         * @param x 指定整数
         * @return 反转后的整数，或0
         */
        public int reverse(int x) {
            int max = Integer.MAX_VALUE; // 2147483647
            int min = Integer.MIN_VALUE; // -2147483648
            if (x == min) return 0;
            // 细节1: 处理负数
            int sign = x < 0 ? -1 : 1;
            x = x < 0 ? -x : x;

            long ret = 0;

            // 循环
            while(x != 0) {
                ret = ret * 10 + x % 10; // 1.尝试拿个位数字
                x /= 10; // 2.让每一位数字变成个位数字
            }

            // 细节2: 处理溢出
            if (ret > max || ret < min) return 0;
            return (int)ret * sign;
        }
    }

}