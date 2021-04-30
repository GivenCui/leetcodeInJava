package given.leetcode.editor.cn;
//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则： 
//
// 
// 任何左括号 ( 必须有相应的右括号 )。 
// 任何右括号 ) 必须有相应的左括号 ( 。 
// 左括号 ( 必须在对应的右括号之前 )。 
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 示例 1: 
//
// 
//输入: "()"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "(*)"
//输出: True
// 
//
// 示例 3: 
//
// 
//输入: "(*))"
//输出: True
// 
//
// 注意: 
//
// 
// 字符串大小将在 [1，100] 范围内。 
// 
// Related Topics 字符串 
// 👍 236 👎 0


import java.util.Stack;

// 678 有效的括号字符串
public class ValidParenthesisString_678 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new ValidParenthesisString_678().new Solution();
        System.out.println(solution.checkValidString("()")); // true
        System.out.println(solution.checkValidString("(*)")); // true
        System.out.println(solution.checkValidString("(*))")); // true
        System.out.println(solution.checkValidString("())*")); // false
        System.out.println(solution.checkValidString("**((")); // false
        System.out.println(solution.checkValidString("*(*(")); // false
        System.out.println(solution.checkValidString("*())")); // true
        System.out.println(solution.checkValidString("(*()")); // true
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法四: 两个栈 (容易理解)
    // 执行耗时:1 ms,击败了58.55% 的Java用户
    // 内存消耗:36.6 MB,击败了24.62% 的Java用户
    class Solution4 {
        public boolean checkValidString(String s) {
            int len = s.length();
            Stack<Integer> leftStack = new Stack<>();
            Stack<Integer> starStack = new Stack<>();

            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    leftStack.push(i);
                } else if (c == '*') {
                    starStack.push(i);
                } else {
                    if (!leftStack.isEmpty()) leftStack.pop();
                    else if (!starStack.isEmpty()) starStack.pop();
                    else return false;
                }
            }

            if (leftStack.isEmpty()) return true;
            if (leftStack.size() > starStack.size()) return false;

            // 数量 "*" >= "("
            while (!leftStack.isEmpty()) {
                if (leftStack.peek() > starStack.peek()) return false; // "*("
                leftStack.pop();
                starStack.pop();
            }

            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    // 解法一:  遇到"(" +1, 遇到")" -1, 追求整体的平衡
    // 整体为负时, 需要立即修正
    // 修正后仍为负, return false
    // 难点: * 对于 (
    // 1. "**((" *号不可以抵消后面的 (
    // 2. "(*()" *号可以抵消之前的 (
    // 执行耗时:0 ms,击败了100.00% 的Java用户
    // 内存消耗:36.4 MB,击败了61.18% 的Java用户
    class Solution {
        public boolean checkValidString(String s) {
            // 遇到"(" +1, 遇到")" -1, 总体最求平衡
            int count = 0;

            // 星号数
            int starCount = 0;
            // 修正的可用于抵消之前 ( 的 "*" 数量
            int leftStarCount = 0;

            int len = s.length();
            for (int i = 0; i < len; i++) {
                char ch = s.charAt(i);
                // 遇到"(" +1
                if (ch == '(') {
                    count++;
                } else if (ch == ')') {
                    // 遇到")" -1
                    count--;
                    if (count < 0) {
                        // 整体为负时, 需要立即修正
                        if(starCount == 0) return false;
                        starCount--;
                        count = 0;
                    }
                } else if (ch == '*') {
                    starCount++;
                    leftStarCount++;
                }

                leftStarCount = Math.min(leftStarCount, starCount);
                leftStarCount = Math.min(leftStarCount, count); // <-- 关键: 根据 "**(("  和 "(*()" 理解
            }
            return leftStarCount >= count; // 修正的可用于抵消之前 ( 的 "*" 数量 与 "(" 的数量是否能抵消
        }
    }

    // 解法二:  大神解法
    // ?
    class Solution2 {
        public boolean checkValidString(String s) {
            int left = 0, right = 0;
            int len = s.length();

            for (int i = 0; i < len; i++) {
                left += (s.charAt(i) == ')') ? -1 : 1;
                right += (s.charAt(len - i - 1) == '(') ? -1 : 1;
                if (left < 0 || right < 0) return false;
            }
            return true;
        }
    }

    // 解法三: 贪心策略
    // l和r表示分别表示「可能多余的左括号」的下界和上界，很直观。执行起来就是:
    //
    //         1. 遇到左括号：l++, r++；
    //         2. 遇到星号：l--, r++（因为星号有三种情况）；
    //         3. 遇到右括号：l--, r--。
    //
    // 在判断的过程中，l要保持不小于0。
    // 另外，如果r < 0，说明把星号全变成左括号也不够了，false。
    // 最后，如果l > 0，说明末尾有多余的左括号，false。
    class Solution3 {
        public boolean checkValidString(String s) {
            char[] cc = s.toCharArray();
            //l、r分别为待匹配左括号数目的上下界
            int l = 0, r = 0;
            for (char c : cc) {
                if (c == '(') {
                    l++;
                    r++;
                } else if (c == ')') {
                    l = Math.max(0, l - 1);
                    r--;
                    if (r < 0) return false;
                } else {
                    l = Math.max(0, l - 1);
                    r++;
                }
            }
            return l == 0;
        }
    }
}