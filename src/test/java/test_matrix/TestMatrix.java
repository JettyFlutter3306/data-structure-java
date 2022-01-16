package test_matrix;

import cn.element.datastructure.matrix.Matrix;
import org.junit.Test;

/**
 * Matrix测试类
 */
public class TestMatrix {

    /**
     * 测试矩阵对象的创建
     */
    @Test
    public void test01(){

        int[][] value = {{1,2,3},{4,5,6,7,8},{9}};

        Matrix matrix = new Matrix(3,4,value);

        matrix.set(2,3,10);

        System.out.println("matrix = " + matrix);
    }
}
