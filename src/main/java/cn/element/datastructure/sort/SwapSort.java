package cn.element.datastructure.sort;

import java.util.Arrays;

/**
 * 交换排序算法(Swap Sort)
 * 基于交换的排序算法有两种: 冒泡排序 和 快速排序
 *
 */
public class SwapSort {

    public static void bubbleSort(int[] keys) {
        bubbleSort(keys,true);
    }

    /**
     * 冒泡排序(Bubble Sort)
     * 算法描述:
     *   比较相邻两个元素大小,如果反序,则交换,若按升序排列,每趟将数据序列中的最大元素交换到最后位置
     *   就像气泡从水里冒出一样
     *   其中布尔变量 exchange 用作本趟扫描是否交换的标记,如果一趟扫描没有数据交换,则排序完成
     *   不必进行下一轮交换
     */
    public static void bubbleSort(int[] keys,boolean asc) {
        String str = asc ? "升序" : "降序";

        System.out.println("冒泡排序: " + str);

        boolean exchange = true;  //是否交换的标记

        for (int i = 0; i < keys.length - 1 && exchange; i++) {  //比较的轮数  总共需要 keys.length - 1 轮
            exchange = false;

            for (int j = 0; j < keys.length - i - 1; j++) {  //每一轮比较的次数  keys.length - i - 1
                if(asc ? keys[j] > keys[j+1] : keys[j] < keys[j+1]){
                    IArrays.swap(keys,j,j+1);

                    exchange = true;
                }
            }

            System.out.println("第" + i + "趟: " + Arrays.toString(keys));
        }
    }

    public static void quickSort(int[] keys) {
        quickSort(keys,0,keys.length - 1);
    }

    /**
     * 快速排序(Quick Sort):
     * 时间复杂度:
     *      最好情况: O(n * log n)
     *      最坏情况: O(n²)
     * 空间复杂度:
     *      O(log n) ~ O(n)
     * 快速排序是一种分区交换的排序算法而且是不稳定的
     * 算法描述:
     *   在数据序列中选择一个元素最为基准值,每趟从数据序列的两端开始交替进行,将小于基准值的元素交换到序列前端,
     *   将大于基准值的元素交换到序列后端,介于两者之间的位置则成为基准值的最终位置,同时,序列被划分为两个子序列,再分别
     *   对两个子序列进行快速排序直到子序列长度为1,则完成排序
     *
     * 对存于 keys 数组 begin ~ end 之间的子序列进行一趟快速排序,递归算法
     *   1.选取子序列第一个元素keys[i]作为基准值vot,空出keys[i]元素位置
     *   2.在子序列后端寻找小于基准值的元素,交换到序列前端,即比较keys[j]元素与基准值,若小则将keys[j]元素移动到序列前端keys[j]
     *     位置,i++,此时keys[j]位置空出
     *   3.在子序列前端寻找大于基准值的元素,交换到序列后端,再比较keys[i]元素与基准值,若大则将keys[i]元素移动到序列后端的keys[j]
     *     位置,j--,keys[i]位置空出,不移动与基准值相等元素
     *   4.重复执行步骤2,步骤3 直到i == j,表示子序列中的每个元素都与基准值比较过了,并已将小于基准值的元素移动到前端,将大于基准值
     *     的元素移动到后端,当 i(j) 位置则是基准值的最终位置
     *   5.一趟快速排序将数据序列划分成两个子序列,范围分别为begin ~ j-1,i+1 ~ end,每个子序列均较短,再对两个子序列分别进行
     *     快速排序,直到子序列长度为1
     */
    public static void quickSort(int[] keys, int begin, int end) {
        if (begin >= 0 && end >= 0 && end < keys.length && begin < end) {  //序列有效
            int i = begin, j = end;  //i,j下标分别从子序列的前后两端开始

            int vot = keys[i];

            while (i != j) {
                while (i < j && keys[j] >= vot) {  //从后向前寻找较小值,不移动与基准值相等元素
                    j--;
                }

                if (i < j) {
                    keys[i++] = keys[j];  //子序列从后端较小元素向前移动
                }

                while (i < j && keys[i] <= vot) {  //从前向后寻找较大值,不移动与基准值相等的元素
                    i++;
                }

                if (i < j) {
                    keys[j--] = keys[i];  //子序列前端较大元素向后移动
                }
            }

            keys[i] = vot;  //基准值到大最终位置

            System.out.println(begin + ".." + end + ", vot = " + vot + " " + Arrays.toString(keys));

            quickSort(keys, begin, j - 1);  //前端子序列再排序,递归调用
            quickSort(keys, i + 1, end);  //后端子序列再排序,递归调用
        }
    }



}
