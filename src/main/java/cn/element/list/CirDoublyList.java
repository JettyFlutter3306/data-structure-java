package cn.element.list;

import cn.element.common.AbstractList;
import cn.element.list.node.DoubleNode;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 循环双链表类,实现ADT List<T>声明的方法
 * T表示数据元素的数据类型
 */
public class CirDoublyList<T> extends AbstractList<T> {

    public DoubleNode<T> head; //头结点的引用

    public CirDoublyList(){

        this.head = new DoubleNode<>(); //创建头结点,3个值均为null
        // 构造循环双链表
        this.head.prev = this.head;
        this.head.next = this.head;
    }

    //判空
    @Override
    public boolean isEmpty(){

        return this.head.next == this.head;
    }

    /**
     * 插入x为第i个元素, x != null,返回x结点
     * 对i容错,若i < 0,则头插入
     * 若i > 插入n,则尾插入 O(n)
     */
    public DoubleNode<T> insert(int i,T x){

        if(x == null){

            throw new NullPointerException("x == null!");
        }

        DoubleNode<T> front = this.head; //front指向头结点

        for(int j=0;front.next != this.head && j < i;j++){ //寻找第i-1个或者最后一个结点

            front = front.next;
        }

        DoubleNode<T> q = new DoubleNode<>(x,front,front.next); //在front之后插入x结点

        front.next.prev = q;

        front.next = q;

        return q;
    }

    /**
     * 尾插入x元素,返回x结点
     * 算法在头结点之前插入 O(1)
     */
    public DoubleNode<T> insert(T x){

        if(x == null){
            throw new NullPointerException("x == null!"); //抛出空指针异常
        }

        DoubleNode<T> q = new DoubleNode<>(x,head.prev,head);

        head.prev.next = q; //在头结点之前插入,即尾插入

        head.prev = q;

        return q;
    }

    @Override
    public T get(int i) {

        return this.head.next.data;
    }

    @Override
    public int size() {

        DoubleNode<T> p = this.head.next;

        int count = 0;

        while(p != null){
            count++;

            p = p.next;
        }

        return count;
    }

    /**
     * 根据索引删除元素
     * @param i     索引
     * @return      元素
     */
    @Override
    public T remove(int i) {

        return null;
    }

    /**
     * 删除最后一个元素,返回被删除元素,若链表空,返回null
     * 算法删除头结点的前驱结点
     */
    public T removeLast(){

        return null;
    }

    @Override
    public void set(int i, T x) {

    }

    /**
     * 返回所有元素的描述字符串(元素次序从后向前)
     */
    public String toPreviousString(){

        return null;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName()+"(");

        DoubleNode<T> p = this.head;

        while(p.next != this.head){
            p = p.next;

            if(p.next == this.head){
                sb.append(p.data).append(")");

                break;
            }

            sb.append(p.data).append(",");
        }

        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }
}
