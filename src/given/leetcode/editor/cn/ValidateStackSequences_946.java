package given.leetcode.editor.cn;
//给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时
//，返回 true；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
//输出：true
//解释：我们可以按以下顺序执行：
//push(1), push(2), push(3), push(4), pop() -> 4,
//push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
// 
//
// 示例 2： 
//
// 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
//输出：false
//解释：1 不能在 2 之前弹出。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= pushed.length == popped.length <= 1000 
// 0 <= pushed[i], popped[i] < 1000 
// pushed 是 popped 的排列。 
// 
// Related Topics 栈 
// 👍 173 👎 0


import java.util.Stack;

// 946 验证栈序列
public class ValidateStackSequences_946{
  public static void main(String[] args) {
        // 测试
        Solution solution = new ValidateStackSequences_946().new Solution();

        int[][][] cases = {
                {
                        {1,2,3,4,5},
                        {4,5,3,2,1}
                },
                {
                        {1,2,3,4,5},
                        {1,2,3,4,5}
                },
                {
                        {1,2,3,4,5},
                        {4,3,5,2,1}
                },
                {
                        {1,0},
                        {1,0}
                }
        };

      for (int[][] test : cases) {
          System.out.println(solution.validateStackSequences(test[0], test[1]));
      }
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0 && popped.length ==0) return true;
        if (pushed.length == 0 || popped.length == 0) return false;

        Stack<Integer> stack = new Stack<>();

        int i = 0;
        int j = 0;

        while(i < pushed.length) {
            if (stack.size() > 0 && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            } else {
                stack.push(pushed[i]);
                i++;
            }
        }

        while(stack.size() > 0 && j < popped.length) {
            if (stack.peek() != popped[j]) {
                break;
            }
            stack.pop();
            j++;
        }

        return stack.size() == 0 && j == popped.length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}