package cn.element.list;

import java.io.IOException;

/**
 * 声明排序顺序表类
 */
public class SortedSeqList<T extends Comparable<? super T>> extends SeqList<T>{

    //构造一个空的排序顺序表
    public SortedSeqList() {
        super();
    }

    public SortedSeqList(int length) {
        super(length);
    }

    /**
     * 构造排序顺序表,由数组提供元素
     */
    public SortedSeqList(T[] values) {
        super(values.length);       //创建空的排序顺序表,指定容量

        for (T value : values) {
            this.insert(value);
        }
    }

    /**
     * 由顺序表list构造排序顺序表,O(n^2)
     */
    public SortedSeqList(SeqList<? extends T> list) {
        super();    //创建空排序顺序表,默认调用SeqList();

        for (int i = 0; i < list.n; i++) {      //直接使用排序插入算法,每趟插入一个元素
            this.insert(list.get(i));           //O(n)
        }
    }

    /**
     * 排序顺序表拷贝构造方法,深拷贝,O(n)
     */
    public SortedSeqList(SortedSeqList<? extends T> list) throws IOException, ClassNotFoundException {
        super(list);                        //调用SeqList(SeqList<T> list)
    }

    /**
     * 插入x, x!=null, 根据x对象大小顺序查找确定插入位置,插入在等值结点之前,返回x的序号
     * 调用T的compareTo()方法比较对象的大小,覆盖父类的insert(T x),参数列表和返回值相同
     * @param x     元素
     * @return      索引
     */
    @Override
    public int insert(T x) {
        int i = 0;      //定义一个索引i

        if (this.isEmpty() || x.compareTo(this.get(this.size()-1)) > 0) {     //compareTo比较大小
            i = this.n;                                                     //尾插入
        } else {
            while (i < this.n && x.compareTo(this.get(i)) > 0) {      //寻找插入位置(升序)
                i++;
            }
        }

        super.insert(i,x);

        return i;
    }

    /**
     * 插入不重复的元素,查找不成功时,按值插入
     */
    @Override
    public int insertDifferent(T x){
        if (!super.contains(x)) {
            this.insert(x);
        }

        return -1;
    }

    /**
     * 不支持父类的以下两个方法,将其覆盖并抛出异常
     */
    @Override
    public void set(int i, T x) {       //只读特性
        throw new java.lang.UnsupportedOperationException("set(int i,T x)");
    }

    /**
     * 任意位置插入
     */
    @Override
    public int insert(int i, T x) {
        throw new java.lang.UnsupportedOperationException("insert(int i,T x)");
    }

    /**
     * 顺序查找首次出现的与key相等的元素,返回元素序号i(0<= i <n);若查找不成功,则返回-1,覆盖
     */
    @Override
    public int search(T key){
        for (int i = 0; i < this.n && key.compareTo(this.get(i)) >= 0; i++) {
            if (key.compareTo(this.get(i)) == 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 删除首次出现的与key相等的元素,返回被删除的元素,查找不成功则返回null
     */
    @Override
    public T remove(T key){
        return super.remove(this.search(key)); //this.search(key)运行时多态,子类对象调用子类的查找方法
    }

    /**
     * 归并this和list排序顺序表
     */
    public void merge(SortedSeqList<? extends T> list){

    }

    /**
     * 返回this和list归并(升序)后的排序顺序表,不改变this和list
     */
    public SortedSeqList<T> mergeWith(SortedSeqList<? extends T> list){

        return null;
    }






}
