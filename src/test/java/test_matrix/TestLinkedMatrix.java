package test_matrix;

import cn.element.matrix.LinkedMatrix;
import cn.element.matrix.Triple;
import org.junit.Test;

public class TestLinkedMatrix {

    /**
     * 测试输出链式矩阵
     */
    @Test
    public void test01(){

        Triple[] triplesA = {
                new Triple(0,2,11),
                new Triple(0,4,17),
                new Triple(1,1,20),
                new Triple(3,0,19),
                new Triple(3,2,36),
                new Triple(3,5,28),
                new Triple(4,2,50)
        };

        LinkedMatrix matrixA = new LinkedMatrix(5,6,triplesA);

        System.out.println("matrixA: \n" + matrixA);

        matrixA.printMatrix();

        Triple[] triplesB = {
                new Triple(0,2,-11),
                new Triple(0,4,-17),
                new Triple(2,3,51),
                new Triple(3,0,10),
                new Triple(4,1,99)
        };

        LinkedMatrix matrixB = new LinkedMatrix(5,6,triplesB);

        System.out.println("matrixB: \n" + matrixB);

        matrixB.printMatrix();

        matrixA.addAll(matrixB);

        System.out.println("matrixA + matrixB: \n" + matrixA);

        matrixA.printMatrix();

        System.out.println("=========================");

        System.out.println(matrixA.equals(matrixB));

    }
}
