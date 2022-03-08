package cn.element.datastructure.util;

import java.util.ArrayList;
import java.util.List;

public class DoubleNode {
    
    public int value;
    
    public DoubleNode prev;
    
    public DoubleNode next;

    public DoubleNode(int value) {
        this.value = value;
    }

    public DoubleNode(int value, DoubleNode prev, DoubleNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();

        for (DoubleNode p = this; p != null ; p = p.next) {
            list.add(p.value);
        }

        return list.toString();
    }
}
