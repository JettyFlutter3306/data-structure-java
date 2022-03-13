package cn.element.datastructure.algorithm.merge;

/**
 * 求出数组右边数字*2还比当前数字小的个数的总和
 */
public class C03BiggerThanRightTwice {

    public static int biggerThanRightTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
    
        int mid = l + ((r - l) >> 1);

        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int ans = 0;
        // 目前囊括进来的数,是从[m + 1, windowR)
        int windowR = m + 1;

        for (int i = l; i <= m; i++) {
            while (windowR <= r && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }

            ans += windowR - m - 1;
        }

        // 归并排序固定写法
        int[] helper = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;

        while (p1 <= m && p2 <= r) {
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
        int[] values = {1, 1, 2, 5, 9, 2, 4, 1};
        System.out.println(biggerThanRightTwice(values));
    }

}
