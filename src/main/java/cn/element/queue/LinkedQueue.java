package cn.element.queue;

import cn.element.list.node.Node;

/**
 * 链式队列,最终类
 * @param <T>  元素类型
 */
public final class LinkedQueue<T> implements MyQueue<T>{

    private Node<T> front,rear;  //front和rear分别指向队列头和队列尾结点

    public LinkedQueue() {  //构造空队列

        this.front = this.rear = null;
    }

    /**
     * 判断队列是否为空
     */
    @Override
    public boolean isEmpty() {

        return this.front == null && this.rear == null;
    }

    /**
     * 元素x入队,空对象不能入队
     * @param x     元素
     * @return      boolean
     */
    @Override
    public boolean add(T x) {

        if(x == null){
            return false;
        }

        Node<T> q = new Node<>(x,null);

        if(this.front == null){
            this.front = q;
        }else{
            this.rear.next = q; //空队列插入
        }

        this.rear = q;

        return true;
    }

    /**
     * 返回队头元素
     */
    @Override
    public T peek() {

        return this.isEmpty() ? null : this.front.data;
    }

    /**
     * 队头元素出队并且返回队头元素
     */
    @Override
    public T poll() {
        if(this.isEmpty()){
            return null;
        }

        T x = this.front.data; //取得队头元素

        this.front = this.front.next; //删除队头结点

        if(this.front == null){
            this.rear = null;
        }

        return x;
    }
}
