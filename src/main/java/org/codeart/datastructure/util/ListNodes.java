package org.codeart.datastructure.util;

/**
 * 构建链表的工具类
 * 调用静态方法构造单链表
 * */
public class ListNodes {

    public static ListNode createListNode(int... value) {
        ListNode head = new ListNode();
        ListNode p = head;

        for (int i : value) {
            p.next = new ListNode(i);
            p = p.next;
        }

        return head.next;
    }
    
    
}
