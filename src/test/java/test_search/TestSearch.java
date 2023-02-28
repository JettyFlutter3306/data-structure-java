package test_search;

import org.codeart.datastructure.search.BinarySearch;
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
     * 找出小于或等于目标值的最大索引
     */
    @Test
    public void testSearchTheLeft() {
        int[] arr = {8, 17, 26, 32, 32, 40, 72, 87, 99};
        int i = BinarySearch.searchTheBiggestIndex(arr, 30);
        System.out.println("i = " + i);
    }

    /**
     * 找出大于或等于目标值的最小索引
     */
    @Test
    public void testSearchTheRight() {
        int[] arr = {8, 17, 26, 32, 32, 40, 72, 87, 99};
        int i = BinarySearch.searchTheSmallestIndex(arr, 30);
        System.out.println("i = " + i);
    }

    /**
     * 找到数组中第一个最小值
     */
    @Test
    public void testFindRegionSmallest() {
        int[] arr = {15, 5, 10, 12, 18, 4, 17, 80, 1};
        int i = BinarySearch.getLessIndex(arr);
        System.out.println("i = " + i);
    }


}
