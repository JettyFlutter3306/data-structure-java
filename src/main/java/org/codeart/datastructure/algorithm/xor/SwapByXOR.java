package org.codeart.datastructure.algorithm.xor;

/**
 * 使用异或的性质进行两数之间交换
 * 1) 0 ^ N = N
 * 2) N ^ N = 0
 * 3) 异或运算就是无进位相加运算
 */
public class SwapByXOR {

    /**
     * 不适用额外空间进行两数交换
     * 分析:
     * a = 10  b = 20
     * 1) a = a ^ b ----> a = 10 ^ 20              b = 20
     * 2) b = a ^ b ----> a = 10 ^ 20              b = 10 ^ 20 ^ 20 = 10
     * 3) a = a ^ b ----> a = 10 ^ 20 ^ 10 = 20    b = 10
     */
    public static void swapTwoNumber() {
        int a = 10;
        int b = 20;

        a = a ^ b;  // a + b
        b = a ^ b;  // a - b
        a = a ^ b;  // a - b

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    /**
     * 找到那个出现次数为奇数的数,假设其他数出现次数为偶数
     * 使用异或 eor ^ number 一次遍历即可
     */
    public static void findTheOddTimesNumber() {
        int[] arr = {2, 2, 3, 3, 3, 4, 4, 4, 4, 3, 5};
        int eor = 0;

        for (int j : arr) {
            eor ^= j;
        }

        System.out.println(eor);
    }

    /**
     * 在一个数组中找到那两个出现次数为奇数的数,其他的数出现次数都是偶数次
     */
    public static void findTheTwoNumbersOddTimes() {
        int[] arr = {2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 6};
        int eor = 0;

        for (int k : arr) {
            eor ^= k;
        }

        // a和b是两种数
        // eor != 0
        // eor最右侧的1,提取出来
        // eor:      00110010110111000
        // rightOne: 00000000000001000
        int rightOne = eor & (-eor);  // 提取出最右侧的1
        int onlyOne = 0;  // eor'

        for (int j : arr) {
            // j        = 111100011110000
            // rightOne = 000000000010000
            if ((j & rightOne) != 0) {  // 表示在那个位置上一定有 1 存在
                onlyOne ^= j;
            }
        }

        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    /**
     * 提取一串二进制数最右侧的1位置
     * a = a & (~a + 1)
     *
     * 其实也就是 a = a & -a
     */
    public static void extractRightestOne() {
        int a = 10;

        System.out.println(Integer.toBinaryString(a));

        a = a & -a;

        System.out.println(Integer.toBinaryString(a));
    }

    /**
     * 在一个数组中找到出现了K次的数,假设其他的数都出现了M次
     * 要求: 时间复杂度 O(N)
     *       空间复杂度 O(1)
     */
    public static void findKTimes(int k, int m) {
        int[] arr = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5};
        int[] t = new int[32];

        // t[0]代表0位置的1出现了几次
        // t[1]代表i位置的1出现了几次
        for (int num : arr) {
            for (int i = 0; i < 31; i++) {
                // 表示在i位置上不是0
                if (((num >> i) & 1) != 0) {
                    t[i]++;
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < 31; i++) {
            if (t[i] % m != 0) {  // 在第i位置上有1
                ans |= (1 << i);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        findKTimes(1, 4);
    }

}
