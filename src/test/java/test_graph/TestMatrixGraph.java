package test_graph;

import org.codeart.datastructure.graph.MatrixGraph;
import org.codeart.datastructure.matrix.Triple;
import org.junit.Test;

public class TestMatrixGraph {

    /**
     * 测试创建带权图
     */
    @Test
    public void test01(){

        String[] vertices = {"A","B","C","D","E"};  //带权无向图的顶点集合

        Triple[] edges = {
                new Triple(0,1,45),
                new Triple(0,2,28),
                new Triple(0,3,10),
                new Triple(1,0,45),
                new Triple(1,2,12),
                new Triple(1,4,21),
                new Triple(2,0,28),
                new Triple(2,1,12),
                new Triple(2,3,17),
                new Triple(2,4,26),
                new Triple(3,0,10),
                new Triple(3,2,17),
                new Triple(3,4,15),
                new Triple(4,1,21),
                new Triple(4,2,26),
                new Triple(4,3,15),
        };  //边集合

        MatrixGraph<String> graph = new MatrixGraph<>(vertices,edges);

        System.out.println("带权无向图: \n" + graph);

        int i = graph.insertVertex("F");  //插入顶点F,扩容
        graph.insertEdge(3,i,13);  //插入边(D,F,13)
        graph.insertEdge(new Triple(i,3,13));  //插入边(F,D,13)
        graph.insertEdge(4,i,11);  //插入边(E,F,11)
        graph.insertEdge(new Triple(i,4,11));  //插入边(F,E,11)

        System.out.println("======================");

        System.out.println("带权无向图: \n" + graph);

        System.out.println("==========================");
        System.out.println("深度优先遍历: ");

        for (int j = 0; j < graph.vertexCount(); j++) {  //测试遍历无向图
            graph.dfsTraverse(j);
        }

        System.out.println("==========================");
        System.out.println("广度优先遍历: ");

        for (int j = 0; j < graph.vertexCount(); j++) {
            graph.bfsTraverse(j);
        }

        System.out.println("=========================");
        System.out.println("最小生成树: ");

        graph.minSpanTree();  //测试最小生成树

        System.out.println("==========================");
        graph.shortestPath(0);  //测试最短路径 Dijkstra算法

        System.out.println("==========================");
        graph.shortestPath();  //测试每对顶点间的最短路径  Floyd算法

    }
}
