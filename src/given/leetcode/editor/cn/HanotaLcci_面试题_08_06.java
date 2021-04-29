package given.leetcode.editor.cn;
//在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只
//能放在更大的盘子上面)。移动圆盘时受到以下限制: 
//(1) 每次只能移动一个盘子; 
//(2) 盘子只能从柱子顶端滑出移到下一根柱子; 
//(3) 盘子只能叠在比它大的盘子上。 
//
// 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。 
//
// 你需要原地修改栈。 
//
// 示例1: 
//
//  输入：A = [2, 1, 0], B = [], C = []
// 输出：C = [2, 1, 0]
// 
//
// 示例2: 
//
//  输入：A = [1, 0], B = [], C = []
// 输出：C = [1, 0]
// 
//
// 提示: 
//
// 
// A中盘子的数目不大于14个。 
// 
// Related Topics 递归 
// 👍 91 👎 0


import java.util.LinkedList;
import java.util.List;

// 面试题 08.06 汉诺塔问题
public class HanotaLcci_面试题_08_06 {
    public static void main(String[] args) {
        // 测试, 链表默认是队列的特点
        Solution solution = new HanotaLcci_面试题_08_06().new Solution();
        LinkedList<Integer> listA = new LinkedList<Integer>();
        LinkedList<Integer> listB = new LinkedList<Integer>();
        LinkedList<Integer> listC = new LinkedList<Integer>();

        listA.add(2); // offerLast
        listA.add(1);
        listA.add(0);

//        // 2 -> 1 -> 0
        for (Integer val : listA) { // 遍历从链表头遍历到尾
            System.out.println(val);
        }
//
//        // 操作, 链表尾部的值
//        System.out.println(listA.get(listA.size() - 1)); // 0
//        System.out.println(listA.remove(listA.size() - 1)); // 0
//
//        // 2 -> 1
//        for (Integer val : listA) { // 遍历从链表头遍历到尾
//            System.out.println(val);
//        }

        solution.hanota(listA, listB, listC);
        for (Integer val : listC) { // 遍历从链表头遍历到尾
            System.out.println(val);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    // 解法二: 递归
    // 执行用时: 1 ms,  击败 82.69%
    // 内存消耗: 36.5 MB, 击败 94.79%

    class Solution {
        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            int size = A.size();
            movePlate(size, A, B, C);
        }
        public void movePlate(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
            // 结束条件
            if(n == 1) {
                C.add(A.remove(A.size() - 1));
                return;
            }
            // 递归公式: f(n - 1, A, C, B) + M(A, C) + f(n-1, B, A, C)
            movePlate(n - 1, A, C, B); // f(n - 1, A, C, B)
            C.add(A.remove(A.size() - 1)); // M(A, C)
            movePlate(n-1, B, A, C); // f(n-1, B, A, C)
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
    // 解法一: 循环
    //    执行用时: 6 ms, 击败 82.69%
    //    内存消耗: 36.5 MB, 击败 84.79%

    class Solution1 {
        public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
            // 顺序Lists数组, 放三个柱子
            List<Integer>[] lists = new List[3];
//            List<Integer>[] lists = new List<Integer>[3]; // <-- 会报错
            int size = A.size();
            lists[0] = A;
            // 盘子的奇偶性决定B,C的位置
            if (size % 2 == 0) {
                // 偶: A -> B -> C
                lists[1] = B;
                lists[2] = C;
            } else {
                // 奇: A -> C -> B
                lists[1] = C;
                lists[2] = B;
            }

            int currentIndex = 0; // 最小盘所在的柱子的索引
            while (C.size() < size) {
                // 1. 最小盘移动到下一个柱子
                List<Integer> cur = lists[currentIndex % 3];
                List<Integer> next = lists[(currentIndex + 1) % 3];
                List<Integer> prev = lists[(currentIndex + 2) % 3];
                int currentSize = cur.size();
                next.add(cur.remove(--currentSize)); // 最小盘子移动端下一个柱子, next变为最小柱所在

                // 2. 另外两个柱子( prev, cur ), 较小的移动端另一个
                int prevSize = prev.size();
                int prevPlate = prevSize == 0 ? Integer.MAX_VALUE : prev.get(prevSize - 1);
                int curPlate = currentSize == 0 ? Integer.MAX_VALUE : cur.get(currentSize - 1);

                if (prevPlate > curPlate) {
                    // cur -> prev 移动
                    prev.add(cur.remove(--currentSize));
                } else if (prevPlate < curPlate) { // bug1: else{ }, prev和cur什么时候相等?  都不存在, 都为 MAX_VALUE 时相等
                    // prev -> cur 移动
                    cur.add(prev.remove(--prevSize));
                }

                // 重复上两步
                currentIndex++; // next 变成 最小盘子的柱子
            }
        }
    }
}

