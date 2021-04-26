package given.leetcode.editor.cn;
//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。 
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
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
// 👍 607 👎 0


// 82 删除排序链表中的重复元素 II
public class RemoveDuplicatesFromSortedListIi_82 {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicatesFromSortedListIi_82().new Solution();
        // case
        ListNode l1 = ListNode.createNodeList(new int[]{1, 2, 3, 3, 4, 4, 5});
        ListNode l2 = ListNode.createNodeList(new int[]{1, 1, 1, 2, 3});
        ListNode l3 = ListNode.createNodeList(new int[]{1, 1});

        // 测试
        ListNode test = l3;
        ListNode.print(test);
        ListNode newL1 = solution.deleteDuplicates(test);
        ListNode.print(newL1);
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
        public ListNode deleteDuplicates(ListNode head) {
            ListNode newHead = new ListNode(-1, head);
            ListNode prev = newHead, p = head;

            while (p != null && p.next != null) {
                if (p.val == p.next.val) {
                    while (p.next != null && p.val == p.next.val) {
                        p.next = p.next.next;
                        prev.next = p.next;
                    }
                    p = prev.next;
                    continue; // 不写会越界
                }
                prev = prev.next;
                p = p.next;
            }

            return newHead.next;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    class Solution1 {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode newHead = new ListNode(-1, head);
            ListNode prev = newHead, p = head;

            while (p != null && p.next != null) {
                if (p.val == p.next.val) {
                    p.next = p.next.next;
                    prev.next = p.next;
                    continue;
                }

                if (p.next != prev.next) {
                    prev = prev.next;
                }
                p = p.next;

            }
            return newHead.next;
        }
    }
}