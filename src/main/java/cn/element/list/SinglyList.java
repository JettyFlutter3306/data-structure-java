package cn.element.list;

import cn.element.list.node.Node;

public class SinglyList<T> implements MyList<T>{

    //头指针,指向单链表的头结点
    public Node<T> head;

    //单链表改进,增加长度属性 n
    public int n;

    public SinglyList(){        //构建空单链表

        this.head = new Node<>();   //创建头结点,data和next值均为null

        this.n = 0;  //长度置为0
    }

    public SinglyList(T[] values){  //构造单链表,由数组提供元素

        this(); //构造空表

        Node<T> rear = this.head;   //rear指向单链表最后一个结点

        int count = 0;

        for (T value : values) {
            rear.next = new Node<>(value, null); //尾插入,创建结点链入rear结点之后

            count++;

            rear = rear.next;   //rear指向新的链尾结点
        }

        this.n = count;
    }

    //深拷贝方法
    public SinglyList(SinglyList<T> list){

    }

    public boolean isEmpty(){   //单链表判空

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

        if(i < values.length){
            p = new Node<>(values[i],null);  //创建第i个结点

            p.next = create(values,i+1);
        }

        return p;
    }

    //根据索引获取元素
    public T get(int i){    //返回第i个元素

        Node<T> p = this.head.next; //起点从head.next开始

        for(int j = 0;p.next != null && j < i;j++){
            p = p.next;
        }

        return (i >= 0 && p != null) ? p.data : null;
    }

    //设置第i个元素为x,0<= i <表长度,x != null
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

    //插入操作
    public Node<T> insert(int i,T x){

        if(x == null){
            throw new NullPointerException("x == null");    //抛出空指针异常
        }

        //小于0容错
        if(i < 0){
            i = 0;
        }

        if(i > this.size()){
            i = this.size();
        }

        Node<T> front = this.head;  //font指向头结点

        for(int j = 0;front.next != null && j < i;j++){
            front = front.next;
        }

        front.next = new Node<>(x,front.next);  //在front之后插入值为x的结点

        this.n++;

        return front.next;  //返回插入结点
    }

    //尾插入
    public Node<T> insert(T x){

        //调用insert(i,x); 用整数最大值指定插入在最后,遍历一次,i必须容错
        return insert(Integer.MAX_VALUE,x);
    }

    /**
     * 删除操作,删除第i个元素,0 <= i < n,
     * 返回被删除的元素;
     * i越界则返回null
     */
    public T remove(int i){

        if(i < 0 || i >= this.size()){
            throw new IndexOutOfBoundsException("i is not in the scope!");
        }

        Node<T> front = this.head;  //front指向头结点

        //循环遍历到索引 i-1
        for(int j = 0;j < i;j++){
            front = front.next;
        }

        T old = front.next.data;    //要删除的元素

        front.next = front.next.next;   //删除front的后继结点

        this.n--;

        return old;
    }

    //清除元素
    public void clear(){

        this.head.next = null;  //由java虚拟机自动回收所有结点占用的内存空间

        this.n = 0;
    }

    //查找返回首个与key相等的元素结点,查找不成功返回null
    public Node<T> search(T key){

        if(key == null){
            throw new NullPointerException("key == null!");
        }

        Node<T> p = this.head;

        while (p.next != null){
            p = p.next;

            if(p.data.equals(key)){
                return p;
            }
        }

        return null;
    }

    //判断是否包含关键字为key的元素
    public boolean contains(T key){

        Node<T> node = this.search(key);

        return node != null;
    }

    //插入不重复的元素,查找不成功时尾插入
    public Node<T> insertDifferent(T x){

        Node<T> node = this.search(x);

        if(node == null){
            this.insert(x);

            return new Node<>(x,null);
        }

        return null;
    }

    //删除首个与key相等元素,返回被删除元素;查找不成功时返回null
    public T remove(T key){

        Node<T> node = this.search(key);

        if(node != null){
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

    public double average(SinglyList<Integer> list){

        int sum = 0;

        int num = 0;

        Node<Integer> p = list.head;

        while (p.next != null){
            p  = p.next;

            num++;

            sum += p.data;
        }

        return (double) sum / num;
    }

    /**
     * 构造反序单链表,由values数组提供元素,返回值类型声明为T
     * 采用头插入,单链表次序与数组次序相反
     * @return  SinglyList
     */
    public static <T> SinglyList<T> createReverse(T[] values){

        SinglyList<T> list = new SinglyList<>();

        list.head = null;   //置空

        for (int i = 0; i < values.length; i++) {
            list.head = new Node<>(values[i],list.head);
        }

        list.head = new Node<>(null,list.head);

        return list;
    }

    /**
     * 将list单链表逆转
     */
    public static <T> void reverse(SinglyList<T> list){

        Node<T> front = null;  //front指向p的前驱

        Node<T> p = list.head.next; //p指向第0个结点

        Node<T> succ = list.head.next.next; //succ指向p的后继

        //cn.element.list.SinglyList(UI,VF,XZ,PO,VD,QW,X)
        while (p != null){

            p.next = front; //p的后继指向front

            front = p;  //front向后移动一个单位

            p = succ;   //p向后移动一个单位

            if(succ != null){
                succ = succ.next;   //succ向后移动一个单位
            }
        }

        list.head.next = front;
    }

    //集合并操作
    public void addAll(SinglyList<T> list){

    }

    /**
     * 遍历单链表的递归算法
     * 返回从p结点开始的子单链表描述字符串,递归方法
     * @param p     结点
     * @return      字符串
     */
    public String toString(Node<T> p){

        if(p == null){  //递归结束约束条件,空单链表返回空串
            return "";
        }

        String str = p.data.toString();

        if(p.next != null){
            str += ",";
        }

        //递归调用,递归通式;当前结点元素串连接从p的后继结点开始的子单链表
        return str + this.toString(p.next);
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(obj instanceof SinglyList){
            SinglyList<T> list = (SinglyList<T>) obj;

            if(this.size() != list.size()){
                return false;
            }else{
                Node<T> p = this.head;
                Node<T> q = list.head;

                while(p != null){
                    if(!p.data.equals(q.data)){
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


}
