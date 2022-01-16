package test_list;

import cn.element.datastructure.list.CirDoublyList;
import org.junit.Test;

public class TestCirDoublyList {

    @Test
    public void test01(){

        CirDoublyList<Integer> list = new CirDoublyList<>();

        list.insert(5);
        list.insert(4);
        list.insert(1);
        list.insert(7);
        list.insert(8);

        System.out.println("list = " + list);
    }
}
