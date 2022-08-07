package test_search;

import cn.element.datastructure.hash.HashSet;
import org.junit.Test;

public class TestHashSet {

    @Test
    public void test01() {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            set.add(i);
        }

        System.out.println("set = " + set);
        System.out.println(set.contains(2));
        System.out.println(set.isEmpty());
        System.out.println(set.search(20));
        System.out.println(set.size());
        System.out.println(set.remove(5));
        System.out.println(set);
    }
}
