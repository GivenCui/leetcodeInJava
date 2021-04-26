package given.leetcode.editor.cn;
//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，po
//s 仅仅是用于标识环的情况，并不会作为参数传递到函数中。 
//
// 说明：不允许修改给定的链表。 
//
// 进阶： 
//
// 
// 你是否可以使用 O(1) 空间解决此题？ 
// 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：返回索引为 1 的链表节点
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：返回索引为 0 的链表节点
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：返回 null
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围在范围 [0, 104] 内 
// -105 <= Node.val <= 105 
// pos 的值为 -1 或者链表中的一个有效索引 
// 
// Related Topics 链表 双指针 
// 👍 963 👎 0


// 142 环形链表 II
public class LinkedListCycleIi_142 {
  public static void main(String[] args) {
      // 测试
      LinkedListCycleIi_142.Solution solution = new LinkedListCycleIi_142().new Solution();
      // case1: head = [3,2,0,-4], pos = 1  输出 true
      ListNode l1 = new ListNode(3);
      ListNode p = l1;
      ListNode ringPoint = null;
      ringPoint = p = p.next = new ListNode(2);
      p = p.next = new ListNode(0);
      p = p.next = new ListNode(-4);
      p.next = ringPoint;

      System.out.println(solution.detectCycle(l1) == ringPoint); // true

      // case2: head = [1,2], pos = 0  输出 true
      ListNode l2 = new ListNode(1);
      p = l2;
      p = p.next = new ListNode(2);
      p.next = l2;
      System.out.println(solution.detectCycle(l2) == l2); // true

      // case3: head = [1], pos = -1  输出 false
      ListNode l3 = new ListNode(1);
      System.out.println(solution.detectCycle(l3)); // false
  }
  //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                slow = head;
                break;
            }
        }
        if (fast == null || fast.next == null) return null;

        // 相遇后, slow从头走, fast改为一步一步的走, 相遇即为五环入口
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}