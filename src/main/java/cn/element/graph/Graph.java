package cn.element.graph;

/**
 * 声明抽象数据类型 Graph(图结构)
 */
public interface Graph<T> {

    int vertexCount();  //返回定点数

    T getVertex(int i);  //返回定点vi元素

    void setVertex(int i,T x);  //设置顶点vi元素为x

    int insertVertex(T x);  //插入元素值为x的顶点,返回定点序号

    void removeVertex(int i);  //删除定点vi及其关联的边

    int next(int i,int j);  //返回vi在vj之后的后继临接定点序号

    void insertEdge(int i,int j,int weight);  //插入边<vi,vj>,权值为weight

    void removeEdge(int i,int j);  //删除边<vi,vj>

    int weight(int i,int j);  //返回<vi,vj>边的权值

    void dfsTraverse(int i);  //非联通图的一次深度优先搜索遍历,从顶点vi出发

    void bfsTraverse(int i);  //非联通图的一次广度优先搜索遍历,从顶点vi出发

    void minSpanTree();  //构造带权无向图的最小生成树,Prim算法

    void shortestPath(int i);  //求带权图顶点vi的单源最短路径,Dijkstra算法

    void shortestPath();  //求带权图每对顶点间的最短路径及长度,Floyd算法
}
