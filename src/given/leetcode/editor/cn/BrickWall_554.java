package given.leetcode.editor.cn;
//你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。 
//
// 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没
//有穿过一块砖的。 
//
// 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的
//砖块数量最少 ，并且返回 穿过的砖块数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：wall = [[1],[1],[1]]
//输出：3
// 
// 
//
// 提示： 
//
// 
// n == wall.length 
// 1 <= n <= 104 
// 1 <= wall[i].length <= 104 
// 1 <= sum(wall[i].length) <= 2 * 104 
// 对于每一行 i ，sum(wall[i]) 应当是相同的 
// 1 <= wall[i][j] <= 231 - 1 
// 
// Related Topics 哈希表 
// 👍 187 👎 0


import java.util.*;

// 554 砖墙
public class BrickWall_554 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new BrickWall_554().new Solution();
        // case1: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]] => 2
        // 初始化 wall
        List<List<Integer>> wall1 = new ArrayList();
        wall1.add(new ArrayList(Arrays.asList(1, 2, 2, 1)));
        wall1.add(new ArrayList(Arrays.asList(3, 1, 2)));
        wall1.add(new ArrayList(Arrays.asList(1, 3, 2)));
        wall1.add(new ArrayList(Arrays.asList(2, 4)));
        wall1.add(new ArrayList(Arrays.asList(3, 1, 2)));
        wall1.add(new ArrayList(Arrays.asList(1, 3, 1, 1)));
        // 测试
        System.out.println(solution.leastBricks(wall1)); // 2

        // case2: [[1],[1],[1]]  => 3
        List<List<Integer>> wall2 = new ArrayList();
        wall2.add(new ArrayList(Arrays.asList(1)));
        wall2.add(new ArrayList(Arrays.asList(1)));
        wall2.add(new ArrayList(Arrays.asList(1)));
        // 测试
        System.out.println(solution.leastBricks(wall2)); // 3

        // case3: [[100000000],[100000000],[100000000]] => 3
        List<List<Integer>> wall3 = new ArrayList();
        wall3.add(new ArrayList(Arrays.asList(1)));
        wall3.add(new ArrayList(Arrays.asList(1)));
        wall3.add(new ArrayList(Arrays.asList(1)));
        // 测试
        System.out.println(solution.leastBricks(wall3)); // 3
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法二: 要求穿过的砖块数量最少，等效于通过的间隙最多
    // 即: wall.size() - gapMax
    class Solution {
        public int leastBricks(List<List<Integer>> wall) {
            int n = wall.size();
            HashMap<Integer, Integer> map = new HashMap<>();
            int sum = 0;

            for (int i = 0; i < n; i++) {
                sum = 0;
                for (Integer el : wall.get(i)) {
                    sum += el;
                    map.put(sum, map.getOrDefault(sum, 0) + 1);

                    // 写法2:
                    // map.put(sum, (map.get(sum) == null ? 0 : map.get(sum) )+ 1);

                    // 写法3:
                    // if(map.containsKey(sum)) {
                    //     map.put(sum, map.get(sum) + 1);
                    // } else {
                    //     map.put(sum, 1);
                    // }
                }
                map.remove(sum); // 最后一个不算
            }


            // 找出最多的gap
            int maxGap = map.values().size() > 0 ? Collections.max(map.values()) : 0;

            return n - maxGap;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
// 解法一: Memory Limit Exceeded, 测试用例 [[100000000],[100000000],[100000000]] ??
    class Solution1 {
        public int leastBricks(List<List<Integer>> wall) {
            if (wall.size() == 0) return 0;
            if (wall.get(0).size() == 0) return 0;

            // 1. 获取墙的宽度
            int width = 0;
            int row1Len = wall.get(0).size();
            for (int i = 0; i < row1Len; i++) {
                width += wall.get(0).get(i);
            }
            // 2. 初始化 gap数组, width - 1
            int[] gap = new int[width > 1 ? width - 1 : 1];  // <-  用 Collections方法, 需要转为 Integer

            for (int i = 0; i < wall.size(); i++) {
                List row = wall.get(i);
                int index = 0;
                for (int j = 0; j < row.size(); j++) {
                    // [1,2,2,1]
                    // [0, 1, 0, 1, 0]
                    if (width == 1) {
                        gap[0] += 1;
                        break;
                    }
                    int cur = (int) row.get(j);
                    while (index < gap.length && cur > 0) {
                        gap[index] += cur > 1 ? 1 : 0;
                        cur--;
                        index++;
                    }
                }
            }

            // 3. 求最小值
            int min = Integer.MAX_VALUE;
            for (int el : gap) {
                min = min > el ? el : min;
            }

            return min;
        }
    }

}