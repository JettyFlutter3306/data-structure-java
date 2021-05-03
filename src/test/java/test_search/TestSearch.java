package test_search;

import cn.element.search.BinarySearch;
import org.junit.Test;

/**
 * 测试查找算法
 */
public class TestSearch {

    /**
     * 测试二分查找
     */
    @Test
    public void test01(){

        Integer[] arr = {8,17,26,32,40,72,87,99};

        int i = BinarySearch.binarySearch(arr, 72);

        System.out.println("i = " + i);  //i = 5
    }
}
