package cn.element.search;

/**
 * 二分查找算法
 * 二分查找(Binary Search)是一种典型的采用分治策略的算法它将问题分解成规模更小的子问题
 * 分而治之,逐一解决,二分查找的两个条件是:顺序存储,数据元素排序
 */
public class BinarySearch {

    /**
     * 已知value数组元素按升序排序,在begin~end范围内,二分查找关键字为key元素,若查找成功,则返回下标
     * 否则返回-1,若begin,end越界,返回-1,若key == null,抛出空对象异常
     * @param values            排序数组
     * @param begin             开始位置
     * @param end               结束位置
     * @param key               关键字
     * @param <T>               泛型
     * @return                  下标
     */
    public static <T extends Comparable<? super T>> int binarySearch(T[] values,int begin,int end,T key){

        while(begin <= end){  //边界有效
            int mid = (begin + end) / 2;  //取中间位置,当前比较元素位置

            if(key.compareTo(values[mid]) == 0){  //两对象相等
                return mid;
            }

            if(key.compareTo(values[mid]) < 0){  //key对象较小
                end = mid - 1;  //查找范围缩小到前半段
            }else{
                begin = mid + 1;  //查找范文缩小到后半段
            }
        }

        return -1;  //查找不成功返回-1
    }

    public static <T extends Comparable<? super T>> int binarySearch(T[] value,T key){

        return binarySearch(value,0,value.length - 1,key);
    }

}
