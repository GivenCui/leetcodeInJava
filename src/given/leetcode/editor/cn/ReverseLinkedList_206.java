package given.leetcode.editor.cn;
//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表 
// 👍 1691 👎 0


import org.w3c.dom.NodeList;

// 206 反转链表
public class ReverseLinkedList_206 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new ReverseLinkedList_206().new Solution();
        ListNode l1 = new ListNode(1);
        ListNode p = l1;
        p = p.next = new ListNode(2);
        p = p.next = new ListNode(3);
        p = p.next = new ListNode(4);

        ListNode reverseP = solution.reverseList(l1);

        while(reverseP != null) {
            System.out.println(reverseP.val);
            reverseP = reverseP.next;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode next;
            while (head != null) {
                next = head.next;
                head.next = pre;

                pre = head;
                head = next;
            }
            return pre;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}