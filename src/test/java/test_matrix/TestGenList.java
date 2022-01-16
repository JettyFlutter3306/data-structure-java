package test_matrix;

import cn.element.datastructure.matrix.GenList;
import cn.element.datastructure.matrix.GenListBuilder;
import org.junit.Test;

public class TestGenList {

    /**
     * 测试广义表的创建
     */
    @Test
    public void test01(){

        String[] atoms = {"a","b"};

        GenList<String> genListA = new GenList<>(atoms);  //由原子数组构造广义表

        System.out.println("genListA = " + genListA);
        System.out.println("size = " + genListA.size());
        System.out.println("depth = " + genListA.depth());

        GenList<String> genListB = new GenList<>();  //构造广义表

        genListB.insert("c");
        genListB.insert(genListA);

        System.out.println("genListB = " + genListB);
        System.out.println("size = " + genListB.size());
        System.out.println("depth = " + genListB.depth());

        GenList<String> genListC = new GenList<>();

        genListC.insert("d");
        genListC.insert(genListA);
        genListC.insert(genListB);

        System.out.println("genListC = " + genListC);
        System.out.println("size = " + genListC.size());
        System.out.println("depth = " + genListC.depth());
    }

    /**
     * 测试创建广义表
     */
    @Test
    public void test02(){

        GenList<String> genList = GenListBuilder.createByString("(d,(a,b),(c,(c,b)))");  //构造广义表

        System.out.println("genList = " + genList);
        System.out.println("size = " + genList.size());
        System.out.println("depth = " + genList.depth());
    }
}
