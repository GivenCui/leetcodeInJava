package given.leetcode.editor.cn;

/**
 * @author : givencui
 * @date: 2021/4/16 - 04 - 16 - 8:00 下午
 * @Description: given.leetcode.editor.cn
 * @version: 1.0
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int x, ListNode nextNode) {
        val = x;
        next = nextNode;
    }

    public static void print(ListNode head) {
        String res = "";
        while(head != null) {
            res += head.val + " -> ";

            head = head.next;
        }
        res += "null";
        System.out.println(res);
    }

    public static  ListNode createNodeList(int[] arr) {
        ListNode head = new ListNode(-1);
        ListNode p = head;

        for (int i : arr) {
            p.next = new ListNode(i);
            p = p.next;
        }

        return head.next;
    }
}