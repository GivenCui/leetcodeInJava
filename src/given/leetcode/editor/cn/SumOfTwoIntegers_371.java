package given.leetcode.editor.cn;
//不使用运算符 + 和 - ，计算两整数 a 、b 之和。 
//
// 示例 1: 
//
// 输入: a = 1, b = 2
//输出: 3
// 
//
// 示例 2: 
//
// 输入: a = -2, b = 3
//输出: 1 
// Related Topics 位运算 
// 👍 386 👎 0


// 371 两整数之和
public class SumOfTwoIntegers_371 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new SumOfTwoIntegers_371().new Solution();
//        int[][] cases = {{1,2,3}, {-2,3,1}};
//
//        for (int[] test : cases) {
//            System.out.println(solution.getSum(test[0], test[1]) + " : " + (test[2] ==solution.getSum(test[0], test[1])) );
//        }

        // 循环多少次 ？
        solution.getSum(11, 5);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int getSum(int a, int b) {
//            int r1 = a ^ b;
//            int r2 = (a & b) << 1;
//
//            return r1 + r2  // 又一次用 +， 需要重复1，2步骤

            // 修正1
//           int res = a ^ b;
//           int carry = (a & b) << 1;
//           while(carry != 0) {
//               int tmp = (carry & res) << 1;
//               res = res ^ carry;
//               carry = tmp;
//           }

           // 修正2  a 当成 无进位相加, b 当成 carry
            while(b != 0) {
                System.out.println("a二进制：" + Integer.toBinaryString(a) + "  " + "b二进制：" + Integer.toBinaryString(b));
                System.out.println();

                int tmp = a ^ b; // 无进位相加
                b = (a & b) << 1; // 进位
                a = tmp;
            }
           return a;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}