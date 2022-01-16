package cn.element.datastructure.common;

public interface Stack<T> extends Collection<T> {

    //判断是否是空栈
    boolean isEmpty();

    //元素x入栈
    void push(T x);

    //返回栈顶元素
    T peek();

    //出栈,返回栈顶元素
    T pop();

}
