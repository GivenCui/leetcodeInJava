package given.leetcode.editor.cn;
//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。 
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,1,2]
//输出：[1,2]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序排列 
// 
// Related Topics 链表 
// 👍 567 👎 0


// 83 删除排序链表中的重复元素
public class RemoveDuplicatesFromSortedList_83 {
  public static void main(String[] args) {
        // 测试
        Solution solution = new RemoveDuplicatesFromSortedList_83().new Solution();
        ListNode l1 = ListNode.createNodeList(new int[]{ 1, 1, 2, 3, 3, 3, 4});
        ListNode.print(l1);

        ListNode newL1 = solution.deleteDuplicates(l1);

        ListNode.print(newL1);

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
    // 为有序链表量身打造的解法
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head;

        while(p != null && p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }

        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = head, q = head;

        while(p != null) { // 这里用 p (最后 p是最后节点, q 是 null)
            // 处理末尾是重复元素的case
            if (q == null) {
                p.next = null;
                break;
            }
            if (p.val != q.val) {
                p.next = q;
                p = p.next;
            }
            q = q.next;
        }

        return head;
    }
}
}