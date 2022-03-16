package cn.element.datastructure.algorithm.merge;

/**
 * 求一个数组中的前缀和子数组在一个给定范围内的个数
 */
public class C04CountOfRange {

    public static int countOfRange(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        long[] sum = new long[arr.length];
        sum[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        
        return process(sum, 0, arr.length - 1, lower, upper);
    }

    /**
     * @param sum   原始数组
     * @param l     左边界
     * @param r     右边界
     * @param lower 下界
     * @param upper 上界
     */
    private static int process(long[] sum, int l, int r, int lower, int upper) {
        if (l == r) {
            return sum[l] >= lower && sum[l] <= upper ? 1 : 0;
        }
        
        int mid = l + ((r - l) >> 1);
        
        return process(sum, l, mid, lower, upper) + 
                process(sum, mid + 1, r, lower, upper) + 
                merge(sum, l, mid, r, lower, upper);
    }

    private static int merge(long[] arr, int l, int m, int r, int lower, int upper) {
        int ans = 0;
        int windowL = l;
        int windowR = l;
        
        // [windowL, windowR)
        for (int i = m + 1; i <= r; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            
            while (windowR <= m && arr[windowR] <= max) {
                windowR++;
            }
            
            while (windowL <= m && arr[windowL] < min) {
                windowL++;
            }
            
            ans += windowR - windowL;
        }
        
        long[] help = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        
        while (p1 <= m && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        
        return ans;
    }

    public static void main(String[] args) {
        int[] values = {2, 5, 9, 4};
        int lower = 10;
        int upper = 20;
        System.out.println(countOfRange(values, lower, upper));
    }


}
