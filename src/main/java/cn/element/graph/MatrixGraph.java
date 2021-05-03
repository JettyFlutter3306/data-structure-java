package cn.element.graph;

import cn.element.matrix.Matrix;
import cn.element.matrix.Triple;

/**
 * 声明邻接矩阵表示的带权图类
 * 继承抽象图类,继承了常量MAX_WEIGHT和成员变量vertexList
 * 增加了新的成员变量 matrix 存储图的邻接矩阵
 */
public class MatrixGraph<T> extends AbstractGraph<T> {

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

    @Override
    public int insertVertex(T x) {

        return 0;
    }

    @Override
    public void removeVertex(int i) {

    }

    @Override
    public int next(int i, int j) {

        return 0;
    }

    @Override
    public void insertEdge(int i, int j, int weight) {

    }

    /**
     * 插入一条边
     * @param edge      Triple
     */
    public void insertEdge(Triple edge){

        this.insertEdge(edge.row,edge.column,edge.value);
    }

    @Override
    public void removeEdge(int i, int j) {

    }

    @Override
    public int weight(int i, int j) {

        return 0;
    }

    @Override
    public void dfsTraverse(int i) {

    }

    @Override
    public void bfsTraverse(int i) {

    }

    @Override
    public void minSpanTree() {

    }

    @Override
    public void shortestPath(int i) {

    }

    @Override
    public void shortestPath() {

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(super.toString()+"邻接矩阵: \n");

        int n = this.vertexCount();  //顶点数

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(this.matrix.get(i,j) == MAX_WEIGHT){
                    sb.append(" ∞");
                }else{
                    sb.append(String.format("%6d",this.matrix.get(i,j)));
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
