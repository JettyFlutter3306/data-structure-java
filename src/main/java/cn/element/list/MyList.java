package cn.element.list;

public interface MyList<T> {

    int size();

    T get(int i);

    void set(int i,T x);

    T remove(int i);

    boolean isEmpty();

}
