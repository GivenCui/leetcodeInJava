package given.leetcode.editor.cn;
//特殊的二进制序列是具有以下两个性质的二进制序列： 
//
// 
// 0 的数量与 1 的数量相等。 
// 二进制序列的每一个前缀码中 1 的数量要大于等于 0 的数量。 
// 
//
// 给定一个特殊的二进制序列 S，以字符串形式表示。定义一个操作 为首先选择 S 的两个连续且非空的特殊的子串，然后将它们交换。（两个子串为连续的当且仅当第一
//个子串的最后一个字符恰好为第二个子串的第一个字符的前一个字符。) 
//
// 在任意次数的操作之后，交换后的字符串按照字典序排列的最大的结果是什么？ 
//
// 示例 1: 
//
// 
//输入: S = "11011000"
//输出: "11100100"
//解释:
//将子串 "10" （在S[1]出现） 和 "1100" （在S[3]出现）进行交换。
//这是在进行若干次操作后按字典序排列最大的结果。
// 
//
// 说明: 
//
// 
// S 的长度不超过 50。 
// S 保证为一个满足上述定义的特殊 的二进制序列。 
// 
// Related Topics 递归 字符串 
// 👍 60 👎 0


import java.util.Arrays;

// 761 特殊的二进制序列
public class SpecialBinaryString_761 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new SpecialBinaryString_761().new Solution();
        System.out.println(solution.makeLargestSpecial("11011000").equals("11100100"));
        System.out.println(solution.makeLargestSpecial("101100101100").equals("110011001010"));
        System.out.println(solution.makeLargestSpecial("11010110110000").equals("11110010010100"));

        // String[] test1 = new String[]{"10", "1100","10", "1100"};
        // solution.bubbleSort(test1, 4);
        // System.out.println(Arrays.toString(test1));
        // System.out.println("10".compareTo("1100")); // -1
        // System.out.println("1100".compareTo("10")); // 1
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法一:
    // 执行耗时:2 ms,击败了93.33% 的Java用户
    // 内存消耗:36.6 MB,击败了95.83% 的Java用户
    class Solution {
        public String makeLargestSpecial(String S) {
            // 结束条件
            int len;
            if ((len = S.length()) <= 1) return S;

            StringBuilder res = new StringBuilder();
            String[] stringArr = new String[25];
            int index = 0;
            int countOne = 0;
            int start = 0;
            // 找出特殊子串, 并递归的筛选, 字典排序, 拼接
            for (int end = 0; end < len; end++) {
                countOne += (S.charAt(end) == '1' ? 1 : -1);
                if (countOne == 0) {
                    String sub = S.substring(start + 1, end); // 去掉头1和尾部的0, 后再判断是否是特殊子串
                    sub = sub.length() > 1 ? makeLargestSpecial(sub) : sub; // <-- 递归调用

                    stringArr[index++] = "1" + sub + "0";
                    start = end + 1; // bug1,  写到了if外面
                }
            }

            // 按字典从小到到大排序
            bubbleSort(stringArr, index);

            // 逆序取出拼接到 stringBuilder
            for (int j = index - 1; j >= 0; j--) {
                res.append(stringArr[j]);
            }

            return res.toString();
        }

        public void bubbleSort(String[] A, int n) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) { // bug2: 复制后, i 没有都改为 j, tips 别复制代码
                    if (A[j].compareTo(A[j + 1]) > 0) {
                        String tmp = A[j + 1];
                        A[j + 1] = A[j];
                        A[j] = tmp;
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}