package given.leetcode.editor.cn;
//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 873 👎 0


import java.util.Stack;

// 155 最小栈
// 思路一: 辅助栈 minStack (借助了Stack)
// 思路二: 一个栈, 元素为数组方法 [cur, min]
// 思路三: 基于链表
// 思路四: 保存差值
// 参考 https://leetcode-cn.com/problems/min-stack/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-38/
public class MinStack_155{
  public static void main(String[] args) {
        // 测试
//        MinStack stack = new MinStack_155().new MinStack();
//        stack.push(2);
//        stack.push(4);
//        stack.push(1);
//        stack.push(3);
//        stack.push(5);
//
//      System.out.println(stack.getMin()); // 1
//      System.out.println(stack.top()); // 5
//
//      stack.pop();
//      stack.pop();
//      stack.pop();
//
//      System.out.println(stack.getMin()); // 2
//      System.out.println(stack.top()); // 4
//
//      stack.pop();
//
//      System.out.println(stack.getMin()); // 2
//      System.out.println(stack.top()); // 2

      // 错误case1
      // ["MinStack","push","push","push","getMin","pop","getMin"]
      //[[],[0],[1],[0],[],[],[]]
//      MinStack stack2 = new MinStack_155().new MinStack();
//      stack2.push(0);
//      stack2.push(1);
//      stack2.push(0);
//      System.out.println(stack2.getMin());
//      stack2.pop();
//      System.out.println(stack2.getMin());

      // 错误case2
//      ["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
//[[],[512],[-1024],[-1024],[512],[],[],[],[],[],[]]
      MinStack stack3 = new MinStack_155().new MinStack();
      stack3.push(512);
      stack3.push(-1024);
      stack3.push(-1024);
      stack3.push(512);
      stack3.pop();
      System.out.println(stack3.getMin()); // -1024
      stack3.pop();
      System.out.println(stack3.getMin()); // -1024
      stack3.pop();
      System.out.println(stack3.getMin()); // 512


  }
  //leetcode submit region begin(Prohibit modification and deletion)
class MinStack {
   Stack<int[]> stack = new Stack<>();
    /** initialize your data structure here. */
    public MinStack() {

    }
    
    public void push(int val) {
        if (stack.empty()) {
            stack.push(new int[]{val, val});
        } else {
            stack.push(new int[]{val, Math.min(val, stack.peek()[1])});
        }
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
       return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.peek()[1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

// 思路一: 辅助栈 minStack (借助了Stack)
class MinStack1 {
    Stack<Integer> stack;
    Stack<Integer> minStack;
    /** initialize your data structure here. */
    public MinStack1() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
        minStack.push(Integer.MAX_VALUE); // <-- 解决边界问题
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(val,minStack.peek()));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek(); // stack.peek() 中处理了边界问题
    }

    public int getMin() {
        return minStack.peek();
    }
}

}
// 思路二: 一个栈, 元素为数组方法 [cur, min]
class MinStack2 {
    Stack<int[]> stack = new Stack<>();
    /** initialize your data structure here. */
    public MinStack2() {

    }

    public void push(int val) {
        if (stack.empty()) {
            stack.push(new int[]{val, val});
        } else {
            stack.push(new int[]{val, Math.min(val, stack.peek()[1])});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}