package cn.element.datastructure.algorithm.list;

import cn.element.datastructure.util.DoubleNode;
import cn.element.datastructure.util.ListNode;
import cn.element.datastructure.util.ListNodes;

public class ReverseList {

    /**
     * 反转单链表
     * 编程小技巧: 处理有关链表类的问题,通常定义两个链表引用
     */
    public static ListNode reverseLinkedList(ListNode head) {
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

    /**
     * 反转双链表
     */
    public static DoubleNode reverseLinkedList(DoubleNode head) {
        DoubleNode prev = null;
        DoubleNode next;
        
        while (head != null) {
            next = head.next;
            head.next = prev;
            head.prev= next;
            prev = head;
            head = next;
        }
        
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = ListNodes.createListNode(1, 2, 3, 4, 5);
        System.out.println(reverseLinkedList(head));
    }
    
    

}
