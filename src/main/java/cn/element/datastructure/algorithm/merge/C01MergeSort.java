package cn.element.datastructure.algorithm.merge;

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

        // 要么p1越界了,要么p2越界了,开始拷贝剩下的数据到helper数组里面
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
        mergeSort(values);
        System.out.println(Arrays.toString(values));
    }

}
