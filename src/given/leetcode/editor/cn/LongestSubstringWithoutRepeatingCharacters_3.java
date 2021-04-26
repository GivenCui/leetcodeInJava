package given.leetcode.editor.cn;
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 5375 👎 0


import java.util.ArrayList;
import java.util.Arrays;

// 3 无重复字符的最长子串
public class LongestSubstringWithoutRepeatingCharacters_3 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new LongestSubstringWithoutRepeatingCharacters_3().new Solution();
        // case1
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // 3 abc
        System.out.println(solution.lengthOfLongestSubstring("pwwkew")); // 3 wke
        System.out.println(solution.lengthOfLongestSubstring("GivenCui")); // 7 GivenCu
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 最优解: 双指针 + 哈希  ( 一层循环 )
    // 1. 遍历字符串, 双指针 left, right, 在子串中是否重复用哈希表判断
    //      a. right 处字符 在 table中:
    //          1. table中抹去left对应字符的记录
    //          2. left++
    //      b. right 处字符 不在 table中:
    //          1. right出字符存入 table
    //          2. 统计最长子串长度
    //          3. right++
    //      c. 重复
    // 时间复杂度: O(n)  执行耗时:2 ms,击败了100.00% 的Java用户
    // 空间复杂度: O(1)  内存消耗:38.1 MB,击败了98.78% 的Java用户
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int length;
            if (s == null || (length = s.length()) == 0) return 0;

            // 否重复用哈希表判断
            boolean[] table = new boolean[128];
            int maxLen = 1; // 不是空串至少一个子串
            for (int left = 0, right = 0;  right < length;) {
                if (table[s.charAt(right)]) { // a. right 处字符 在 table中
                    table[s.charAt(left)] = false;
                    left++;
                } else { // b. right 处字符 不在 table中:
                    table[s.charAt(right)] = true;

                    int subLen = right + 1 - left;
                    maxLen = maxLen < subLen ? subLen : maxLen;
//                    System.out.println(s.substring(left, right + 1));

                    right++;
                }
            }

            return maxLen;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    // 方法一: 暴力破解(两层for + 集合存储子串)
    // 1. 生成所有不包含重复字符的子串
    //      a. 所有单子串存入集合
    //      b. 遍历字符串, 外层 start, 内层 end
    //      c. 截取不含重复的子串存入集合
    // 2. 统计最长子串长度
    // 时间复杂度: O(n^3)
    // 空间复杂度: O(n^2)
    // 运行: Time Limit Exceeded
    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            int length;
            if (s == null || (length = s.length()) == 0) return 0;
            // a. 所有单子串存入集合
            ArrayList<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(s.split("")));

            // b. 遍历字符串, 外层 start, 内层 end
            for (int start = 0; start < length; start++) {
                for (int end = start + 1; end < length; end++) {
                    String subStr = s.substring(start, end);  // 截取子串 一次 for
                    if (subStr.indexOf(s.charAt(end)) != -1) {  // 一次 for
                        break;
                    }
                    list.add(subStr + s.charAt(end));
                }
            }
//            System.out.println(list.toString());
            //  2. 统计最长子串长度
            int maxLen = 0;
            for (String s1 : list) {
                maxLen = s1.length() > maxLen ? s1.length() : maxLen;
            }

            return maxLen;
        }
    }
    // 方法二: 暴力破解(两层for + 直接统计maxLength)
    // 1. 生成所有不包含重复字符的子串
    //      a. 遍历字符串, 外层 start, 内层 end
    // 2. 统计最长子串长度
    // 时间复杂度: O(n^3)  执行耗时:224 ms, 击败了6.08% 的Java用户
    // 空间复杂度: O(n^2)  内存消耗:38.7 MB,击败了38.12% 的Java用户
    class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            int length;
            if (s == null || (length = s.length()) == 0) return 0;

            // b. 遍历字符串, 外层 start, 内层 end
            int maxLen = 1;
            for (int start = 0; start < length; start++) {
                for (int end = start + 1; end < length; end++) {
                    String subStr = s.substring(start, end);  // 截取子串 一次 for
                    if (subStr.indexOf(s.charAt(end)) != -1) {  // 一次 for
                        break;
                    }

                    // 2. 统计最长子串长度
                    int subLen = end + 1 - start;
                    maxLen = subLen > maxLen ? subLen : maxLen;
                }
            }

            return maxLen;
        }
    }
    // 方法三: 两次循环 + 双指针 + 哈希
    // 1. 生成所有不包含重复字符的子串a
    //      a. 遍历字符串, 外层 start, 内层 end
    //      b. end在子串中是否重复用哈希表判断
    //      c. 哈希表 用 char 作为key,   hash = (char c) => c   // 'a' -> 97, 128个字符
    // 2. 统计最长子串长度
    // 时间复杂度: O(n^2)  执行耗时:224 ms, 击败了6.08% 的Java用户
    // 空间复杂度: O(1)  内存消耗:38.7 MB,击败了38.12% 的Java用户
    class Solution3 {
        public int lengthOfLongestSubstring(String s) {
            int length;
            if (s == null || (length = s.length()) == 0) return 0;

            // b. 遍历字符串, 外层 start, 内层 end
            int maxLen = 1; // 不是空串至少一个子串
            for (int start = 0; start < length; start++) {
                boolean[] hashTable = new boolean[128];
                hashTable[s.charAt(start)] = true;

                for (int end = start + 1; end < length; end++) {
                    char c = s.charAt(end);
                    if (hashTable[c]) {
                        break;
                    }

                    hashTable[c] = true;
                    // 2. 统计最长子串长度
                    int subLen = end + 1 - start;
                    maxLen = maxLen < subLen ? subLen : maxLen;
                }
            }

            return maxLen;
        }
    }
}