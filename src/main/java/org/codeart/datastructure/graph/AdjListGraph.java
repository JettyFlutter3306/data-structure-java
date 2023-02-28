package org.codeart.datastructure.graph;

import org.codeart.datastructure.common.AbstractGraph;
import org.codeart.datastructure.common.Graph;
import org.codeart.datastructure.matrix.LinkedMatrix;
import org.codeart.datastructure.matrix.Triple;
import org.codeart.datastructure.list.SortedSinglyList;
import org.codeart.datastructure.list.node.Node;

import java.util.Iterator;

/**
 * 邻接表示的带权有向图类,继承抽象图类
 */
public class AdjListGraph<T> extends AbstractGraph<T> implements Graph<T> {

    protected LinkedMatrix adjList;  //图的邻接表,结构同矩阵行的单链表

    /**
     * 构造空图,顶点数为0,边数为n,length指定顺序表容量和邻接表容量
     */
    public AdjListGraph(int length) {
        super(length);
        adjList = new LinkedMatrix(length, length);  //构造length * length的矩阵
    }

    /**
     * 空构造方法,默认容量为10
     */
    public AdjListGraph() {
        this(10);
    }

    /**
     * 以vertices顶点集合构造图,边数为0
     *
     * @param vertices 顶点集合
     */
    public AdjListGraph(T[] vertices) {
        this();
        for (T vertex : vertices) {
            insertVertex(vertex);
        }
    }

    /**
     * 以vertices顶点集合和edges边集合构造图
     *
     * @param vertices 顶点集合
     * @param edges    边集合
     */
    public AdjListGraph(T[] vertices, Triple[] edges) {
        this(vertices);

        for (Triple edge : edges) {
            insertEdge(edge);
        }
    }

    @Override
    public void setVertex(int i, T x) {

    }

    /**
     * 插入元素为 x 的顶点,返回 x 顶点序号
     */
    @Override
    public int insertVertex(T x) {
        int i = vertexList.insert(x);  //顶点顺序表尾插入 x ,返回 x 的序号,自动扩容

        if (i >= adjList.getRows()) {  //若邻接表容量不够
            adjList.setRowsColumn(i + 1, i + 1);  //则扩容,保持邻接表行数同图的顶点数
        }

        return i;  //返回插入顶点序号
    }

    /**
     * 删除顶点vi及其关联的边
     * 在邻接表表示的图中,删除顶点vi,需要进行一下两部操作
     * 1.在顶点顺序表中删除第 i 个元素,图的顶点数减 1,vi之后的顶点序号减 1
     * 2.在邻接表中删除所有与顶点vi相关的边,包括一下操作:
     * (1) 在第 i 条以外的边单链表中,删除所有以顶点 i 为终点的边结点
     * (2) 在行指针顺序表中,删除第 i 个元素,即删除第 i 条边单链表
     * (3) 在所有边单链表中,将边结点中大于 i 的顶点序号减 1
     */
    @Override
    public void removeVertex(int i) {
        int n = vertexCount();  //删除之前的顶点数

        if (i >= 0 && i < n) {
            //删除与第 i 条边单链表中所有结点对称的边
            SortedSinglyList<Triple> list = adjList.rowList.get(i);

            for (Node<Triple> p = list.head.next; p != null; p = p.next) {  //遍历第 i 条边单链表
                removeEdge(p.data.toSymmetry());  //删除与p结点对称的边
            }

            n--;  //顶点数减 1

            adjList.rowList.remove(i);  //删除行指针顺序表的第 i 条边单链表,其后单链表上移
            adjList.setRowsColumn(n, n);  //设置矩阵行列数,少一行

            for (int j = 0; j < n; j++) {  //遍历每条单链表,将大于 i 的顶点序号减 1
                list = adjList.rowList.get(j);

                for (Node<Triple> p = list.head.next; p != null; p = p.next) {  //遍历第 j 条边单链表
                    if (p.data.row > i) {
                        p.data.row--;
                    }

                    if (p.data.column > i) {
                        p.data.column--;
                    }
                }
            }

            vertexList.remove(i);  //删除顶点vi,i后顶点序号减 1,图顶点数减 1
        } else {
            throw new IndexOutOfBoundsException("i = " + i);  //抛出序号异常
        }
    }

    /**
     * 返回顶点vi在vj之后的后继邻接顶点序号,若j == -1,返回vi的第一个邻接顶点序号
     * 若不存在后继邻接顶点,返回-1,用于图的遍历算法
     */
    @Override
    public int next(int i, int j) {
        int n = vertexCount();

        if (i >= 0 && i < n && j >= -1 && j < n && i != j) {
            SortedSinglyList<Triple> list = adjList.rowList.get(i);  //第i条排序单链表

            Node<Triple> tripleNode = list.head.next;  //单链表第0个元素

            if (j == -1) {
                return tripleNode != null ? tripleNode.data.column : -1;  //返回第一个邻接顶点的序号
            }

            tripleNode = list.search(new Triple(i, j, 0));  //顺序查找[vi,vj]边的结点

            if (tripleNode != null) {  //查找成功
                tripleNode = tripleNode.next;  //获得[vi,vj]边的后继结点

                if (tripleNode != null) {
                    return tripleNode.data.column;  //返回后继邻接结点序号
                }
            }
        }

        return -1;
    }

    /**
     * 插入边[vi,vj],权值为weight
     * 在邻接表表示的图中,插入权值为weight的边[vi,vj],操作如下:
     * ⑴ weight范围: 0 < weight < ∞,对 weight 容错,若越界,视为无边,取值为 0
     * ⑵ 若 i , j 满足: 0 <= i,j < 图的顶点数, i != j,则在邻接表的第 i 条边单链表中查找表示[vi,vj]边的结点,根据查找结果和
     * weight权值,分别执行插入,替换或删除的操作
     * ⑶ 若 i , j 越界,抛出序号越界异常,若 i == j,表示自身环,抛出无效参数异常
     */
    @Override
    public void insertEdge(int i, int j, int weight) {
        if (i != j) {  //不能表示自身环
            if (weight < 0 || weight >= MAX_WEIGHT) {  //边的权值容错,视为无边,取值为0
                weight = 0;
            }

            /*
             * 设置第i条边单链表中[vi,vj]边的权值为weight,若0<weight<∞,插入边或替换边的权
             * 值,若weight == 0,删除该边,若i,j越界,抛出序号越界异常
             */
            adjList.set(i, j, weight);
        } else {
            throw new IllegalArgumentException("不能插入自身环, i = " + i + ", j = " + j);
        }
    }

    /**
     * 算法实现说明:
     * 1.由于图的邻接表adjList的结构同矩阵行的单链表,adjList中只存储了非零元素的三元组,对于图而言,只存储了
     * 权值 weight > 0 的边,LinkedMatrix 类的set(i,j,weight) 方法实现了上述说明的操作,根据 weight 取值,或插入
     * 或替换,或删除结点
     * 2.由于邻接表中的边表示排序单链表,在查找和排序时,均调用compareTo(Triple triple)方法,各边仅按其行列值比较相等与大小,
     * 与边的权值无关
     * 插入一条边
     *
     * @param edge 边
     */
    public void insertEdge(Triple edge) {
        adjList.set(edge.row, edge.column, edge.value);
    }

    /**
     * 删除一条边[vi,vj],忽略权值
     * AdjListGraph<T>类声明以下重载的成员方法,删除边[vi,vj],算法将邻接表中该边的权值设置为0,就是在第i条边单链表中删除表示
     * 边[vi,vj]的结点,忽略权值
     */
    @Override
    public void removeEdge(int i, int j) {
        if (i != j) {
            adjList.set(new Triple(i, j, 0));  //设置边的权值为0,即在第i条边单链表中删除边结点
        }
    }

    /**
     * 删除一条边
     */
    public void removeEdge(Triple triple) {
        removeEdge(triple.row, triple.column);
    }

    /**
     * 返回[vi, vj]边的权值,用于图的最小生成树,最短路径等算法
     */
    @Override
    public int weight(int i, int j) {
        if (i == j) {
            return 0;
        }

        int weight = adjList.get(i, j);  //返回矩阵元素[i,j]值,若i, j越界,抛出序号越界异常

        return weight != 0 ? weight : MAX_WEIGHT;  //若返回0表示没有边,则边的权值返回∞
    }

    /**
     * 返回图的顶点集合和邻接表描述字符串
     */
    @Override
    public String toString() {
        return super.toString() + "出边表: \n" + adjList.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
