package given.leetcode.editor.cn;
//给定一个字符串，逐个翻转字符串中的每个单词。 
//
// 说明： 
//
// 
// 无空格字符构成一个 单词 。 
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。 
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。 
// 
//
// 
//
// 示例 1： 
//
// 输入："the sky is blue"
//输出："blue is sky the"
// 
//
// 示例 2： 
//
// 输入："  hello world!  "
//输出："world! hello"
//解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 
//
// 示例 3： 
//
// 输入："a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
// 
//
// 示例 4： 
//
// 输入：s = "  Bob    Loves  Alice   "
//输出："Alice Loves Bob"
// 
//
// 示例 5： 
//
// 输入：s = "Alice does not even like bob"
//输出："bob like even not does Alice"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 包含英文大小写字母、数字和空格 ' ' 
// s 中 至少存在一个 单词 
// 
//
// 
// 
//
// 
//
// 进阶： 
//
// 
// 请尝试使用 O(1) 额外空间复杂度的原地解法。 
// 
// Related Topics 字符串 
// 👍 315 👎 0


import java.util.*;

// 151 翻转字符串里的单词
public class ReverseWordsInAString_151 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new ReverseWordsInAString_151().new Solution();

        System.out.println(solution.reverseWords("the sky is blue").equals("blue is sky the"));
        System.out.println(solution.reverseWords("  hello world!  ").equals("world! hello"));
        System.out.println(solution.reverseWords("a good   example").equals("example good a"));
        System.out.println(solution.reverseWords("  Bob    Loves  Alice   ").equals("Alice Loves Bob"));
        System.out.println(solution.reverseWords("Alice does not even like bob").equals("bob like even not does Alice"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解四: spit(" ") + 逆序遍历
    // 对解一的优化
    // O(n) 执行耗时:6 ms,击败了78.37% 的Java用户
    // O(n) 内存消耗:38.5 MB,击败了65.94% 的Java用户
    class Solution {
        public String reverseWords(String s) {
            if (s == null || "".equals(s = s.trim())) return "";

            String[] strArr = s.split(" ");
            StringBuilder word = new StringBuilder();

            for (int i = strArr.length - 1; i >= 0; i--) {
                if(strArr[i].length() != 0) {
                    word.append(" ").append(strArr[i]);
                }
            }

            return word.substring(1); // 截掉第一个空格
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    // 解法一: split拆分成字符串数组, 然后逆序拼接
    // O(n) 执行耗时:11 ms,击败了35.63% 的Java用户
    // O(n) 内存消耗:38.8 MB,击败了24.79% 的Java用户
    class Solution1 {
        public String reverseWords(String s) {
            if (s == null || "".equals(s = s.trim())) return ""; // 判断边界情况和去除两端空格

            String[] stringArr = s.split("\\s+"); // <-- 正则性能比较低, TODO: java的正则深入学习
            List<String> list = Arrays.asList(stringArr);

            Collections.reverse(list);

            return String.join(" ", list);
        }
    }

    // 解法二(最优解): 字符数组 + 双指针
    // 1. 根据字符串长度创建新字符数组, start, end初始化为 -1
    // 2. 倒序遍历, start和end定位单词开头和结尾
    //    1. end遇到不为空的停止
    //    2. start遇到空格且end不为-1时停止, start + 1是单词起始位置
    //    3. [start, end] 区间正向遍历, 存入字符数组
    //    4. start,end 重置 -1
    // 3. 把字符数组转换为字符串, 注意区间, 字符数组会短, 不是全部转换
    //
    // O(n) 执行耗时:3 ms,击败了96.07% 的Java用户
    // O(n) 内存消耗:38.5 MB,击败了70.89% 的Java用户
    class Solution2 {
        public String reverseWords(String s) {
            if (s == null || "".equals(s = s.trim())) return "";

            int len = s.length(), start = -1, end = -1, index = 0;
            char[] chars = new char[len];

            // 2. 倒序遍历
            for (int i = len - 1; i >= 0; i--) {
                char c = s.charAt(i);
                if (c != ' ') {
                    // 当前字符不是空格时
                    if (end == -1) end = i; // 1. 定位单词末尾
                    if (i == 0) start = i; // 到头了的定位开头修正
                } else if (end != -1) {
                    // 当前字符是空格时, 并且 end 已定位
                    start = i + 1; // 2. 定位单词开头
                }

                // 单词定位成功
                if (start >= 0 && end >= 0) {
                    if (index != 0) chars[index++] = ' '; // 非首尾单词间有空格
                    while (start <= end) { // 3. [start, end] 区间正向遍历, 存入字符数组
                        chars[index++] = s.charAt(start);
                        start++;
                    }
                    start = end = -1; // 4. start,end 重置 -1
                }
            }
//            System.out.println(String.valueOf(chars, 0, index));
            return String.valueOf(chars, 0, index);
        }
    }
    // 解法三: 双端队列 ( 实际是栈 ) + StringBuilder 缓冲区
    // 1. 在双端队列队头依次存入每个单词
    //    1. 正序遍历, 以空格为界限, 将单词存入 StringBuilder 缓冲区
    //    2. 从缓冲区取出单词, 存入队头 ( 注意首尾, 单词之间的多个空格 )
    //    3. 注意正序遍历结束后最后一个单词
    // 2. 从双端队列队头依次取出每个单词
    //    1. String的join方法拼接, 默认双端队列的头部开始遍历 (相当于依次头部出队)
    //
    // O(n) 执行耗时:8 ms,击败了60.26% 的Java用户
    // O(n) 内存消耗:38.6 MB,击败了55.86% 的Java用户
    class Solution3 {
        public String reverseWords(String s) {
            if (s == null || "".equals(s = s.trim())) return "";

            int i = 0, len = s.length();
            StringBuilder wordBuffer = new StringBuilder();
            Deque<String> deque = new ArrayDeque<>();

            // 1. 正序遍历, 以空格为界限, 将单词存入 StringBuilder 缓冲区
            while(i < len) {
                char c = s.charAt(i);
                if (c != ' ') {
                    wordBuffer.append(c); // 将字符存入 StringBuilder 缓冲区
                } else if (wordBuffer.length() != 0) {
                    deque.offerFirst(wordBuffer.toString()); // 从缓冲区取出单词, 存入队头
                    wordBuffer.setLength(0); // 清空缓冲区
                }
                i++;
            }
            // 3. 注意正序遍历结束后最后一个单词
            if(wordBuffer.length() != 0) {
                deque.offerFirst(wordBuffer.toString());
            }

            // String的join方法拼接, 默认双端队列的头部开始遍历 (相当于依次头部出队)
            return String.join(" ", deque);
        }
    }
}