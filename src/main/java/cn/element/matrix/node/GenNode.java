package cn.element.matrix.node;

import cn.element.common.AbstractBaseEntity;
import cn.element.matrix.GenList;

/**
 * 声明广义表双链表示的结点类
 */
public class GenNode<T> extends AbstractBaseEntity {

    public T data;  //数据域

    public GenList<T> child;  //地址域,指向子表

    public GenNode<T> next;  //地址域.指向后继结点

    public GenNode(T data, GenList<T> child, GenNode<T> next) {
        this.data = data;
        this.child = child;
        this.next = next;
    }

    public GenNode(T data) {
        this.data = data;
    }

    public GenNode() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
