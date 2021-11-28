package cn.element.list;

import cn.element.common.AbstractList;
import cn.element.list.node.Node;
import cn.element.util.SerializeUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 声明单链表类继承自抽象列表类,泛型类
 */
public class SinglyList<T> extends AbstractList<T> {

    public Node<T> head;  //头结点的引用,指向单链表的头结点

    public int n;  //单链表改进,增加长度属性 n

    /**
     * 构建空单链表
     */
    public SinglyList() {
        this.head = new Node<>();   //创建头结点,data和next值均为null

        this.n = 0;  //长度置为0
    }

    /**
     * 构造单链表,由数组提供元素
     * @param values            数据源
     */
    public SinglyList(T[] values) {
        this(); //构造空表

        Node<T> rear = this.head;   //rear指向单链表最后一个结点

        for (T value : values) {
            rear.next = new Node<>(value, null); //尾插入,创建结点链入rear结点之后

            rear = rear.next;   //rear指向新的链尾结点
        }

        this.n = values.length;
    }

    /**
     * 深拷贝单链表
     * @param list                      数据源
     * @throws IOException              IO异常
     * @throws ClassNotFoundException   类型转换异常
     */
    public SinglyList(SinglyList<T> list) throws IOException, ClassNotFoundException {
        this.head = SerializeUtil.deepClone(list.head);

        this.n = list.n;
    }

    /**
     * 返回单链表第0个元素结点
     */
    public Node<T> first() {
        return null;
    }

    /**
     * 返回结点p的后继结点
     */
    public Node<T> next(Node<T> p) {
        return null;
    }

    /**
     * 返回结点p的前驱结点
     */
    public Node<T> previous(Node<T> p) {
        return null;
    }

    /**
     * 返回单链表最后一个元素的结点
     */
    public Node<T> last() {
        return null;
    }

    /**
     * 单链表判空
     * @return      boolean
     */
    public boolean isEmpty(){
        return this.head.next == null;
    }

    /**
     * 返回从value数组第i个元素开始构造的子单链表,即返回第i个结点,递归算法
     * @param values        数组
     * @param i             索引
     * @return              结点
     */
    private Node<T> create(T[] values,int i){
        Node<T> p = null;

        if (i < values.length) {
            p = new Node<>(values[i],null);  //创建第i个结点

            p.next = create(values,i+1);
        }

        return p;
    }

    /**
     * 根据索引获取元素
     * @param i         索引
     * @return          T
     */
    public T get(int i) {    //返回第i个元素
        Node<T> p = this.head.next; //起点从head.next开始

        for (int j = 0; p.next != null && j < i; j++) {
            p = p.next;
        }

        return i >= 0 ? p.data : null;
    }

    /**
     * 设置第i个元素为x,0<= i < 表长度,x != null
     * @param i         索引,从0开始
     * @param x         泛型元素
     */
    public void set(int i,T x){
        Node<T> p = this.head.next;

        for(int j = 0;p.next != null && j < i;j++){
            p = p.next;
        }

        p.data = x;
    }

    /**
     * 返回单链表长度,优化了一手
     * 在单链表创建的时候就已经初始化了长度n的值
     */
    @Override
    public int size(){
//        int count = 0;
//
//        Node<T> p = this.head.next; //从head.next开始
//
//        while (p != null){
//
//            count++;
//
//            p = p.next;
//        }
        return this.n;
    }

    /**
     * 在指定位置插入元素
     * @param i         索引
     * @param x         元素
     * @return          Node
     */
    public Node<T> insert(int i,T x){
        if (x == null) {
            throw new NullPointerException("x == null");    //抛出空指针异常
        }

        //小于0容错
        if (i < 0) {
            i = 0;
        }

        if (i > this.size()) {
            i = this.size();
        }

        Node<T> front = this.head;  //font指向头结点

        for (int j = 0; front.next != null && j < i; j++){
            front = front.next;
        }

        front.next = new Node<>(x,front.next);  //在front之后插入值为x的结点

        this.n++;

        return front.next;  //返回插入结点
    }

    /**
     * 尾插入
     */
    public Node<T> insert(T x){
        return insert(Integer.MAX_VALUE,x);  //调用insert(i,x); 用整数最大值指定插入在最后,遍历一次,i必须容错
    }

    /**
     * 删除操作,删除第i个元素,0 <= i < n,
     * 返回被删除的元素;
     * i越界则返回null
     */
    public T remove(int i){
        if (i < 0 || i >= this.size()) {
            throw new IndexOutOfBoundsException("i is not in the scope!");
        }

        Node<T> front = this.head;  //front指向头结点

        for (int j = 0; j < i; j++) {  //循环遍历到索引 i-1
            front = front.next;
        }

        T old = front.next.data;    //要删除的元素

        front.next = front.next.next;   //删除front的后继结点

        this.n--;

        return old;
    }

    /**
     * 清空单链表
     */
    public void clear(){
        this.head.next = null;  //由java虚拟机自动回收所有结点占用的内存空间

        this.n = 0;
    }

    /**
     * 查找返回首个与key相等的元素结点,查找不成功返回null
     * @param key       关键字
     * @return          Node
     */
    public Node<T> search(T key) {
        if (key == null) {
            throw new NullPointerException("key == null!");
        }

        Node<T> p = this.head;

        while (p.next != null) {
            p = p.next;

            if(p.data.equals(key)){
                return p;
            }
        }

        return null;
    }

    /**
     * 判断是否包含关键字为key的元素
     */
    public boolean contains(T key) {
        Node<T> node = this.search(key);

        return node != null;
    }

    /**
     * 判断是否包含关键字为key的元素
     */
    public Node<T> insertDifferent(T x) {
        Node<T> node = this.search(x);

        if (node == null) {
            this.insert(x);

            return new Node<>(x,null);
        }

        return null;
    }

    /**
     * 删除首个与key相等元素,返回被删除元素;查找不成功时返回null
     */
    public T remove(T key) {
        Node<T> node = this.search(key);

        if (node != null) {
            Node<T> p = this.head;

            while (p.next != null) {
                if (p.next.data.equals(key)) {
                    T data = p.next.data;

                    p.next = p.next.next;

                    this.n--;

                    return data;
                }

                p = p.next;
            }
        }

        return null;
    }

    /**
     * 求一个整数单链表的平均值
     * @param list      单链表,泛型<Integer>
     * @return          double
     */
    public static double average(SinglyList<Integer> list) {
        int sum = 0;

        for (Integer integer : list) {
            sum += integer;
        }

        return (double) sum / list.n;
    }

    /**
     * 去掉一个最大值和一个最小值再求平均值
     */
    public static double averageExceptMaxMin(SinglyList<Integer> list) {
        return 0;
    }

    /**
     * 返回单链表list最大值,T类必须能够比较对象大小
     */
    public static <T extends Comparable<? super T>> T max(SinglyList<T> list) {
        return null;
    }

    /**
     * 判断单链表list是否是排序,若asc取值为true,否则降序
     */
    public static <T extends Comparable<? super T>> boolean isSorted(SinglyList<T> list) {
        return false;
    }

    /**
     * 构造反序单链表,由values数组提供元素,返回值类型声明为T
     * 采用头插入,单链表次序与数组次序相反
     * @return  SinglyList
     */
    public static <T> SinglyList<T> createReverse(T[] values) {
        SinglyList<T> list = new SinglyList<>();

        list.head = null;   //置空

        for (T value : values) {
            list.head = new Node<>(value, list.head);
        }

        list.head = new Node<>(null,list.head);

        return list;
    }

    /**
     * 将list单链表逆转
     */
    public static <T> void reverse(SinglyList<T> list) {
        Node<T> front = null;  //front指向p的前驱

        Node<T> p = list.head.next; //p指向第0个结点

        Node<T> suc = list.head.next.next; //suc指向p的后继

        //cn.element.list.SinglyList(UI,VF,XZ,PO,VD,QW,X)
        while (p != null){

            p.next = front; //p的后继指向front

            front = p;  //front向后移动一个单位

            p = suc;   //p向后移动一个单位

            if(suc != null){
                suc = suc.next;   //suc向后移动一个单位
            }
        }

        list.head.next = front;
    }

    /**
     * 集合并操作
     * @param list      SinglyList
     */
    public void addAll(SinglyList<T> list) {
        addAll(this.n, list);
    }

    public void addAll(int i, SinglyList<T> list) {

    }

    /**
     * 顺序查找,并将所有与key相等的元素替换为x
     */
    public void replaceAll(T key, T x) {

    }

    /**
     * 顺序查找并删除所有与key相等元素的结点
     */
    public void removeAll(T key) {

    }

    /**
     * 返回由 begin ~ end 之间的元素组成的子表
     * @param begin         开始
     * @param end           结束
     * @return              SinglyList
     */
    public SinglyList<T> subList(int begin, int end) {
        return null;
    }

    /**
     * 删除 begin ~ end 之间的元素,返回被删除元素组成的表
     * @param begin         开始
     * @param end           结束
     * @return              SinglyList
     */
    public SinglyList<T> remove(int begin, int end) {
        return null;
    }

    /**
     * 判断this单链表是否包含list的所有元素
     */
    public boolean contains(SinglyList<T> list) {
        return false;
    }

    /**
     * 返回将this与list合并连接的单链表,并集
     */
    public SinglyList<T> union(SinglyList<T> list) {
        return null;
    }

    /**
     * 删除那些也在list中的元素
     */
    public void subtract(SinglyList<T> list) {

    }

    /**
     * 返回this与list的差集
     */
    public SinglyList<T> difference(SinglyList<T> list) {
        return null;
    }

    /**
     * 仅保留那些也包含在list中的元素
     */
    public void retainAll(SinglyList<T> list) {

    }

    /**
     * 返回this与list的交集
     */
    public SinglyList<T> intersection(SinglyList<T> list) {
        return null;
    }

    /**
     * 查找并返回this中首个与pattern匹配的子表,包含模式匹配
     */
    public Node<T> search(SinglyList<T> pattern) {
        return null;
    }

    /**
     * 删除this中所有与pattern匹配的子表,模式匹配
     */
    public void removeAll(SinglyList<T> pattern) {

    }

    /**
     * 将this中所有与pattern匹配的子表替换为list,模式匹配
     */
    public void replaceAll(SinglyList<T> pattern, SinglyList<T> list) {

    }

    /**
     * 遍历单链表的递归算法
     * 返回从p结点开始的子单链表描述字符串,递归方法
     * @param p     结点
     * @return      字符串
     */
    public String toString(Node<T> p) {
        if (p == null) {  //递归结束约束条件,空单链表返回空串
            return "";
        }

        String str = p.data.toString();

        if (p.next != null) {
            str += ",";
        }

        //递归调用,递归通式;当前结点元素串连接从p的后继结点开始的子单链表
        return str + this.toString(p.next);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof SinglyList) {
            SinglyList<T> list = (SinglyList<T>) obj;

            if (this.size() != list.size()) {
                return false;
            } else {
                Node<T> p = this.head;
                Node<T> q = list.head;

                while (p != null) {
                    if (!p.data.equals(q.data)) {
                        return false;
                    }

                    p = p.next;
                    q = q.next;
                }
            }
        }

        return true;
    }

    //返回描述字符串
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("(");

        for(Node<T> p = this.head.next;p != null;p = p.next){
            str.append(p.data.toString());

            if(p.next != null){
                str.append(",");
            }
        }

        return str + ")";
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> p = SinglyList.this.head;

            @Override
            public boolean hasNext() {
                return p.next != null;
            }

            @Override
            public T next() {
                p = p.next;

                return p.data;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Node<T> p = this.head;

        while(p.next != null){
            p = p.next;

            action.accept(p.data);
        }
    }
}
