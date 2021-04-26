package given.leetcode.editor.cn;
//给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。 
//
// 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。 
//
// 
//
// 说明: 
//
// 为什么返回数值是整数，但输出的答案是数组呢? 
//
// 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。 
//
// 你可以想象内部操作如下: 
//
// 
//// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//int len = removeDuplicates(nums);
//
//// 在函数里修改输入数组对于调用者是可见的。
//// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
//for (int i = 0; i < len; i++) {
//    print(nums[i]);
//}
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：2, nums = [1,2]
//解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,1,1,1,2,2,3,3,4]
//输出：5, nums = [0,1,2,3,4]
//解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3 * 104 
// -104 <= nums[i] <= 104 
// nums 已按升序排列 
// 
//
// 
// Related Topics 数组 双指针 
// 👍 2029 👎 0


// 26 删除有序数组中的重复项
public class RemoveDuplicatesFromSortedArray_26 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new RemoveDuplicatesFromSortedArray_26().new Solution();
        int[] arr = {1, 1, 2, 3, 4, 4, 4, 5};
        System.out.println(solution.removeDuplicates(arr));
        System.out.println("----- 遍历 -----");
        for (int i : arr) {
            System.out.println(i);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法二: 双指针解法
    class Solution {
        public int removeDuplicates(int[] nums) {
            // 1 1 2 3 4 4 4 5
            int target = 0; // 目标位指针
            for (int i = 1; i < nums.length; i++) { // 移动指针
                if (nums[target] != nums[i] && (++target != i)) { // 优化没重复时的同位置赋值,  && 是短路操作, 相等时后面不会执行
                    nums[target] = nums[i];
                }
            }
            return target + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    // 解法一: 暴力解法
    class Solution1 {
        public int removeDuplicates(int[] nums) {
            // 1 1 2 3 4 4 4 5
            int length = nums.length;
            // 1. 遍历数组
            for (int i = 0; i < length - 1; i++) {
                // 2. i, i+1位置元素相等, 后面所有的前移
                if (nums[i] == nums[i + 1]) {
                    for (int j = i + 1; j < length - 1; j++) {
                        nums[j] = nums[j + 1];
                    }
//                    i--; // >= 3 个重复元素时的case, 要在原地继续判断
                    length--;
                }
            }

            return length;
        }
    }

}