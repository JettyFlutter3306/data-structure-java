package cn.geeklbd.app.polynomial;

import cn.geeklbd.list.Node;
import cn.geeklbd.list.SortedSinglyList;

/**
 * 多项式排序单链表类
 */
public class PolySinglyList<T extends Comparable<? super T> & Addable> extends SortedSinglyList<T> {

    //构造方法
    public PolySinglyList(){
        super();
    }

    //构造方法,由数组提供元素,构造链表
    public PolySinglyList(T[] terms){

        super(terms);
    }

    //深拷贝
    public PolySinglyList(PolySinglyList<T> list){

        super(list);
    }

    //多项式相加,实现 this += list功能,不改变list
    public void addAll(PolySinglyList<T> list){

        Node<T> front = this.head; //定义一个front引用指向this头结点

        Node<T> p = this.head.next; //p指向第一项

        Node<T> q = list.head.next; // q指向list的头结点

        while(q != null){

            //若p == null,将list单链表中自q结点之后的剩余结点复制并插入到this单链表尾
            if(p == null){
                this.insert(q.data);

                q = q.next;
            }else{
                //初始状态,若p,q指数相同,则p系数 += q系数,使p,front,q都向右移动
                if(p.data.compareTo(q.data) == 0){
                    int i = p.data.add(q.data);

                    if(i == 0){
                        this.remove(p.data);
                    }

                    p = p.next;

//                    front = front.next;

                    q = q.next;
                }else if(p.data.compareTo(q.data) < 0){ //p的指数较小,front,p向后移动,q不移动
//                    front = front.next;

                    p = p.next;
                }else if(p.data.compareTo(q.data) > 0){ //p的指数较大,复制q结点并插入到p之前,front,q向后移动
                    this.insert(q.data);

//                    front = front.next;

                    q = q.next;
                }
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("");

        Node<T> p = this.head;

        while (p.next != null){

            p = p.next;

            sb.append(p.data.toString());
        }

        return sb.toString();
    }
}
