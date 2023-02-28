package org.codeart.datastructure.list.node;

import org.codeart.datastructure.common.AbstractBaseEntity;

/**
 * 双链表结点类,T指定结点的元素类型
 */
public class DoubleNode<T> extends AbstractBaseEntity {

    public T data; //数据域,存储数据元素

    public DoubleNode<T> prev, next; //地址域,prev指向前驱元素,next指向后继元素

    public DoubleNode(T data, DoubleNode<T> prev, DoubleNode<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    public DoubleNode(T data) {
        this(data, null, null);
    }

    public DoubleNode() {

    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "data=" + data +
                '}';
    }
}
