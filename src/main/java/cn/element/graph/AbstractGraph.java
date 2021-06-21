package cn.element.graph;

import cn.element.list.SeqList;
import cn.element.matrix.Triple;
import cn.element.queue.LinkedQueue;
import cn.element.queue.MyQueue;

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

    /**
     * 非连通图的一次深度优先搜索遍历,从顶点vi出发
     */
    @Override
    public void dfsTraverse(int i) {

        boolean[] visited = new boolean[this.vertexCount()];  //访问标记数组,元素初始值为false

        int j = i;

        do {
            if(!visited[j]){  //若顶点vj未被访问
                System.out.print("{");

                this.depthFirst(j, visited);  //从顶点vj出发的一次深度优先搜索

                System.out.print("}");
            }

            j = (j + 1) % this.vertexCount();  //在其他连通分量中寻找未被访问的结点
        } while(j != i);

        System.out.println();
    }

    /**
     * 从顶点vi出发的一次深度优先搜索,遍历一个连通分量,visited指定访问标记数组,递归算法
     */
    private void depthFirst(int i, boolean[] visited) {

        System.out.print(this.getVertex(i) + " ");  //访问结点vi

        visited[i] = true;  //设置访问标记

        int j = this.next(i, -1);  //返回vi的第一个邻接顶点序号

        while(j != -1){  //若vi存在一个邻接顶点vj
            if(!visited[j]){  //且顶点vj未被访问
                depthFirst(j, visited);  //从vj出发的深度优先搜索遍历,递归调用
            }

            j = this.next(i, j);  //返回vi在vj后的后继邻接顶点序号,运行时多态,调用子实现类的next(i, j)方法
        }
    }

    /**
     * 非连通图的一次广度优先搜索遍历,从顶点vi出发
     */
    @Override
    public void bfsTraverse(int i) {

        boolean[] visited = new boolean[this.vertexCount()];  //访问标记数组

        int j = i;

        do {
            if(!visited[j]){  //若顶点vj未被访问
                System.out.print("{");

                this.breadthFirst(j, visited);  //从vj出发的一次广度优先搜索

                System.out.print("}");
            }

            j = (j + 1) % this.vertexCount();  //在其他连通分量中寻找未被访问的结点
        } while (j != i);

        System.out.println();
    }

    /**
     * 从顶点vi出发的一次广度优先搜索,遍历一个连通分量,使用队列
     */
    private void breadthFirst(int i, boolean[] visited) {

        System.out.print(this.getVertex(i) + " ");  //访问结点vi

        visited[i] = true;  //设置访问标记

        MyQueue<Integer> queue = new LinkedQueue<>();  //创建链式队列

        queue.add(i);  //访问过的顶点vi序号入队,自动转换成Integer(自动装箱操作)

        while(!queue.isEmpty()){  //当队列不为空的时候循环开始
            i = queue.poll();  //出队,自动拆箱成int

            for (int j = this.next(i, -1); j != -1; j = this.next(i, j)) {  //j依次获得vi的所有邻接顶点
                if(!visited[j]){  //若顶点vj未被访问过
                    System.out.print(this.getVertex(j) + " ");  //访问顶点vj

                    visited[j] = true;

                    queue.add(j);  //访问过的顶点vj序号入队
                }
            }
        }
    }

    /**
     * Prim算法,构造带权无向图的最小生成树,输出最小生成树的各边及代价
     */
    @Override
    public void minSpanTree() {

        Triple[] triples = new Triple[this.vertexCount() - 1];  //最小生成树的边集合,边数为顶点数 n - 1

        for (int i = 0; i < triples.length; i++) {  //边集合初始化,从顶点v0出发构造
            triples[i] = new Triple(0, i + 1, this.weight(0, i + 1));  //保存从v0到其他各顶点的边
        }

        for (int i = 0; i < triples.length; i++) {  //选择 n - 1 条边,每趟确定一条权值最小的边
            int minWeight = triples[i].value;  //最小权值
            int min = i;  //最小权值的下标

            for (int j = i + 1; j < triples.length; j++) {  //在i~n-1范围内,寻找权值最小的边
                if(triples[j].value < minWeight){  //若存在更小的权值,则更新最小值变量
                    minWeight = triples[i].value;  //最小权值

                    min = j;  //保存当前权值最小边的序号
                }
            }

            //将权值最小的边(由min记得)交换到第i个元素,表示该边加入TE集合
            Triple edge = triples[min];
            triples[min] = triples[i];
            triples[i] = edge;

            //将 i+1 ~ n-1 的其他边用权值更小的边替换
            int tv = edge.column;  //刚并入tv的顶点

            for (int j = i + 1; j < triples.length; j++) {
                int v = triples[j].column;  //原边在v-tv中终点

                int weight = this.weight(tv,v);

                if(weight < triples[j].value){  //若(tv,v)边比第j条边的权值更小,则替换
                    triples[j] = new Triple(tv,v,weight);
                }
            }
        }

        System.out.print("\n最小生成树的边集合: ");

        int minCost = 0;

        for (Triple triple : triples) {  //输出最小生成树的边集合和代价
            System.out.print(triple + " ");

            minCost += triple.value;
        }

        System.out.println(", 最小代价为: " + minCost);
    }

    @Override
    public void shortestPath(int i) {

    }

    @Override
    public void shortestPath() {

    }

    @Override
    public String toString() {

        return "顶点集合: " + this.vertexList + "\n";
    }
}
