package cn.element.matrix;

import cn.element.matrix.node.GenNode;

/**
 * 声明双链表示的广义表GenList<T>
 */
public class GenList<T> implements IGenList<T>{

    public GenNode<T> head;  //头指针,指向(引用)头结点

    public GenList() {  //构造空广义表

        this.head = new GenNode<>();  //创建头结点,3个域值均为 null
    }

    /**
     * 构造广义表,由原子项数组提供数据源
     * @param atoms         原子项数组
     */
    public GenList(T[] atoms) {

        this();

        GenNode<T> p = this.head;

        for (T atom : atoms) {
            p.next = new GenNode<>(atom,null,null);

            p = p.next;
        }
    }

    @Override
    public boolean isEmpty() {

        return this.head.next == null;
    }

    /**
     * 返回广义表的长度
     */
    @Override
    public int size() {

        GenNode<T> p = this.head.next;

        int count = 0;

        while(p != null){
            count++;

            p = p.next;
        }

        return count;
    }

    /**
     * 返回广义表的深度,递归算法
     */
    @Override
    public int depth() {

        int temp = 0;

        for (GenNode<T> p = this.head.next; p != null ; p = p.next) {
            if(p.child != null){
                temp = Math.max(p.child.depth(),temp);
            }
        }

        return temp + 1;
    }

    /**
     * 插入原子项 x 作为第 i 个元素,对 i 容错
     * @param i         索引
     * @param x         元素
     * @return          GenNode<T>
     */
    @Override
    public GenNode<T> insert(int i, T x) {

        int n = this.size();

        if(i < 0){  //对 i 容错
            i = 0;
        }else if(i > n){
            i = n;
        }

        GenNode<T> p = this.head;

        for (int j = 0; j < i; j++) {
            p = p.next;
        }

        p.next = new GenNode<>(x,null,p.next);

        return p.next;
    }

    /**
     * 在广义表最后添加原子 x 结点
     * @param x         原子项
     * @return          结点
     */
    public GenNode<T> insert(T x){

        return this.insert(this.size(),x);
    }

    /**
     * 插入子表 genList 作为第 i 个元素,对 i 容错
     * @param i                 索引
     * @param genList           子表
     * @return                  结点
     */
    @Override
    public GenNode<T> insert(int i, GenList<T> genList) {

        int n = this.size();

        if(i < 0){  //对 i 容错
            i = 0;
        }else if(i > n){
            i = n;
        }

        GenNode<T> p = this.head;

        for (int j = 0; j < i; j++) {
            p = p.next;
        }

        p.next = new GenNode<>(null,genList,p.next);

        return p.next;
    }

    /**
     * 尾插入子表,方法重载
     */
    public GenNode<T> insert(GenList<T> genList){

        return this.insert(this.size(),genList);
    }

    @Override
    public void remove(int i) {

        GenNode<T> p = this.head;

        for (int j = 0; j < i; j++) {
            p = p.next;
        }

        p.next = p.next.next;
    }

    /**
     * 返回广义表所有元素值对应的字符串,形式为 "(,)",广义表遍历算法,递归方法
     * @param sb           字符串
     */
    public String toString(StringBuilder sb){

        sb.append("(");

        for (GenNode<T> p = this.head.next; p != null ; p = p.next) {
            if(p.child == null){
                sb.append(p.data);
            }else{
                sb.append(p.child);  //递归调用,遍历子表添加子表描述字符串
            }

            if(p.next != null){
                sb.append(",");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    /**
     * 返回广义表所有元素的描述字符串
     */
    @Override
    public String toString() {

        return this.toString(new StringBuilder());
    }
}
