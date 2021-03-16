package cn.element.queue;

import cn.element.list.SortedCirDoublyList;

/**
 * 优先队列类(升序或者降序),最终类,实现自定义队列接口,T表示数据元素的数据类型
 */
public final class PriorityQueue<T extends Comparable<? super T>> implements MyQueue<T>{

    private SortedCirDoublyList<T> list;    //使用排序循环双链表存储

    private boolean asc;                //使用asc指定升序 (true) 降序 (false)

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(T x) {
        return false;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }
}
