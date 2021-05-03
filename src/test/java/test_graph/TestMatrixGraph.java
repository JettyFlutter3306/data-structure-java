package test_graph;

import cn.element.graph.MatrixGraph;
import cn.element.matrix.Triple;
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

    }
}
