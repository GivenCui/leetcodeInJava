package given.leetcode.editor.cn;
//有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"
//，"(())()" 和 "(()(()))" 都是有效的括号字符串。 
//
// 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。 
//
// 给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。 
//
// 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。 
//
// 
//
// 示例 1： 
//
// 输入："(()())(())"
//输出："()()()"
//解释：
//输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
//删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。 
//
// 示例 2： 
//
// 输入："(()())(())(()(()))"
//输出："()()()()(())"
//解释：
//输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
//删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
// 
//
// 示例 3： 
//
// 输入："()()"
//输出：""
//解释：
//输入字符串为 "()()"，原语化分解得到 "()" + "()"，
//删除每个部分中的最外层括号后得到 "" + "" = ""。
// 
//
// 
//
// 提示： 
//
// 
// S.length <= 10000 
// S[i] 为 "(" 或 ")" 
// S 是一个有效括号字符串 
// 
// Related Topics 栈 
// 👍 168 👎 0


import java.util.Stack;

// 1021 删除最外层的括号
public class RemoveOutermostParentheses_1021 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new RemoveOutermostParentheses_1021().new Solution();
        String[][] cases = {
                {"(()())(())", "()()()"},
                {"(()())(())(()(()))", "()()()()(())"},
                {"()()", ""},
        };

        for (int i = 0; i < cases.length; i++) {
            String[] test = cases[i];

            System.out.println("测试用例" + (i + 1) + test[0] + " : " + solution.removeOuterParentheses(test[0]).equals(test[1]));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 题目规定只有(), 所以可以只用一个计数器 (正负抵消), 为0时找到一个括号原语
    // 或 用栈, 左入栈, 右出栈, 栈为空时, 找到一个括号原语
    class Solution {
        public String removeOuterParentheses(String S) {
            StringBuilder res = new StringBuilder();
            Stack<Character> stack = new Stack<>();
            int p = 0;
            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')' && !stack.isEmpty()) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    res.append(S.substring(p + 1, i)); // 截取时[x, y)
                    p = i + 1;
                }
            }

            return res.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    //  方法一: 统计左右括号, 数量相等时匹配到一个括号原语
    // O(n) | O(n)
    class Solution1 {
        public String removeOuterParentheses(String S) {
//        String res = "";
            StringBuilder res = new StringBuilder();
            int lCount = 0, rCount = 0, p = 0;

            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);

                if (c == '(') {
                    lCount++;
                } else if (c == ')') {
                    rCount++;
                }
                if (lCount == rCount) {
//                res += S.substring(p + 1, i); // 截取时去掉了头和尾
                    res.append(S.substring(p + 1, i));
                    p = i + 1;
                }
            }
            return res.toString();
        }
    }
}