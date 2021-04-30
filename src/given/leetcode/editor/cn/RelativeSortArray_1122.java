package given.leetcode.editor.cn;
//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组 
// 👍 170 👎 0


import java.util.Arrays;
import java.util.HashMap;

// 1122 数组的相对排序
public class RelativeSortArray_1122 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new RelativeSortArray_1122().new Solution();
        int[] arr1 = new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = new int[]{2, 1, 4, 3, 9, 6};

        int[] res = solution.relativeSortArray(arr1, arr2);

        System.out.println(Arrays.toString(res));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法二: 统计计数, 0 <= arr1[i], arr2[i] <= 1000
    // 执行耗时:0 ms,击败了100.00% 的Java用户
    // 内存消耗:38.5 MB,击败了66.13% 的Java用户
    class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            // 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
            // 输出：[2,2,2,1,4,3,3,9,6,7,19]
            // 1. 遍历arr1, 找出最大值 max, 创建 frequency[max+1]
            int max = 0;
            for (int el : arr1) {
                max = max > el ? max : el;
            }
            int[] frequency = new int[max + 1];
            // 2.遍历 arr1, 统计出现的个数
            for (int el : arr1) {
                frequency[el]++;
            }
            // 新建 ans 结果数组, index = 0
            int[] ans = new int[arr1.length];
            int index = 0;
            // 3. 遍历 arr2, 存入 res, 并重置对应值为0
            for (int el : arr2) {
                for(int i = 0; i < frequency[el];i++) {
                    ans[index++] = el;
                }
                frequency[el] = 0; // 重置对应值为0
            }
            // 4. 根据 arr1.length 或 0 ~ max, 遍历, 存入剩余元素, 根据数组索引, 自然是排序的
            for (int x = 0; x <= max; x++) {
                for(int i = 0; i < frequency[x]; i++) {
                    ans[index++] = x;
                }
            }
            // 5. 插入剩余元素
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
    // 解法一:
    // 执行耗时:2 ms,击败了52.53% 的Java用户
    // 内存消耗:38.6 MB,击败了55.18% 的Java用户
    class Solution1 {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            // 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
            // 输出：[2,2,2,1,4,3,3,9,6,7,19]

            HashMap<Integer, Integer> hashMap = new HashMap();
            int len1 = arr1.length;
            int len2 = arr2.length;
            int[] res = new int[len1];
            int index = 0;

            // 1.遍历 arr1, 统计出现的个数 ( 因为arr2不重复)
            for (int i : arr1) {
                if(!hashMap.containsKey(i)) {
                    hashMap.put(i, 1);
                } else {
                    hashMap.put(i, hashMap.get(i) + 1);
                }
            }

            // 2. 遍历 arr2, 存入 res, 并remove 对应的 hashMap中的key
            for (int el : arr2) {
                int count = hashMap.get(el);
                while(count > 0) {
                    res[index++] = el;
                    count--;
                }
                hashMap.remove(el);
            }

            // 3. 剩余 keys 排序
            int[] remain = new int[hashMap.size()];
            int i = 0;
            for (Integer val : hashMap.keySet()) {
                remain[i++] = val;
            }

            Arrays.sort(remain); // 排序

            // 4. 插入剩余元素
            for (int el : remain) {
                int count = hashMap.get(el);
                while(count > 0) {
                    res[index++] = el;
                    count--;
                }
            }
            return res;
        }
    }
}