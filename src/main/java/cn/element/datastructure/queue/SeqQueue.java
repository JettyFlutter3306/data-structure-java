package cn.element.datastructure.queue;

import cn.element.datastructure.common.AbstractQueue;

import java.util.Iterator;

/**
 * 声明顺序队列
 */
public final class SeqQueue<T> extends AbstractQueue<T> {

    private Object[] element; //存储队列数据元素的数组

    private int front,rear; //front,rear分别为队列头尾下标

    /**
     * 构造容量为length的队列
     */
    public SeqQueue(int length) {
        if (length < 64) {
            length = 64;
        }

        //设置队列数组容量最小值
        this.element = new Object[length];

        //设置空队列
        this.front = this.rear = 0;
    }

    /**
     * 构造默认容量的空队列
     */
    public SeqQueue() {
        this(64);
    }

    /**
     * 判断队列是否是空
     * @return  boolean
     */
    @Override
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    /**
     * 元素x入队,空对象不能入队
     * @param x     元素
     * @return      boolean
     */
    @Override
    public boolean add(Object x) {
        if (x == null) {
            return false;
        }

        //若队列满,则扩充数组
        if (this.front == (this.rear + 1) % this.element.length) {
            Object[] temp = this.element;

            this.element = new Object[temp.length * 2]; //重新申请一个容量更大的数组

            int j = 0;

            for (int i = this.front; i != this.rear; i = (i+1) % temp.length) {
                this.element[j++] = temp[i]; //按照队列元素次序复制数组元素
            }

            this.front = 0;

            this.rear = j;
        }

        this.element[this.rear] = x;            //设置元素的值

        this.rear = (this.rear + 1) % this.element.length;  //重新设置rear的所在位置

        return true;
    }

    /**
     * 返回队头元素,没有删除,若队列为空,则返回null
     * @return  队头元素
     */
    @Override
    public T peek() {
        return this.isEmpty() ? null : (T) this.element[this.front];
    }

    /**
     * 出队,返回队头元素,若队列空返回null
     * @return  队头元素
     */
    @Override
    public T poll() {
        if (this.isEmpty()) {
            return null;
        }

        T temp = (T) this.element[this.front];

        this.front = (this.front + 1) % this.element.length;

        return temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "(");

        for (int i = front; i < rear; i++) {
            if(i == rear - 1){
                sb.append(this.element[i]).append(")");

                break;
            }

            sb.append(this.element[i]).append(",");
        }

        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
