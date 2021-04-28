package given.leetcode.editor.cn;
//给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。 
//
// 示例 1: 
//
// 输入: "eceba"
//输出: 3
//解释: t 是 "ece"，长度为3。
// 
//
// 示例 2: 
//
// 输入: "ccaabbb"
//输出: 5
//解释: t 是 "aabbb"，长度为5。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 113 👎 0


import java.util.Collections;
import java.util.HashMap;

// 159 至多包含两个不同字符的最长子串
public class LongestSubstringWithAtMostTwoDistinctCharacters_159 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new LongestSubstringWithAtMostTwoDistinctCharacters_159().new Solution();
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("eceba")); // 3   t是 "ece"
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("ccaabbb")); // 5   t是 "aabbb"
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("abaccc")); // 4   t是 "accc"

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法一: hashMap + 双指针
    // 1. while(right < len)
    //      right字符存入 hashMap { char: index }
    //      判断if hashMap的size() < 3,  ,  right++, 统计 maxLen
    //      判断else if hashMap的size() == 3, 找到index最小的key和val, left = val+1, 移除该key
    // 2. 返回maxLen
    // 时间: O(n) 执行耗时: 5 ms, 击败了80.23% 的Java用户
    // 空间: O(1) 内存消耗: 38.6 MB,击败了35.24% 的Java用户
    class Solution {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if (s == null) return 0;
            int len = s.length();
            if (len < 3) return len; // 2个字符时, 至多包含两个不同字符的最长子串的长度是本身字符串长度

            HashMap<Character, Integer> map = new HashMap<>();
            int maxLen = 2;
            int left = 0, right = 0;
            while (right < len) {
                map.put(s.charAt(right), right++);

                if (map.size() < 3) {
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
    // 解法二: 用系统提供的api
    // O(N)   执行耗时:17 ms,击败了28.52% 的Java用户
    // O(1)    内存消耗:38.5 MB,击败了49.43% 的Java用户
    class Solution2 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if (s == null) return 0;
            int len = s.length();
            if (len < 3) return len; // 2个字符时, 至多包含两个不同字符的最长子串的长度是本身字符串长度

            HashMap<Character, Integer> map = new HashMap<>();
            int left = 0, right = 0;
            int maxLen = 2;

            while (right < len) {
                if (map.size() < 3) {
                    map.put(s.charAt(right), right++);
//                    System.out.println(s.substring(left, right));
                }
                if(map.size() == 3){
                    int minIndex = Collections.min(map.values());
                    left = minIndex + 1;
                    map.remove(s.charAt(minIndex));
                }

                maxLen = Math.max(maxLen, right - left);
            }

            return maxLen;
        }
    }

    // 失败解法 hashMap + 双指针 + 链表队列( x )  这个优化思路是错误的, case: abaccc, 不满足
    // 1. while(right < len)
    //      right字符存入 hashMap { char: index }
    //      判断如果 hashMap的size() < 3,  ,  right++, 统计 maxLen
    //      判断如果 hashMap的size() == 3
    //         1. 找到index最小的key和val, 借助链表, 模拟队列, O(1)的速度找到第一个字母, 不行, 是找所以最小的字符删除, 而不是最早添加的
    //         2. left = val+1, 移除该key
    // 2. 返回maxLen
    class Solution3 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if (s == null) return 0;
            int len = s.length();
            if (len < 3) return len; // 2个字符时, 至多包含两个不同字符的最长子串的长度是本身字符串长度

            HashMap<Character, Integer> map = new HashMap<>();
            ListNode p, head = new ListNode();
            p = head;
            int maxLen = 2;
            int left = 0, right = 0;
            int prevSize = 0;
            while (right < len) {
                char rightChar = s.charAt(right);
                map.put(rightChar, right++);
                if (prevSize != map.size()) {
                    // 说明有新的key被put
                    p.next = new ListNode(rightChar);
                    p = p.next;
                }
                if (map.size() < 3) {
                    int subLen = right - left;
//                    System.out.println(s.substring(left, right));
                    maxLen = maxLen > subLen ? maxLen : subLen;
                } else {
                    char leftChar = head.next.val;
                    head.next = head.next.next;
                    left = map.get(leftChar) + 1;
                    map.remove(leftChar);
                }
                prevSize = map.size();
            }

            return maxLen;
        }
    }


    // 辅助链表, 模拟队列
    class ListNode {
        char val;
        ListNode next;

        ListNode() {
        }

        ListNode(char x) {
            val = x;
            next = null;
        }

        public void print(ListNode head) {
            String res = "";
            while (head != null) {
                res += head.val + " -> ";

                head = head.next;
            }
            res += "null";
            System.out.println(res);
        }
    }
}