package given.leetcode.editor.cn;
//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。 
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 
//输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出："1"
// 
//
// 示例 4： 
//
// 
//输入：nums = [10]
//输出："10"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 109 
// 
// Related Topics 排序 
// 👍 698 👎 0


// 179 最大数
public class LargestNumber_179 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new LargestNumber_179().new Solution();
        System.out.println(solution.largestNumber(new int[]{2, 10}));
        System.out.println(solution.largestNumber(new int[]{4, 45}));
        // System.out.println(solution.largestNumber(new int[]{2, 10, 31, 3, 45, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法一: "x" + "y" 和 "y" + "x" 比较 + 快排
    //
    // https://leetcode-cn.com/problems/largest-number/solution/chao-guo-100-java-shou-xie-kuai-pai-by-w-gb2w/
    // "00" 这样的全零字符串。解决办法是可以提前判断 nums 是否全部为零
    // 执行耗时:4 ms,击败了96.14% 的Java用户
    // 内存消耗:38 MB,击败了46.67% 的Java用户
    class Solution {
        public String largestNumber(int[] nums) {
            int len = nums.length;
            String[] array = new String[len];
            for (int i = 0; i < len; i++) {
                array[i] = String.valueOf(nums[i]);
            }

            quickSort(array, 0, len - 1);
            if (array[0].equals("0")) return "0";

            StringBuilder ans = new StringBuilder();
            for (String str : array) {
                ans.append(str);
            }

            return ans.toString();
        }

        private void quickSort(String[] nums, int start, int end) {
            if (start >= end) {
                return;
            }
            String pivot = nums[start];
            int index = start;
            for (int i = start + 1; i <= end; i++) {
                if ((nums[i] + pivot).compareTo(pivot + nums[i]) > 0) {
                    index += 1;
                    swap(nums, index, i);
                }
            }
            swap(nums, index, start);
            quickSort(nums, start, index - 1);
            quickSort(nums, index + 1, end);
        }

        private void swap(String[] nums, int l, int r) {
            String temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}