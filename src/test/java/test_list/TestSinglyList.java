package test_list;

import org.codeart.datastructure.list.node.Node;
import org.codeart.datastructure.list.SinglyList;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestSinglyList {

    public static String[] arr = {"X","QW","VD","PO","XZ","VF","UI"};

    public static SinglyList<String> singlyList;

    static {

        singlyList = new SinglyList<>(arr);
    }

    @Before
    public void before(){

        System.out.println(singlyList);
    }

    /**
     * 测试单链表增删改查
     */
    @Test
    public void test01(){

        String s = singlyList.get(3);

        System.out.println("s = " + s);

        singlyList.set(5,"ea");

        System.out.println("singlyList = " + singlyList);

        System.out.println(singlyList.size());
    }

    /**
     * 测试单链表按索引删除
     */
    @Test
    public void test02(){

        String s = singlyList.remove(5);

        System.out.println("s = " + s);

        System.out.println(singlyList);
    }

    /**
     * 测试单链表搜索
     */
    @Test
    public void test03(){

        Node<String> node = singlyList.search("UI");

        System.out.println("node = " + node);

        boolean b = singlyList.contains("QW");

        System.out.println("b = " + b);

        singlyList.insertDifferent("gw");

        System.out.println("singlyList = " + singlyList);

        Node<String> x = singlyList.insertDifferent("X");

        System.out.println(singlyList);
    }

    /**
     * 测试单链表根据关键字删除
     */
    @Test
    public void test04(){

        String vd = singlyList.remove("sff");

        System.out.println("singlyList = " + singlyList);
    }

    /**
     * 测试求单链表平均值
     */
    @Test
    public void test05(){

        Integer[] intArr = {1,32,54,90,52,12,35,78,92,10};

        SinglyList<Integer> list = new SinglyList<>(intArr);

        double average = SinglyList.average(list);

        System.out.println("average = " + average);
    }

    /**
     * 测试单链表逆转
     */
    @Test
    public void test06(){

        SinglyList<String> list = SinglyList.createReverse(arr);

        System.out.println("cn.element.list = " + list);

        SinglyList.reverse(list);

        System.out.println("cn.element.list = " + list);
    }

    /**
     * 测试单链表深拷贝
     */
    @Test
    public void test07() throws IOException, ClassNotFoundException {

        Integer[] intArr = {1,32,54,90,52,12,35,78,92,10};

        SinglyList<Integer> list = new SinglyList<>(intArr);

        SinglyList<Integer> list1 = new SinglyList<>(list);

        System.out.println(list.hashCode());
        System.out.println(list1.hashCode());

    }
}
