package org.codeart.datastructure.list;

import org.codeart.datastructure.list.node.Node;

public class SortedSinglyList<T extends Comparable<? super T>> extends SinglyList<T>{

    /**
     * 构造空排序单链表
     */
    public SortedSinglyList() {
        super();    //默认调用父类构造方法SinglyList();
    }

    /**
     * 构造,将values数组中的所有对象按值插入
     */
    public SortedSinglyList(T[] values) {
        super();    //创建空单链表,默认调用SinglyList()

        for (T value : values) {
            this.insert(value);     //排序单链表按值插入
        }
    }

    /**
     * 拷贝构造方法
     */
    public SortedSinglyList(SortedSinglyList<T> list) {
        super(); //创建空表

        Node<T> p = list.head;

        while (p.next != null){

            p = p.next;

            this.insert(p.data);
        }


    }

    /**
     * 重载深拷贝,由单链表构造排序单链表
     */
    public SortedSinglyList(SinglyList<T> list) {
        super();

        for(Node<T> p = list.head.next;p != null;p = p.next){   //排序单链表按值插入
            this.insert(p.data);
        }
    }

    /**
     * 不支持父类的以下连个方法,将其覆盖并抛出异常,方法体同排序顺序表
     * @param i         索引,从0开始
     * @param x         泛型元素
     */
    public void set(int i,T x) {
        throw new UnsupportedOperationException("set(i,x)");
    }

    public Node<T> insert(int i,T x) {
        throw new UnsupportedOperationException("insert(i,x)");
    }

    /*
     * 插入x, x!=null,根据x对象的大小顺序查找确定插入的位置,插入在等值结点之前
     * 返回插入结点, O(n) 覆盖父类的insert(x)的方法
     */
    @Override
    public Node<T> insert(T x) {
        Node<T> front = this.head;

        Node<T> p = front.next;//front指向p的前驱结点

        while (p != null && x.compareTo(p.data) > 0) {
            front = p;

            p = p.next;
        }

        front.next = new Node<>(x,p);   //在front之后,p之前插入值为x的结点
        this.n++;

        return front.next;  //返回插入结点
    }

    /*
    一下顺序表查找和基于查找算法的操作,都是覆盖父类的同名方法
    调用compareTo()方法比较大小的相等
     */
    @Override
    public Node<T> search(T key) { //查找返回首个与key相等元素的结点,查找不成功返回null
        Node<T> p = this.head.next;

        if (this.isEmpty()) {
            return null;
        }

        if (p.data.compareTo(key) > 0) {
            return null;
        }

        while (p != null) {
            if (p.data.compareTo(key) == 0) {
                return p;
            }

            p = p.next;
        }

        return null;
    }

    /**
     * 插入不重复元素,查找不成功时按值插入
     */
    public Node<T> insertDifferent(T x) {
        Node<T> node = this.search(x);

        if (node == null) {
            return this.insert(x);
        }

        return null;
    }

    /**
     * 删除首个与key相等的元素,返回被删除的元素;查找不成功返回null
     */
    public T remove(T key) {
        Node<T> node = this.search(key);

        if (node != null) {
            Node<T> p = this.head;

            while (p.next != null) {
                if (p.next.data.compareTo(key) == 0) {
                    p.next = p.next.next;
                    this.n--;
                    return key;
                }

                p = p.next;
            }
        }

        return null;
    }

    /**
     * 将list中所有的元素插入到this单链表,不改变list,集合并,覆盖
     * @param list      SinglyList
     */
    @Override
    public void addAll(SinglyList<T> list) {
        Node<T> p = list.head.next;

        while (p != null){

            this.insert(p.data);

            p = p.next;
        }
    }

    
}
