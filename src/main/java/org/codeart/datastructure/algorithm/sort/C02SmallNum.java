package org.codeart.datastructure.algorithm.sort;

/**
 * 使用归并排序的方法解决小和问题
 * 在一个数组中,每一个数左边比当前数小的数累加起来,叫做这个数组的小和.求一个数组的小和
 */
public class C02SmallNum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    /**
     * arr[l...r]既要排好序,也要求小和返回
     * 所有merge时,产生的小和累加
     * 左排序,右排序
     */
    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }

        int mid = l + ((r - l) >> 1);

        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] helper = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int ans = 0;

        // 简而言之就是求出右边一组有多少个比当前值大的数,然后累加,然后下面照样归并排序
        while (p1 <= m && p2 <= r) {
            ans += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;  // 求小和关键代码
            helper[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            helper[i++] = arr[p1++];
        }

        while (p2 <= r) {
            helper[i++] = arr[p2++];
        }

        for (i = 0; i < helper.length; i++) {
            arr[l + i] = helper[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] values = {1, 3, 8, 2, 5, 7, 9};
        System.out.println(smallSum(values));
    }

}
