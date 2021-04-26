package given.leetcode.editor.cn;
//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 6006 👎 0

// 2 两数相加
public class AddTwoNumbers_2 {
    public static void main(String[] args) {
        // 测试
        Solution solution = new AddTwoNumbers_2().new Solution();

        // 新建 l1 和 l2 链表
        int[] nums1 = {2, 4};
        int[] nums2 = {5, 6, 4};

        ListNode h1 = new ListNode(-1);
        ListNode p1 = h1;

        for (int n : nums1) {
            p1.next = new ListNode(n);
            p1 = p1.next;
        }
        ListNode l1 = h1.next;  // 第一个链表完成

        ListNode h2 = new ListNode(-1);
        ListNode p2 = h2;

        for (int n : nums2) {
            p2.next = new ListNode(n);
            p2 = p2.next;
        }
        ListNode l2 = h2.next;  // 第一个链表完成

        ListNode l3 = solution.addTwoNumbers(l1, l2);

        // 遍历 l3
        ListNode p3 = l3;
        while(p3 != null) {
            System.out.println(p3.val);
            p3 = p3.next;
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode p1 = l1, p2 = l2; // 两个链表的遍历指针

            ListNode resHead = new ListNode(-1); // 新建链表 头节点
            ListNode r = resHead; // 新链表遍历指针

            int carry = 0; //  进位

            while (p1 != null || p2 != null) { // ｜｜表示以长链表为准
                // 获取当前节点的值:链表较短，已⽆无节点，取0
                int x = p1 != null ? p1.val : 0;
                int y = p2 != null ? p2.val : 0;

                // 2.对应位置的节点数值相加
                int res = x + y + carry;
                carry = res / 10; // 进位

                r.next = new ListNode(res % 10); // 链表中新插入的值
                r = r.next;

//                if (p1 != null) {
//                    p1 = p1.next;
//                }
//                if (p2 != null) {
//                    p2 = p2.next;
//                }
                p1 = p1 == null ? p1 : p1.next;
                p2 = p2 == null ? p2 : p2.next;

            }

            // 处理遍历结束后产生的进位
            if (carry > 0) {
                r.next = new ListNode(carry);
            }

            return resHead.next; // head头节点只是临时节点， 返回 h.next 即可
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}