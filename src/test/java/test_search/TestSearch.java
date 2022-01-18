package test_search;

import cn.element.datastructure.search.BinarySearch;
import org.junit.Test;

/**
 * 测试查找算法
 */
public class TestSearch {

    /**
     * 测试二分查找
     */
    @Test
    public void testBinarySearch() {
        Integer[] arr = {8, 17, 26, 32, 40, 72, 87, 99};

        int i = BinarySearch.binarySearch(arr, 72);

        System.out.println("i = " + i);  //i = 5
    }

    /**
     * 二分查找大于某个数的最左的位置
     */
    @Test
    public void testSearchTheLeft() {
        int[] arr = {8, 17, 26, 32, 32, 40, 72, 87, 99};

        int i = BinarySearch.nearestIndex(arr, 30);

        System.out.println("i = " + i);
    }

    @Test
    public void testFindRegionSmallest() {
        int[] arr = {15, 5, 10, 15, 18, 5, 17, 2, 99};

        int i = BinarySearch.getLessIndex(arr);

        System.out.println("i = " + i);
    }


}
