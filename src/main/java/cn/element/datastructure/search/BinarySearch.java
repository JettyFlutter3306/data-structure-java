package cn.element.datastructure.search;

/**
 * 二分查找算法
 * 二分查找(Binary Search)是一种典型的采用分治策略的算法它将问题分解成规模更小的子问题
 * 分而治之,逐一解决,二分查找的两个条件是:顺序存储,数据元素排序
 */
public class BinarySearch {

    /**
     * 已知value数组元素按升序排序,在begin~end范围内,二分查找关键字为key元素,若查找成功,则返回下标
     * 否则返回-1,若begin,end越界,返回-1,若key == null,抛出空对象异常
     * 为什么不用 mid = (begin + end) / 2 ??
     * 因为有溢出的风险,而begin + ((end - begin) >> 1) 不会出现溢出的情况,而且速度更快!
     *
     * @param values 排序数组
     * @param begin  开始位置
     * @param end    结束位置
     * @param key    关键字
     * @param <T>    泛型
     * @return 下标
     */
    public static <T extends Comparable<? super T>> int binarySearch(T[] values, int begin, int end, T key) {
        if (values == null || values.length == 0) {
            return -1;
        }

        while (begin <= end) {  //边界有效
            int mid = begin + ((end - begin) >> 1);  //取中间位置,当前比较元素位置

            if (key.compareTo(values[mid]) == 0) {  //两对象相等
                return mid;
            } else if (key.compareTo(values[mid]) < 0) {  //key对象较小
                end = mid - 1;  //查找范围缩小到前半段
            } else {
                begin = mid + 1;  //查找范文缩小到后半段
            }
        }

        return -1;  //查找不成功返回-1
    }

    public static <T extends Comparable<? super T>> int binarySearch(T[] value, T key) {
        return binarySearch(value, 0, value.length - 1, key);
    }

    /**
     * 找出一个数组中,小于或等于目标值的最大索引
     * 
     */
    public static int searchTheBiggestIndex(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int index = -1;

        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (arr[mid] <= target) {
                index = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return index;
    }

    /**
     * 在数组中,找到满足 >= value 的最左的位置
     */
    public static int searchTheSmallestIndex(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int index = -1;

        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (arr[mid] >= target) {
                index = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return index;
    }

    /**
     * 获取一个数组中的第一个局部最小值
     */
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }

        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int left = 1;
        int right = arr.length - 1;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return left;
    }

}
