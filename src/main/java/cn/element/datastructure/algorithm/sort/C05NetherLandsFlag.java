package cn.element.datastructure.algorithm.sort;

import java.util.Arrays;

/**
 * 使用快速排序思想解决荷兰国旗问题
 */
public class C05NetherLandsFlag {
    
    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * arr[l...r]上,以arr[r]位置的数做划分值
     */
    private static int partition(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        } else if (l == r) {
            return 1;
        }
        
        int lessEqual = l - 1;
        int index = l;
        
        while (index < r) {
            if (arr[index] <= arr[r]) {
                swap(arr, index, ++lessEqual);
            }
            
            index++;
        }
        
        swap(arr, ++lessEqual, r);
        return lessEqual;
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] > arr[R]
    private static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) { // L...R L>R
            return new int[]{-1, -1};
        } else if (l == r) {
            return new int[]{l, r};
        }
        
        int less = l - 1; // < 区 右边界
        int more = r; // > 区 左边界
        int index = l;
        
        while (index < more) { // 当前位置，不能和 >区的左边界撞上
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
//				swap(arr, less + 1, index);
//				less++;
//				index++;						
                swap(arr, index++, ++less);
            } else { // >
                swap(arr, index, --more);
            }
        }
        
        swap(arr, more, r); // <[R]   =[R]   >[R]
        return new int[] { less + 1, more };
    }
    
    public static void main(String[] args) {
        
    }

}
