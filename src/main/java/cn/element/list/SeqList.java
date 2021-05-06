package cn.element.list;

import cn.element.common.MyList;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SeqList<T> implements MyList<T> {

    protected Object[] element;  //对象数组存储顺序表的数据元素,保护成员

    protected int n;            //顺序表元素的个数

    public SeqList(int length){  //构造容量为length的空表

        this.element = new Object[length];      //申请数组的存储空间,元素为null

        this.n = 0;
    }

    public SeqList(){

        this(64);       //创建默认容量的空表
    }

    public SeqList(T[] values){         //构造顺序表,由values数组提供元素

        this(values.length);        //创建容量为values.length的空表

        //复制元素赋值
        for (int i = 0; i < values.length; i++) {
            this.element[i] = values[i];
        }

        this.n = element.length;
    }

    //拷贝构造方法  浅拷贝
//    public SeqList(SeqList<T> list){
//
//        this.n = list.n;        //int整数赋值,复制了整数值
//
//        this.element = list.element;    //数组引用赋值,两个变量共用一个数组,错误
//    }

    //顺序表深拷贝
    public SeqList(SeqList<? extends T> list){

        this.n = list.n;

        this.element = new Object[list.element.length];     //申请一个数组

        for (int i = 0; i < list.n; i++) {
            this.element[i] = list.element[i];
        }
    }


    //判断顺序表是否为空
    @Override
    public boolean isEmpty(){

        return this.n == 0;
    }

    //返回顺序表元素的个数
    @Override
    public int size(){

        return this.n;
    }

    //返回第i个元素
    @Override
    public T get(int i){

        //范围 [0,n)
        if(i >= 0 && i < this.n){

            return (T) this.element[i];
        }

        return null;
    }

    //设置第i个元素
    @Override
    public void set(int i,T x){

        if(x == null){
            throw new NullPointerException("x == null");
        }

        if(i >= 0 && i < n){
            this.element[i] = x;
        }else {
            throw new ArrayIndexOutOfBoundsException(i + "");       //越界异常
        }
    }

    /**
     * 插入x作为第i个元素方法  若i>n则插入到最后
     *
     * 插入删除效率低下的原因
     */
    public int insert(int i,T x){

        if(x == null){
            throw new NullPointerException("x == null");
        }

        if(i < 0){
            i = 0;
        }

        if(i > this.n){
            i = n;
        }

        Object[] source = this.element;         //数组引用赋值,element也引用source

        if(this.element.length == this.n){      //如果数组已满,则扩充顺序表的容量
            this.element = new Object[this.n * 2];      //申请一个更大的数组

            for (int j = 0; j < i; j++) {
                this.element[j] = source[j];
            }
        }

        for (int j = this.n - 1; j >= i; j--) {
            element[j+1] = source[j];
        }

        this.element[i] = x;

        this.n++;

        return i;       //返回x的序号
    }

    //尾部插入,方法重载
    public int insert(T x){

        return this.insert(this.n,x);
    }

    //根据索引删除元素
    @Override
    public T remove(int i){

        if(this.n > 0 && i >= 0 && i < this.n){
            T old = (T) this.element[i];        //获取索引为i的元素

            for(int j = i;j < this.n-1;j++){
                this.element[j] = this.element[j+1];  //索引i之后的元素都向前移动一个位置
            }

            this.element[this.n-1] = null;      //设置数组元素对象为空,释放原引用实例

            this.n--;

            return old;
        }

        return null;
    }

    //删除顺序表所有的元素
    public void clear(){

        this.element = null;

        this.n = 0;
    }

    //查找方法,查找首次与Key相等的元素,返回元素索引,查找不成功返回-1,key==null,返回NullPointerException
    public int search(T key){

        if(key == null){
            throw new NullPointerException("key == null");
        }

        for (int i = 0; i < this.n; i++) {
            if(element[i].equals(key)){
                return i;
            }
        }

        return -1;
    }

    //判断是否包含关键字为key的元素
    public boolean contains(T key){

        return this.search(key) != -1;
    }

    //插入不重复的元素
    public int insertDifferent(T x){

        if(!this.contains(x)){
            this.insert(x);

            return 1;
        }

        return -1;
    }

    //删除首个出现的与key相等的元素,返回被删除的元素
    public T remove(T key){

        int i = this.search(key);

        if(i == -1){
            return null;
        }

        return this.remove(i);
    }

    //实现集合并运算
    public void addAll(SeqList<? extends T> list){

        for (int i = 0; i < list.element.length; i++) {

            if(!this.contains(list.get(i))){
                this.insert(list.get(i));
            }
        }
    }


    //顺序表比较相等
    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;            //引用同一个实例则相等
        }

        if(obj instanceof SeqList<?>){  //若obj引用顺序表实例,SeqList<?>是所有的SeqList<T>的父类

            SeqList<T> list = (SeqList<T>) obj;

            if(this.n == list.n){
                for (int i = 0; i < this.n; i++) {
                    if(!(this.get(i).equals(list.get(i)))){
                        return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    //返回描述的字符串
    @Override
    public String toString(){

        StringBuilder str = new StringBuilder(this.getClass().getSimpleName() + "(");       //返回类名

        if(this.n > 0){
            str.append(this.element[0].toString());
        }

        for (int i = 1; i < this.n; i++) {
            str.append(",").append(this.element[i].toString());
        }

        return str + ")";
    }

    //返回所有元素的描述字符串(从后向前)
    public String toPreviousString(){

        StringBuilder str = new StringBuilder(this.getClass().getName() + "(");       //返回类名

        if(this.n > 0){
            str.append(this.element[this.n-1].toString());
        }

        for (int i = this.n-2; i > -1; i--) {
            str.append(",").append(this.element[i].toString());
        }

        return str + ")";
    }

    /**
     * 获得本类的迭代器
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private int i = -1;

            @Override
            public boolean hasNext() {
                return element[++i] != null;
            }

            @Override
            public T next() {
                return (T) element[i];
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {

        for (Object o : this.element) {
            action.accept((T) o);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}