package com.given.test;

/**
 * @author : givencui
 * @date: 2021/4/11 - 04 - 11 - 11:02 上午
 * @Description:
 * 1. 两数相加 https://leetcode-cn.com/problems/add-two-numbers/
 * 2. 幕布: https://mubu.com/app/edit/home/3LYV5pgrYS#o-1NWtCQoEkM
 * @version: 1.0
 */
public class ListNodePractice {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};

        // 创建头节点 (辅助)
        ListNode head = new ListNode(-1);
        ListNode p = head; // 用于遍历的指针

        // 新建链表
        // 尾插法
        System.out.println("----------- 新建链表(尾插法) -----------");
        for (int num : nums) {
            // 新建结点
            ListNode node = new ListNode(num);

            p.next = node;
            p = p.next;
        }

        // 头插法
//        System.out.println("----------- 新建链表(头插法) -----------");
//        for (int num : nums) {
//            ListNode node = new ListNode(num);
//
//            node.next = head.next;
//            head.next = node;
//        }


        ListNode l1 = head.next;  // 对外暴露的链表 (不包含头) 此处灵活处理

        // 遍历链表
        ListNode p1 = l1;

        while(p1 != null) {
            System.out.println(p1.val);
            p1 = p1.next;
        }

        // 反转链表
        System.out.println("----------- 反转链表 -----------");
        ListNode p2 = l1;
        ListNode pre = null; // 尾部

        while(p2 != null) {
            ListNode next = p2.next; // 暂存next
            p2.next = pre;

            pre = p2;
            p2 = next;
        }

        // 遍历结束后, 反转链表第一个节点为 pre
        // 遍历验证
        ListNode p3 = pre;

        while(p3 != null) {
            System.out.println(p3.val);
            p3 = p3.next;
        }
    }
}

// 链表节点类
class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }
}