package test_search;

import cn.element.datastructure.search.sort_tree.BinarySortedTree;
import org.junit.Test;

/**
 * 二叉排序树测试类
 */
public class TestBinarySortTree {

    /**
     * 测试创建二叉排序树
     */
    @Test
    public void test01(){

        BinarySortedTree<Integer> tree = new BinarySortedTree<>();

        System.out.println("关键字序列: ");

        int i = 0;
        int n = 10;
        int size = 100;

        while (i < n){
            int key = (int) (Math.random() * size);

            System.out.print(key + " ");

            if(tree.add(key)){
                i++;
            }
        }

        System.out.println("\n二叉排序树: \n" + tree);
    }

}
