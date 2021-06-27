package cn.element.queue;

import cn.element.common.AbstractQueue;
import cn.element.list.SortedCirDoublyList;

import java.util.Iterator;

/**
 * 优先队列类(升序或者降序),最终类,实现自定义队列接口,T表示数据元素的数据类型
 */
public final class PriorityQueue<T extends Comparable<? super T>> extends AbstractQueue<T> {

    private final SortedCirDoublyList<T> list;    //使用排序循环双链表存储

    private final boolean asc;                //使用asc指定升序 (true) 降序 (false)

    public PriorityQueue(boolean asc) { //构造空队列.asc指定升序或降序

        this.list = new SortedCirDoublyList<>();
        this.asc = asc;
    }

    public PriorityQueue() {

        this(true);
    }

    /**
     * 判断队列元素是否为空
     * @return      boolean
     */
    @Override
    public boolean isEmpty() {

        return this.list.isEmpty();
    }

    /**
     * 元素x入队,空对象不能入队
     * @param x    元素x
     * @return      boolean
     */
    @Override
    public boolean add(T x) {

        return this.list.insert(x) != null;     //排序循环双链表按值插入
    }

    /**
     * 返回队头元素,没有删除,若队列为空则返回null
     * @return      元素
     */
    @Override
    public T peek() {

        return this.asc ? this.list.get(0) : this.list.head.prev.data;
    }

    /**
     * 出队,返回队头元素,若队列为空则返回null
     * @return      元素
     */
    @Override
    public T poll() {

        //升序时,返回队头元素,删除队头结点,降序时,返回队尾元素,删除队尾结点
        return this.asc ? this.list.remove(0) : this.list.removeLast();
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName() + "" + (this.asc ? this.list : this.list.toPreviousString());
    }

    @Override
    public Iterator<T> iterator() {

        return null;
    }
}
