package testList;

import cn.geeklbd.list.Node;
import cn.geeklbd.list.SinglyList;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void test01(){

        String s = singlyList.get(3);

        System.out.println("s = " + s);

        singlyList.set(5,"ea");

        System.out.println("singlyList = " + singlyList);

        System.out.println(singlyList.size());
    }

    @Test
    public void test02(){

        String s = singlyList.remove(5);

        System.out.println("s = " + s);

        System.out.println(singlyList);
    }

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

    @Test
    public void test04(){

        String vd = singlyList.remove("sff");

        System.out.println("singlyList = " + singlyList);
    }

    @Test
    public void test05(){

        Integer[] intArr = {1,32,54,90,52,12,35,78,92,10};

        SinglyList<Integer> list = new SinglyList<>(intArr);

        double average = singlyList.average(list);

        System.out.println("average = " + average);
    }

    @Test
    public void test06(){

        SinglyList<String> list = SinglyList.createReverse(arr);

        System.out.println("cn.geeklbd.list = " + list);

        SinglyList.reverse(list);

        System.out.println("cn.geeklbd.list = " + list);
    }
}
