package test_list;

import org.codeart.datastructure.list.SinglyList;
import org.codeart.datastructure.list.SortedSinglyList;
import org.junit.Test;

public class TestSortedSinglyList {

    private final String[] arr = new String[]{"132","sdf","ad","08","ok","pl"};

    /**
     * 测试 Node<T> search(T x) 方法
     * 返回第一个与key相等的元素
     */
    @Test
    public void test01(){

        SinglyList<String> list = new SortedSinglyList<>(arr);

        System.out.println(list.search("ok"));

        System.out.println(list.size()); //6
    }

    /**
     * 测试 Node<T> insertDifferent(T x)
     * 插入不重复元素,返回插入的元素
     */
    @Test
    public void test02(){

        SinglyList<String> list = new SortedSinglyList<>(arr);

        System.out.println(list.size()); //6

        System.out.println(list.insertDifferent("ok")); //null, 因为插入的元素已经存在了

        System.out.println(list.size()); //6, 列表长度不变

        System.out.println(list.insertDifferent("new")); //new, 插入的元素不存在

        System.out.println(list.size()); //7, 列表长度加一

    }

    /**
     * 测试 T remove(T x)方法
     * 返回删除的元素
     */
    @Test
    public void test03(){

        SinglyList<String> list = new SortedSinglyList<>(arr);


        System.out.println(list.size()); //6

        System.out.println(list.remove("ok"));

        System.out.println(list);

        System.out.println(list.size()); //5
    }

    /**
     * 测试 void addAll(SinglyList<T> cn.geeklbd.list) 方法
     * 实现排序单链表集合并
     */
    @Test
    public void test04(){

        SinglyList<String> list = new SortedSinglyList<>(arr);

        String[] arr1 = new String[]{"ad","safsa","mld","qw","csc"};

        SinglyList<String> list1 = new SortedSinglyList<>(arr1);

        list.addAll(list1);

        System.out.println("cn.geeklbd.list = " + list);
    }
}
