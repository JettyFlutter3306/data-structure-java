package cn.element.graph;

import cn.element.matrix.Matrix;
import cn.element.matrix.Triple;

import java.io.Serializable;

/**
 * 声明邻接矩阵表示的带权图类
 * 继承抽象图类,继承了常量MAX_WEIGHT和成员变量vertexList
 * 增加了新的成员变量 matrix 存储图的邻接矩阵
 */
public class MatrixGraph<T extends Serializable> extends AbstractGraph<T> {

    protected Matrix matrix;  //矩阵对象,存储图的邻接矩阵

    /**
     * 构造空图,顶点数为0,边数为0
     * @param length        指定顶点顺序表容量和邻接矩阵容量
     */
    public MatrixGraph(int length) {

        super(length);  //构造容量为length的空顺序表

        this.matrix = new Matrix(length);  //构造length * length 矩阵,初值为0
    }

    /**
     * 构造空图,顶点数为0,边数为0
     * 顶点顺序表和邻接矩阵的默认容量是10
     */
    public MatrixGraph() {

        this(10);
    }

    /**
     * 以vertices顶点集合构造图,边数0
     * @param vertices      顶点集合
     */
    public MatrixGraph(T[] vertices) {

        this(vertices.length);  //构造指定容量的空图

        for (T vertex : vertices) {
            this.insertVertex(vertex);
        }
    }

    public MatrixGraph(T[] vertices, Triple[] edges) {

        this(vertices);

        for (Triple edge : edges) {
            this.insertEdge(edge);  //插入一个结点
        }
    }

    @Override
    public void setVertex(int i, T x) {

    }

    /**
     * 插入元素为 x 的顶点,返回 x 的序号
     * @return      序号
     */
    @Override
    public int insertVertex(T x) {

        int i = this.vertexList.insert(x);  //顶点顺序表尾插入 x ,返回 x 序号,自动扩容

        if(i >= this.matrix.getRows()){  //若邻接矩阵容量不够
            this.matrix.setRowsColumns(i + 1,i + 1);  //矩阵扩容,保持邻接矩阵行列数同图的顶点数
        }

        for (int j = 0; j < i; j++) {  //初始化第 i 行,列元素值为 ∞, i == j 值已经是 0
            this.matrix.set(i,j,MAX_WEIGHT);
            this.matrix.set(j,i,MAX_WEIGHT);
        }

        return i;
    }

    /**
     * 删除定点顺序表第 i 个元素,定点数减1
     */
    @Override
    public void removeVertex(int i) {

        int n = this.vertexCount();  //原顶点数

        if(i >= 0 && i < n){
            this.vertexList.remove(i);  //删除顶点顺序表第i个元素,顶点数减1

            for (int j = i + 1; j < n; j++) {  //第i+1 ~ n-1行元素上移一行
                for (int k = 0; k < n; k++) {
                    this.matrix.set(j - 1,k,this.matrix.get(j,k));
                }
            }

            for (int j = 0; j < n; j++) {
                for (int k = i + 1; k <= n; k++) {  //第 i + 1 ~ n -1列元素左移一列
                    this.matrix.set(j,k-1,this.matrix.get(j,k));
                }
            }

            this.matrix.setRowsColumns(n - 1,n- 1);  //邻接矩阵少一行,少一列
        }else{
            throw new IndexOutOfBoundsException("i = " + i);  //抛出序号越界异常
        }
    }

    /**
     * 返回顶点vi在vj之后的后继邻接顶点序号,若 j == -1,返回vi的第一个邻接顶点序号
     * 若不存在后继邻接顶点,返回-1,
     */
    @Override
    public int next(int i, int j) {

        int n = this.vertexCount();

        if(i >= 0 && i < n && j >= -1 && j < n && i != j){
            for (int k = j + 1; k < n; k++) {
                if(this.matrix.get(i,k) > 0 && this.matrix.get(i,k) < MAX_WEIGHT){  //权值表示有边
                    return k;
                }
            }
        }

        return -1;
    }

    /**
     * 插入边<vi,vj>,权值为weight
     */
    @Override
    public void insertEdge(int i, int j, int weight) {

        if(i != j){  //不能表示自身环
            if(weight <= 0 || weight > AbstractGraph.MAX_WEIGHT){  //边的权值容错,视为无边,取值为 ∞
                weight = MAX_WEIGHT;
            }

            this.matrix.set(i,j,weight);  //设置矩阵元素[i,j]为weight,若i,j越界,抛出序号越界异常
        }else{
            throw new IllegalArgumentException("不能插入自身环, i = " + i + ", j = " + j);
        }
    }

    /**
     * 插入一条边
     * @param edge      Triple
     */
    public void insertEdge(Triple edge){

        this.insertEdge(edge.row,edge.column,edge.value);
    }

    /**
     * 删除边<vi,vj>,忽略权值
     */
    @Override
    public void removeEdge(int i, int j) {

        if(i != j){
            this.matrix.set(i,j,MAX_WEIGHT);  //设置边的权值为 +∞ ,若i,j越界,抛出序号越界异常
        }
    }

    /**
     * 删除一条边,忽略权值
     */
    public void removeEdge(Triple edge){

       this.removeEdge(edge.row,edge.column);
    }

    /**
     * 返回<vi,vj>边的权值,用于图的最小生成树
     */
    @Override
    public int weight(int i, int j) {

        return this.matrix.get(i,j);  //返回矩阵元素[i,j]值,若i,j越界,抛出序号越界异常
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(super.toString()+"邻接矩阵: \n");

        int n = this.vertexCount();  //顶点数

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(this.matrix.get(i,j) == AbstractGraph.MAX_WEIGHT){
                    sb.append("∞").append("\t");
                }else{
                    sb.append(this.matrix.get(i,j)).append("\t");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
