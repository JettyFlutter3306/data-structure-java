package cn.element.graph;

import cn.element.list.SeqList;

/**
 * 声明抽象图类
 * @param <T>  顶点元素类型
 */
public abstract class AbstractGraph<T> implements Graph<T>{

    protected static final int MAX_WEIGHT = 0x0000ffff;  //最大权值

    protected SeqList<T> vertexList;  //顶点顺序表,存储图的顶点集合

    /**
     * 构造空图,顶点数为0,length指定顶点顺序表容量
     * @param length        容量
     */
    public AbstractGraph(int length) {

        this.vertexList = new SeqList<>(length);  //构造容量为length的空顺序表
    }

    /**
     * 构造空图,顶点数为0
     * 顺序表默认容量为10
     */
    public AbstractGraph() {

        this(10);
    }

    /**
     * 返回图的顶点数
     */
    public int vertexCount(){

        return this.vertexList.size();  //返回图的顶点顺序表的元素个数
    }

    /**
     * 返回顶点元素vi,若i越界那么则抛出异常
     * @param i         索引
     * @return          T
     */
    public T getVertex(int i){

        return this.vertexList.get(i);
    }

    @Override
    public String toString() {

        return "顶点集合: " + this.vertexList + "\n";
    }
}
