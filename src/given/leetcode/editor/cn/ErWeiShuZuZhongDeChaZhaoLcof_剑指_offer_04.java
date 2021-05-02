package given.leetcode.editor.cn;
//在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个
//整数，判断数组中是否含有该整数。 
//
// 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// 
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
//
// 
//
// 限制： 
//
// 0 <= n <= 1000 
//
// 0 <= m <= 1000 
//
// 
//
// 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/ 
// Related Topics 数组 双指针 
// 👍 318 👎 0


// 剑指 Offer 04 二维数组中的查找
public class ErWeiShuZuZhongDeChaZhaoLcof_剑指_offer_04 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new ErWeiShuZuZhongDeChaZhaoLcof_剑指_offer_04().new Solution();
        // matrix
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                // {3, 6, 9, 16, 22},
                // {10, 13, 14, 17, 24},
                // {18, 21, 23, 26, 30}
        };
        int[][] matrix2 = {
                {1, 4},
                {2, 5},
                {3, 6},
                {10, 13}
        };

        System.out.println(solution.findNumberIn2DArray(matrix, 8)); // true
        System.out.println(solution.findNumberIn2DArray(matrix, 18));
        // case2: matrix注释掉后三行
        System.out.println(solution.findNumberIn2DArray(matrix, 19)); // true
        // case3:
        System.out.println(solution.findNumberIn2DArray(matrix2, 13)); // true
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法二优化写法: 精简重复代码
    // 执行耗时:0 ms,击败了100.00% 的Java用户
    // 内存消耗:44.1 MB,击败了77.63% 的Java用户
    class Solution {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            // 临界条件
            if (matrix == null) return false;
            int m = matrix.length;
            if (m == 0) return false;
            int n = matrix[0].length;
            if (n == 0) return false;

            return findNumberHelp(matrix, target, 0, 0, m - 1, n - 1);
        }

        public boolean findNumberHelp(int[][] matrix, int target, int rowStart, int colStart, int rowEnd, int colEnd) {
            // 终止条件
            if (rowStart > rowEnd || colStart > colEnd) return false;

            // 遍历对角线, 区域划分4分, 能排除两个区域, 递归查找剩余左下和右上两个待查区域
            int diagonalLen = Math.min(rowEnd - rowStart + 1, colEnd - colStart + 1);
            for (int i = 0; i < diagonalLen; i++) {
                if (matrix[rowStart + i][colStart + i] == target) return true; // 当前 cur
                if (i == diagonalLen - 1 || matrix[rowStart + i + 1][colStart + i + 1] > target) { // 如果是最后一个元素 或 下一个元素 精简到一起
                    // 右上区域
                    boolean res1 = findNumberHelp(matrix, target, rowStart, colStart + i + 1, rowStart + i, colEnd);
                    // 左下区域
                    boolean res2 = findNumberHelp(matrix, target, rowStart + i + 1, colStart, rowEnd, colStart + i);
                    return res1 || res2;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    // 解法一: 右上角开始, cur > target ←, cur < target ↓
    // 执行耗时:0 ms,击败了100.00% 的Java用户
    // 内存消耗:44.3 MB,击败了40.89% 的Java用户
    class Solution1 {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            // 临界条件
            if (matrix == null) return false;
            int m = matrix.length;
            if (m == 0) return false;
            int n = matrix[0].length;
            if (n == 0) return false;

            int row = 0, col = n - 1; // 起始位置, 右上角
            while (row < m && col >= 0) {
                int cur = matrix[row][col];
                if (cur == target) return true;
                if (cur < target) { // 当前值 < 目标, 指针下移
                    row++;
                } else { // 当前值 > 目标, 指针左移
                    col--;
                }
            }
            return false;
        }
    }

    // 解法二: 分治思想: 左上到右下对接线查找
    // 执行耗时:0 ms,击败了100.00% 的Java用户
    // 内存消耗:44.1 MB,击败了77.63% 的Java用户
    class Solution2 {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            // 临界条件
            if (matrix == null) return false;
            int m = matrix.length;
            if (m == 0) return false;
            int n = matrix[0].length;
            if (n == 0) return false;

            return findNumberHelp(matrix, target, 0, 0, m - 1, n - 1);
        }

        public boolean findNumberHelp(int[][] matrix, int target, int rowStart, int colStart, int rowEnd, int colEnd) {
            // 终止条件
            if (rowStart > rowEnd || colStart > colEnd) return false;

            // 遍历对角线, 区域划分4分, 能排除两个区域, 递归查找剩余左下和右上两个待查区域
            int diagonalLen = Math.min(rowEnd - rowStart + 1, colEnd - colStart + 1);
            for (int i = 0; i < diagonalLen; i++) {
                int cur = matrix[rowStart + i][colStart + i];
                if (cur == target) return true;
                if (cur > target) {
                    // 右上区域
                    boolean res1 = findNumberHelp(matrix, target, rowStart, colStart + i, rowStart + i - 1, colEnd);
                    // 左下区域
                    boolean res2 = findNumberHelp(matrix, target, rowStart + i, colStart, rowEnd, colStart + i - 1);
                    return res1 || res2;
                } else if (i == diagonalLen - 1){ // 处理对角线遍历到最后一个, 仍是 cur < target 的情况
                    // 右上区域
                    boolean res1 = findNumberHelp(matrix, target, rowStart, colStart + i + 1, rowStart + i, colEnd);
                    // 左下区域
                    boolean res2 = findNumberHelp(matrix, target, rowStart + i + 1, colStart, rowEnd, colStart + i);
                    return res1 || res2;
                }
            }
            return false;
        }
    }
}