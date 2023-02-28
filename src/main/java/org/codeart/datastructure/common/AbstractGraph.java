package org.codeart.datastructure.common;

import org.codeart.datastructure.list.SeqList;
import org.codeart.datastructure.list.SinglyList;
import org.codeart.datastructure.matrix.Matrix;
import org.codeart.datastructure.matrix.Triple;
import org.codeart.datastructure.queue.LinkedQueue;

/**
 * 声明抽象图类
 * @param <T>  顶点元素类型
 */
public abstract class AbstractGraph<T> extends AbstractCollection<T> implements Graph<T> {

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
    public int vertexCount() {
        return this.vertexList.size();  //返回图的顶点顺序表的元素个数
    }

    /**
     * 返回顶点元素vi,若i越界那么则抛出异常
     * @param i         索引
     * @return          T
     */
    public T getVertex(int i) {
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
            if (!visited[j]) {  //若顶点vj未被访问
                System.out.print("{");

                this.depthFirst(j, visited);  //从顶点vj出发的一次深度优先搜索

                System.out.print("}");
            }

            j = (j + 1) % this.vertexCount();  //在其他连通分量中寻找未被访问的结点
        } while (j != i);

        System.out.println();
    }

    /**
     * 从顶点vi出发的一次深度优先搜索,遍历一个连通分量,visited指定访问标记数组,递归算法
     */
    private void depthFirst(int i, boolean[] visited) {
        System.out.print(this.getVertex(i) + " ");  //访问结点vi

        visited[i] = true;  //设置访问标记

        int j = this.next(i, -1);  //返回vi的第一个邻接顶点序号

        while (j != -1) {  //若vi存在一个邻接顶点vj
            if (!visited[j]) {  //且顶点vj未被访问
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
            if (!visited[j]) {  //若顶点vj未被访问
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

        Queue<Integer> queue = new LinkedQueue<>();  //创建链式队列

        queue.add(i);  //访问过的顶点vi序号入队,自动转换成Integer(自动装箱操作)

        while (!queue.isEmpty()) {  //当队列不为空的时候循环开始
            i = queue.poll();  //出队,自动拆箱成int

            for (int j = this.next(i, -1); j != -1; j = this.next(i, j)) {  //j依次获得vi的所有邻接顶点
                if (!visited[j]) {  //若顶点vj未被访问过
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
                if (triples[j].value < minWeight) {  //若存在更小的权值,则更新最小值变量
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

                if (weight < triples[j].value) {  //若(tv,v)边比第j条边的权值更小,则替换
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

    /**
     * 求带权图中顶点vi的单源最短路径,Dijkstra算法
     */
    @Override
    public void shortestPath(int i) {
        int n = this.vertexCount();  //图的顶点数

        boolean[] vSet = new boolean[n];  //已经求出最短路径的顶点集合,初始值全为false

        vSet[i] = true;  //标记源点vi在集合S中

        int[] dist = new int[n];  //最短路径长度
        int[] path = new int[n];  //最短路径的终点的前一个顶点

        for (int j = 0; j < n; j++) {  //初始化dist和path数组
            dist[j] = this.weight(i,j);

            path[j] = (j != i && dist[j] < MAX_WEIGHT) ? i : -1;
        }

        for (int j = (i + 1) % n; j != i; j = (j + 1) % n) {  //寻找从vi到vj的最短路径,vj在V-S集合中
            int mindDist = MAX_WEIGHT;  //求路径长度最小
            int min = 0;  //最短路径下标

            for (int k = 0; k < n; k++) {
                if (!vSet[k] && dist[k] < mindDist) {
                    mindDist = dist[k];  //路径长度最小值
                    min = k;  //路径长度最小值下标
                }
            }

            if (mindDist == MAX_WEIGHT) {  //若没有其他最短路径则算法结束,此语句对非连通图必须
                break;
            }

            vSet[min] = true;  //确定一条最短路径(vi,min),终点min并入集合S

            for (int k = 0; k < n; k++) {  //调整从vi到V-S中其他顶点的最短路径以及长度
                if (!vSet[k] && this.weight(min,k) < MAX_WEIGHT && dist[min] + this.weight(min,k) < dist[k]) {
                    dist[k] = dist[min] + this.weight(min,k);  //用更短路径替换

                    path[k] = min;  //最短路径经过min顶点
                }
            }
        }

        System.out.println(this.getVertex(i) + "顶点的单源最短路径: ");

        for (int j = 0; j < n; j++) {  //输出顶点vi的单源最短路径
            if (j != i) {
                SinglyList<T> pathLink = new SinglyList<>();  //路径单链表,记录最短路径经过的各个顶点,用于反序

                pathLink.insert(0, this.getVertex(j));  //单链表插入最短路径终点vj

                for (int k = path[j]; k != i && k != j && k != -1; k = path[k]) {  //寻找从vi到vj的最短路径
                    pathLink.insert(0, this.getVertex(k));  //单链表头插入经过的顶点,反序
                }

                pathLink.insert(0, this.getVertex(i));  //最短路径的起点vi

                System.out.print(pathLink + "长度" + (dist[j] == MAX_WEIGHT ? "∞" :dist[j]) + ", ");
            }
        }

        System.out.println();
    }

    /**
     * 求带权图每对顶点间的最短路径Floyd算法
     */
    @Override
    public void shortestPath() {
        int n = this.vertexCount();  //图的顶点数

        Matrix path = new Matrix(n);  //最短路径
        Matrix dist = new Matrix(n);  //长度矩阵,初值为0

        for (int i = 0; i < n; i++) {  //初始化dist,path矩阵
            for (int j = 0; j < n; j++) {
                int w = this.weight(i, j);

                dist.set(i, j, w);  //dist初值是图的邻接矩阵

                path.set(i, j, (i != j && w < MAX_WEIGHT ? i : -1));
            }
        }

        for (int k = 0; k < n; k++) {  //以vk作为其他路径的中间顶点
            for (int i = 0; i < n; i++) {  //测试每对从vi到vj路径长度是否更短
                if (i != k) {
                    for (int j = 0; j < n; j++) {
                        if (j != k && j != i && dist.get(i, j) > dist.get(i, k) + dist.get(k, j)) {  //若更短,则替换
                            dist.set(i, j, dist.get(i, k) + dist.get(k, j));

                            path.set(i, j, path.get(k, j));
                        }
                    }
                }
            }
        }

        System.out.println("每对顶点间的最短路径如下: ");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    System.out.println(toPath(path, i, j) + "长度" + (dist.get(i, j) == MAX_WEIGHT ? "∞" : dist.get(i, j) + ", "));
                }
            }

            System.out.println();
        }
    }

    /**
     * 返回path路径矩阵中从顶点vi从vj的一条路径字符串
     */
    private String toPath(Matrix path, int i, int j) {
        SinglyList<T> pathLink = new SinglyList<>();  //单链表,记录最短路径经过的顶点,用于反序

        pathLink.insert(0, this.getVertex(j));  //单链表插入最短路径终点vj

        for (int k = path.get(i, j); k != i && k != j && k != -1; k = path.get(i, k)) {
            pathLink.insert(0, this.getVertex(k));  //单链表头插入经过的顶点,反序
        }

        pathLink.insert(0, this.getVertex(i));  //最短路径的起点vi

        return pathLink.toString();
    }

    @Override
    public String toString() {
        return "顶点集合: " + this.vertexList + "\n";
    }
}
