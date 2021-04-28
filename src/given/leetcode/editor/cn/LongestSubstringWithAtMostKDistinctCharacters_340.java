package given.leetcode.editor.cn;
//给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。 
//
// 示例 1: 
//
// 输入: s = "eceba", k = 2
//输出: 3
//解释: 则 T 为 "ece"，所以长度为 3。 
//
// 示例 2: 
//
// 输入: s = "aa", k = 1
//输出: 2
//解释: 则 T 为 "aa"，所以长度为 2。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 125 👎 0


import java.util.HashMap;

// 340 至多包含 K 个不同字符的最长子串
public class LongestSubstringWithAtMostKDistinctCharacters_340 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new LongestSubstringWithAtMostKDistinctCharacters_340().new Solution();
        System.out.println(solution.lengthOfLongestSubstringKDistinct("eceba", 2)); // 3
        System.out.println(solution.lengthOfLongestSubstringKDistinct("eceba", 3)); // 4
        System.out.println(solution.lengthOfLongestSubstringKDistinct("aa", 1)); // 2
        System.out.println(solution.lengthOfLongestSubstringKDistinct("aa", 5)); // 2
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // O(n) 执行耗时:7 ms,击败了82.32% 的Java用户
    // O(1) 内存消耗:38.9 MB,击败了23.88% 的Java用户
    class Solution {
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            if (s == null) return 0;
            int len = s.length();
            if (len <= k) return len; // 2个字符时, 至多包含两个不同字符的最长子串的长度是本身字符串长度

            HashMap<Character, Integer> map = new HashMap<>();
            int maxLen = k;
            int left = 0, right = 0;
            while (right < len) {
                map.put(s.charAt(right), right++);

                if (map.size() <= k) {
                    int subLen = right - left;
//                    System.out.println(s.substring(left, right));
                    maxLen = maxLen > subLen ? maxLen : subLen;
                } else {
                    int oldestIndex = Integer.MAX_VALUE;
                    for (Integer value : map.values()) {
                        oldestIndex = oldestIndex > value ? value : oldestIndex;
                    }
                    left = oldestIndex + 1;
                    map.remove(s.charAt(oldestIndex));
                }
            }

            return maxLen;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}