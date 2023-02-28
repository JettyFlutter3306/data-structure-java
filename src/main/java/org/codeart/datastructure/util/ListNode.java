package org.codeart.datastructure.util;

import java.util.ArrayList;
import java.util.List;

public class ListNode {

    public int val;

    public ListNode next;

    public ListNode() {
        
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();

        for (ListNode p = this; p != null ; p = p.next) {
            list.add(p.val);
        }

        return list.toString();
    }
}
