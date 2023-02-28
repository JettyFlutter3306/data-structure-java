package org.codeart.datastructure.algorithm.list;

/**
 * 使用递归求数组最大值
 */
public class C08GetMax {

    public static int getMax(int[] arr, int l, int r) {
        // 递归出口
        if (l == r) {
            return arr[l];
        }

        // 取中值
        int mid = l + ((r - l) >> 1);
        int leftMax = getMax(arr, l, mid);  // 获得左侧最大值
        int rightMax = getMax(arr, mid + 1, r);  // 获取右侧最大值

        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        int[] values = {1, 5, 89, 8, 1, 3, 5, 4, 9, 8, 12, 57, 65, 100};
        int ans = getMax(values, 0, values.length - 1);
        System.out.println(ans);
    }

}
