package cn.element.datastructure.common;

public interface Stack<T> extends Collection<T> {
    
    boolean isEmpty();
    
    void push(T x);
    
    T peek();
    
    T pop();
}
