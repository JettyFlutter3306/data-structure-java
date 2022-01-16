package test_list;

import cn.element.datastructure.list.SortedCirDoublyList;
import org.junit.Test;

/**
 * 测排序循环双联表
 */
public class TestSortedCirDoublyList {

    @Test
    public void test01(){

        SortedCirDoublyList<Integer> list = new SortedCirDoublyList<>();

        list.insert(5);
        list.insert(2);
        list.insert(1);
        list.insert(6);
        list.insert(7);
        list.insert(10);

        System.out.println("list = " + list);

        System.out.println(list.isEmpty());
    }
}
