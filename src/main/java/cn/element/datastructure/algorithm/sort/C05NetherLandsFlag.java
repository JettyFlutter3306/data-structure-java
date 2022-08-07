package cn.element.datastructure.algorithm.sort;
    
import java.util.Arrays;

/**
 * 使用快速排序思想解决荷兰国旗问题
 */
public class C05NetherLandsFlag {

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * arr[l...r]上,以arr[r]位置的数做划分值
     * 此种方法只能确定一个中间数
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

    /**
     * arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
     * < arr[R] ==arr[R] > arr[R]
     * 此种方法可以确定一批中间数,避免重复计算
     */
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
        return new int[]{less + 1, more};
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int m = partition(arr, l, r);
        process1(arr, l, m - 1);
        process1(arr, m + 1, r);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process2(arr, 0, arr.length - 1);
    }

    private static void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        // 返回的是等于参考值的区间[equalArea[0], equalArea[1]]
        int[] temp = netherlandsFlag(arr, l, r);
        process2(arr, l, temp[0] - 1);
        process2(arr, temp[1] + 1, r);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] equalArea = netherlandsFlag(arr, l, r);
        process3(arr, l, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, r);
    }

    public static void main(String[] args) {
        int[] values1 = {2, 9, 5, 6, 7, 2, 1, 3, 5, 7};
        quickSort1(values1);
        System.out.println(Arrays.toString(values1));

        System.out.println("========================");
        int[] values2 = {2, 8, 9, 3, 5, 8, 0, 12, 1, 3, 5, 7};
        quickSort2(values2);
        System.out.println(Arrays.toString(values2));

        System.out.println("========================");
        int[] values3 = {2, 8, 2, 8, 9, 10, 56, 1, 3, 5, 7};
        quickSort3(values3);
        System.out.println(Arrays.toString(values3));
    }

}
