package given.leetcode.editor.cn;
//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[4,5,1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：head = [0,1,2], k = 4
//输出：[2,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 500] 内 
// -100 <= Node.val <= 100 
// 0 <= k <= 2 * 109 
// 
// Related Topics 链表 双指针 
// 👍 544 👎 0


// 61 旋转链表
public class RotateList_61 {
  public static void main(String[] args) {
        // 测试
        Solution solution = new RotateList_61().new Solution();
        ListNode l1 = new ListNode(1);
        ListNode p = l1;
        p = p.next = new ListNode(2);
        p = p.next = new ListNode(3);
        p = p.next = new ListNode(4);
        p.next = new ListNode(5);

        ListNode.print(l1);

        p = solution.rotateRight(l1, 7); // 7 % 5 = 2
        ListNode.print(p);

  }
  //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;

        // 慢指针 p, 快指针q
        ListNode p = head, q = head;
        // 计算链表中节点个数
        int count = 0;
        while(p != null) {
            count++;
            p = p.next;
        }
        p = head;
        k = k % count;

        // 快指针先走 k 步
        while(k-- != 0) {
            q = q.next;
        }

        // 快慢指针同时移动, 直到快指针在尾结点
        while(q.next != null) {
            p = p.next;
            q = q.next;
        }

        q.next = head; // 快指针在尾结点, next -> head, 形成环
        head = p.next; // 慢指针的next是待返回的新head
        p.next = null; // 重置慢指针 p.next = null
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}