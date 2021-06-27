package cn.element.stack;

import cn.element.common.AbstractStack;
import cn.element.list.SeqList;

import java.util.Iterator;

/**
 * 顺序栈
 */
public final class SeqStack<T> extends AbstractStack<T> {

    //使用顺序表存储栈元素
    private final SeqList<T> list;

    //构造容量为length的空栈
    public SeqStack(int length) {

        //执行顺序表的构造方法
        this.list = new SeqList<>(length);
    }

    public SeqStack() {

        this(64);
    }

    /**
     * 判空
     * @return  boolean
     */
    @Override
    public boolean isEmpty() {

        return this.list.isEmpty();
    }

    /**
     * 元素x入栈,空对象不能入栈
     * @param x     待入栈元素
     */
    @Override
    public void push(T x) {

        this.list.insert(x);
    }

    /**
     * 返回栈顶元素,若栈为空返回null
     * 若栈空,get(i)返回null
     * @return      栈顶元素
     */
    @Override
    public T peek() {

        return this.list.get(list.size()-1);
    }

    /**
     * 出栈,返回栈顶元素;若栈空返回null
     * 若栈不空,顺序表尾删除,返回删除元素
     * @return      栈顶元素
     */
    @Override
    public T pop() {

        return this.list.remove(list.size()-1);
    }

    @Override
    public Iterator<T> iterator() {

        return null;
    }
}
