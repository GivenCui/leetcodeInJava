package given.leetcode.editor.cn;
//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串 
// 👍 597 👎 0


// 67 二进制求和
public class AddBinary_67 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new AddBinary_67().new Solution();

        String [][] cases = {
                {"11", "1", "100"},
                {"1010", "1011", "10101"},
                {"0", "0", "0"},
        };

        for (String[] test : cases) {
            String res = solution.addBinary(test[0], test[1]);
            System.out.println(res + ": " + res.equals(test[2]));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 优化解法 用字符数组
         * 1。 创建数组 length = max（s1.length, s2.length） + 1
         * 2。 逆序遍历字符串
         *     charArr 从最后开始遍历
         *     字符 - '0' 转为整数
         *     处理结果及进位
         * 3。 遍历结束， 处理进位
         * 4。 字节数组转为字符串
         *
         * @param a
         * @param b
         * @return
         */
        public String addBinary(String a, String b) {
            int aLen = a.length();
            int bLen = b.length();
            char[] charArr = new char[Math.max(aLen, bLen)];
            int carry = 0;
            for (int i = aLen - 1, j = bLen - 1; i >= 0 || j >= 0; i--, j--) {
                int k = Math.max(i, j);
                int x = i >= 0 ? a.charAt(i) - '0' : 0; // 字符转整数
                int y = j >= 0 ? b.charAt(j) - '0' : 0;

                int sum = x + y + carry;
                carry = sum / 2;
                charArr[k] = (char) (sum % 2 + '0'); // 整数转字符
            }

            String res = String.valueOf(charArr);
            if(carry > 0) {
                res = "1" + res;
            }

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    class Solution1 {
        /**
         * 步骤：
         * 1。 res = ""
         * 2。 逆序遍历字符串， 对应位置相加, 短字符串 为0，res += n ， 处理进位
         * 3。 遍历结束， 处理进位
         * 4。 字节数组转为字符串
         *
         * @param a
         * @param b
         * @return
         */
        public String addBinary(String a, String b) {
            StringBuilder res = new StringBuilder();
            int carry = 0;
            for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
                int x = i >= 0 ? a.charAt(i) - '0' : 0;
                int y = j >= 0 ? b.charAt(j) - '0' : 0;
                int sum = x + y + carry;
                carry = sum / 2;
                res.append(sum % 2);
            }

            res.append(carry > 0 ? "1" : "");

            return res.reverse().toString();
        }
    }

}