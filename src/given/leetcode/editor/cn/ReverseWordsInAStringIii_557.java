package given.leetcode.editor.cn;
//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 字符串 
// 👍 288 👎 0


// 557 反转字符串中的单词 III
public class ReverseWordsInAStringIii_557 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new ReverseWordsInAStringIii_557().new Solution();
        System.out.println(solution.reverseWords("Let's take LeetCode contest").equals("s'teL ekat edoCteeL tsetnoc"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法一:
    // 执行耗时:5 ms,击败了86.09% 的Java用户
    // 内存消耗:39.3 MB,击败了21.99% 的Java用户
    class Solution {
        public String reverseWords(String s) {
            if(s == null || "".equals((s = s.trim()))) return "";

            String[] strArr = s.split(" ");
            StringBuilder word = new StringBuilder();

            for (String s1 : strArr) {
                word.append(" ").append(new StringBuilder(s1).reverse());
            }
            return word.substring(1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}