package cn.element.datastructure.list;

import cn.element.datastructure.list.node.DoubleNode;

public class SortedCirDoublyList<T extends Comparable<? super T>> extends CirDoublyList<T> {

    public SortedCirDoublyList() {
        super();
    }

    @Override
    public DoubleNode<T> insert(int i, T x) {
        throw new UnsupportedOperationException("insert(i,x)");
    }

    @Override
    public DoubleNode<T> insert(T x) {
        if (x == null) {
            throw new NullPointerException("x == null"); //抛出空指针异常
        }

        DoubleNode<T> q = null;

        if (this.isEmpty()) {
            q = new DoubleNode<>(x,this.head,this.head);

            this.head.next = q;
            this.head.prev = q;

            return null;
        }

        DoubleNode<T> p = this.head.next;

        while (p != this.head && x.compareTo(p.data) > 0) {
            p = p.next;
        }

        q = new DoubleNode<>(x, p.prev, p);

        p.prev.next = q;
        p.prev = q;

        return q;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
