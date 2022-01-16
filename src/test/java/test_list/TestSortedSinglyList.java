package test_list;

import cn.element.datastructure.list.SinglyList;
import cn.element.datastructure.list.SortedSinglyList;
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
    }

    /**
     * 测试 Node<T> insertDifferent(T x)
     * 插入不重复元素,返回插入的元素
     */
    @Test
    public void test02(){

        SinglyList<String> list = new SortedSinglyList<>(arr);

        System.out.println(list.insertDifferent("ok")); //null
    }

    /**
     * 测试 T remove(T x)方法
     * 返回删除的元素
     */
    @Test
    public void test03(){

        SinglyList<String> list = new SortedSinglyList<>(arr);

        System.out.println(list.remove("ok"));

        System.out.println(list);
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
