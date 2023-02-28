package org.codeart.datastructure.matrix;

import org.codeart.datastructure.list.PolySinglyList;
import org.codeart.datastructure.list.SeqList;
import org.codeart.datastructure.list.SortedSinglyList;
import org.codeart.datastructure.list.node.Node;

/**
 * 声明行的单链表存储的矩阵类
 * 稀疏矩阵行的单链表是指,将稀疏矩阵每行的非零元素三元组组成一条单链表,一个稀疏矩阵由多行单链表组成
 * 使用一个行指针顺序表记住每行的单链表,行指针顺序表的元素为排序单链表,长度是矩阵行数
 */
public class LinkedMatrix {

    private int rows;  //行数

    private int columns; //列数

    public SeqList<PolySinglyList<Triple>> rowList; //行指针顺序表,元素是多项式排序单链表

    /**
     * 构造 m * n 的零矩阵,若行数,列数 <= 0,抛出无效参数异常
     * @param m     行数
     * @param n     列数
     */
    public LinkedMatrix(int m, int n) {
        if (m > 0 && n > 0) {
            this.rows = m;
            this.columns = n;
            this.rowList = new SeqList<>();  //构造顺序表,初值为null

            for (int i = 0; i < m; i++) {  //顺序表增加 m 个空单链表
                this.rowList.insert(new PolySinglyList<>());  //顺序表尾插入,自动扩容
            }
        } else {
            throw new IllegalArgumentException("矩阵行列数不能 <= 0, m = " + m + ", n = " + n);
        }
    }

    /**
     * 构造 m * m 的零矩阵
     * @param m     行数
     */
    public LinkedMatrix(int m) {
        this(m,m);
    }

    /**
     * 构造 m * n 的矩阵,由三元组数组 triples 提供矩阵初值
     * @param m         行数
     * @param n         列数
     * @param triples   三元组数组
     */
    public LinkedMatrix(int m,int n,Triple[] triples) {
        this(m,n);

        for (Triple triple : triples) {
            this.set(triple);
        }
    }

    /**
     * 实现拷贝构造方法,深拷贝,复制所有元素对象
     * @param matrix            待拷贝的矩阵
     */
    public LinkedMatrix(LinkedMatrix matrix) {

    }

    /**
     * 返回矩阵行数
     */
    public int getRows(){
        return this.rows;
    }

    /**
     * 返回矩阵列数
     */
    public int getColumns(){
        return this.columns;
    }

    /**
     * 设置矩阵第 i 行第 j 列元素为x
     */
    public void set(int i,int j,int x){
        this.set(new Triple(i, j, x));
    }

    /**
     * 以三元组 triple 设置矩阵元素,若triple的行/列序号越界,抛出序号越界异常
     */
    public void set(Triple triple) {
        int i = triple.row;
        int j = triple.column;

        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns) {
            SortedSinglyList<Triple> list = this.rowList.get(i);  //获得第 i 行排序单链表

            if (triple.value == 0) {
                list.remove(triple);  //删除(i,j,?)结点 ( 顺序查找,如果有 )
            } else {
                Node<Triple> node = list.search(triple);  //顺序查找首次出现的元素

                if (node != null) {
                    node.data.value = triple.value;  //查找成功,修改矩阵元素值
                } else {
                    list.insert(triple);  //查找不成功,按(i,j)次序插入triple
                }
            }
        } else {
            throw new IndexOutOfBoundsException("i = " + i + ", j = " + j);  //抛出序号越界异常
        }
    }

    /**
     * 返回矩阵第 i 行,第 j 列元素,若 i,j 越界,则抛出序号越界异常
     * @param i         行数
     * @param j         列数
     * @return          int
     */
    public int get(int i,int j) {
        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns) {
            Node<Triple> tripleNode = this.rowList.get(i).search(new Triple(i, j, 0));  //第 i 行排序单链表顺序查找

            return (tripleNode != null) ? tripleNode.data.value : 0;
        }

        throw new IndexOutOfBoundsException("i = " + i + ", j = " + j);
    }

    /**
     * 设置矩阵为 m 行, n 列,若 m 指定行数较大,则将行指针顺序表扩容,使用原各行单链表
     * @param m         行
     * @param n         列
     */
    public void setRowsColumn(int m,int n) {
        if (m > 0 && n > 0) {
            if (m > this.rows) {  //若 m 参数指定行数较大
                for (int i = this.rows; i < m; i++) {
                    this.rowList.insert(new PolySinglyList<>());  //顺序表尾插入,自动扩容
                }

                this.rows = m;
                this.columns = n;
            } else {
                throw new IllegalArgumentException("矩阵行列数 <= 0, m = " + m + ", n = " + n);
            }
        }
    }

    /**
     * 稀疏矩阵相加
     * 将两个以行的单链表表示的矩阵进行相加的运算,算法将两个矩阵各行的单链表分别对应相加合并
     * 每两行单链表的合并算法同两个多项式的相加算法,因此,调用多项式单链表 PolySinglyList<T> 类的多项式相加算法 addAll()
     * 逐个比较两行单链表中对应的三元组对象,如果两个三元组表示相同位置的矩阵元素,则对应元素相加
     * @param matrix        待相加的矩阵
     */
    public void addAll(LinkedMatrix matrix){
        if (this.rows == matrix.rows && this.columns == matrix.columns) {
            for (int i = 0; i < this.rows; i++) {
                this.rowList.get(i).addAll(matrix.rowList.get(i));
            }
        } else {
            throw new IllegalArgumentException("两矩阵阶数不同,不能相加");
        }
    }

    /**
     * 输出矩阵
     */
    public void printMatrix(){
        System.out.println("矩阵" + this.getClass().getSimpleName() + "(" + rows + "*" + columns + "): ");

        for (int i = 0; i < this.rowList.size(); i++) {
            Node<Triple> p = this.rowList.get(i).head.next;  //遍历第 i 行排序单链表

            for (int j = 0; j < this.columns; j++) {
                if (p != null && j == p.data.column) {  //有 i == p.data.row
                    System.out.printf("%4d",p.data.value);

                    p = p.next;
                } else {
                    System.out.printf("%4d",0);
                }
            }

            System.out.println();
        }
    }

    /**
     * 返回this与matrix相加之后的矩阵也就是一个新的矩阵,不改变this和matrix
     * @param matrix            待相加矩阵
     * @return                  新的矩阵
     */
    public LinkedMatrix union(LinkedMatrix matrix) {


        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof LinkedMatrix) {
            LinkedMatrix matrix = (LinkedMatrix) obj;

            return this.rows == matrix.rows && this.columns == matrix.columns && this.rowList.equals(matrix.rowList);
        }

        return false;
    }

    /**
     * 输出矩阵
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.rowList.size(); i++) {
            sb.append(i).append("->").append(this.rowList.get(i).toString()).append("\n");
        }

        return sb.toString();
    }



}
