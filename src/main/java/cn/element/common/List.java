package cn.element.common;

public interface List<T> extends Collection<T> {

    int size();

    T get(int i);

    void set(int i,T x);

    T remove(int i);

    boolean isEmpty();
}
