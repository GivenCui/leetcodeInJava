package given.leetcode.editor.cn;
//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 示例： 
//
// 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
//输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"] 
//
// 注意： 
//
// 
// 单词的定义是不包含空格的一系列字符 
// 输入字符串中不会包含前置或尾随的空格 
// 单词与单词之间永远是以单个空格隔开的 
// 
//
// 进阶：使用 O(1) 额外空间复杂度的原地解法。 
// Related Topics 字符串 
// 👍 54 👎 0


// 186 翻转字符串里的单词 II
public class ReverseWordsInAStringIi_186 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new ReverseWordsInAStringIi_186().new Solution();
        char[] chars = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        solution.reverseWords(chars);

        for (char c : chars) {
            System.out.print(c);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 原地解法:
    // 参考: https://leetcode-cn.com/problems/reverse-words-in-a-string-ii/solution/java-liang-ci-fan-zhuan-by-npe_tle/
    class Solution {
        // 双指针逆序
        private void reverse(char[] s, int start, int end) {
            while (start < end) {
                char tmp = s[start];
                s[start] = s[end];
                s[end] = tmp;
                start++;
                end--;
            }
        }

        public void reverseWords(char[] s) {
            // 两次翻转即可，第一次全局翻转，第二次翻转各个单词
            int len = s.length;
            reverse(s, 0, len - 1);

            int start = 0;
            for (int i = 0; i < len; i++) {
                if (s[i] == ' ') {
                    // 翻转前面的单词
                    reverse(s, start, i - 1);
                    start = i + 1;
                }
            }

            // 翻转最后一个单词
            reverse(s, start, len - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}