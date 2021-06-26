package cn.element.stack;

import cn.element.list.SinglyList;

import java.io.Serializable;

/**
 * 链式栈
 */
public final class LinkedStack<T extends Serializable> implements MyStack<T>{

    //使用单链表存储栈元素
    private final SinglyList<T> list;

    //构造空栈
    public LinkedStack() {

        //构造单链表
        this.list = new SinglyList<>();
    }

    @Override
    public boolean isEmpty() {

        return this.list.isEmpty();
    }

    /**
     * 元素x入栈
     */
    @Override
    public void push(T x) {

        this.list.insert(0,x);
    }

    /**
     * 返回栈顶元素
     * 若栈空则返回null
     */
    @Override
    public T peek() {

        return this.list.get(0);
    }

    /**
     * 出栈,返回栈顶元素,若栈空则返回null
     * 若栈不空,单链表头删除,返回删除元素
     */
    @Override
    public T pop() {

        return this.list.remove(0);
    }
}

