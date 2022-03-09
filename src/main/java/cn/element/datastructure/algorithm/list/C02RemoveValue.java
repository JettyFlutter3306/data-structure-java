package cn.element.datastructure.algorithm.list;

import cn.element.datastructure.util.ListNode;
import cn.element.datastructure.util.ListNodes;

public class C02RemoveValue {

    /**
     * 删除链表内指定的所有值
     */
    public static ListNode removeValue(ListNode head, int num) {
        // head来到第一个不需要删除的位置
        while (head != null) {
            if (head.val != num) {
                break;
            }

            head = head.next;
        }

        // 1) head == null
        // 2) head != null
        ListNode pre = head;
        ListNode cur = head;

        while (cur != null) {
            if (cur.val == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }

            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNodes.createListNode(1, 1, 1, 1, 2, 3, 4, 1, 4, 1, 7);
        System.out.println(removeValue(head, 1));
    }

}
