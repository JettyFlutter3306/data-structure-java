package cn.element.datastructure.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序有关问题
 */
public class C01MergeSort {

    /**
     * 使用递归实现归并
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    /**
     * 使用非递归方法实现归并排序
     */
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        
        int n = arr.length;
        int step = 1;  // 步长
        
        while (step < n) {
            // 当前左组的第一个位置
            int l = 0;
            while (l < n) {
                int m = l + step - 1;
                
                if (m >= n) {
                    break;
                }
                
                // 求出归并右端边界值,与数组最后一个索引位置比较取较小值
                int r = Math.min(m + step, n - 1);
                
                // 归并l ... m  m + 1 ... r
                merge(arr, l, m, r);
                
                // 得到下一组归并左端边界值
                l = r + 1;
            }
            
            // 防止溢出
            if (step > n / 2) {
                break;
            }
            
            // 每次步长扩大2倍直到溢出
            step <<= 1;
        } 
    }

    /**
     * 把arr[l...r]排成有序序列
     */
    private static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }

        int mid = l + ((r - l) >> 1);

        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] helper = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;

        while (p1 <= m && p2 <= r) {
            helper[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 要么p1越界了,要么p2越界了,开始拷贝剩下的数据到helper数组里面,两个while只会发生一个
        while (p1 <= m) {
            helper[i++] = arr[p1++];
        }

        while (p2 <= r) {
            helper[i++] = arr[p2++];
        }

        // 把helper数组里的元素拷贝回原数组里面去
        for (i = 0; i < helper.length; i++) {
            arr[l + i] = helper[i];
        }
    }

    public static void main(String[] args) {
        int[] values = {14, 41, 2, 4, 8, 21, 1, 6, 9, 4, 5};
        mergeSort2(values);
        System.out.println(Arrays.toString(values));
    }

}
