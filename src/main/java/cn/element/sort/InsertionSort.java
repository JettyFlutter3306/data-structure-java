package cn.element.sort;

import java.util.Arrays;

/**
 * 插入排序(Insertion Sort)算法思想,每趟将一个元素,按其关键字值的大小插入到它前面已排序的子序列中,
 * 依此重复,直到插入全部元素
 * 插入排序有 直接插入排序, 二分插入排序 和 希尔排序
 */
public class InsertionSort {

    /**
     * 直接插入排序(Straight Insertion Sort)  时间复杂度 O(n²)  空间复杂度O(1)
     * 1.第 i 趟,线性序列{a0,a1,a2,...,ai,...,an-1},设前i个元素构成的子序列{a0,a1,...,an-1}是排序的
     * 将元素ai插入到子序列{a0,a1,...,ai-1}适当位置,使得插入后的子序列仍然是排序的,ai的插入位置由关键字
     * 比较大小确定
     * 2.重复执行步骤1,n个元素共需要n-1趟,每趟将一个元素ai插入到它前面的子序列中
     */
    public static void straightInsertionSort(int[] keys) {

        for (int i = 1; i < keys.length; i++) {
            int temp = keys[i];

            int j;

            for (j = i - 1; j >= 0 && temp < keys[j]; j--) {
                keys[j+1] = keys[j];
            }

            keys[j+1] = temp;

            System.out.println("第" + i + "趟 temp = " + temp + "\t" + Arrays.toString(keys));
        }
    }

    /**
     * 泛型的插入排序,只需要让泛型类实现Comparable接口,重写compare(Object o)方法即可
     */
    public static<T extends Comparable<? super T>> void straightInsertSort(T[] values) {

        for (int i = 1; i < values.length; i++) {
            T temp = values[i];

            int j;

            for (j = i - 1; j >= 0 && temp.compareTo(values[j]) < 0 ; j--) {
                values[j+1] = values[j];
            }

            values[j+1] = temp;
        }
    }

    /*
     * 排序算法的稳定性
     *   排序算法的稳定性指关键字重复情况下的排序性能,设两个元素ai和aj(i<j),ai位于aj之前,
     *   它们的关键字相等ki = kj,排序后如果ai仍然在aj之前,则称该排序算法  "稳定" !
     *
     * 二分查找排序代替直接插入排序中的顺序查找,则构成 "二分查找排序"
     */

    /**
     * 希尔排序(Shell Sort)  时间复杂度O(n^(1.3~2))  空间复杂度  O(1)
     * 希尔排序又称为缩小增量排序,基本思想是分组的直接插入排序
     * 由直接插入排序算法分析可知,若数据序列越接近有序,则时间效率越高,再者,当n很小的时候,时间效率也比较高,
     * 希尔排序正是基于这两点对直接插入排序算法进行改造
     *
     * 算法描述:
     *   1.将一个数据序列分成若干组,每组由若干相隔一段距离(称为增量)的元素构成,在一个组内采用直接插入排序算法进行排序
     *   2.增量初值通常为数据序列长度的一半,以后每趟增量减半,最后值为1,随着增量逐渐减小,组数也减少,
     *     组内元素个数增加,数据序列越接近有序
     *
     * 解释三重循环:
     *   1.最外层循环for语句以增量 delta 变化控制进行着若干趟的扫描, delta 初始值为序列长度 n/2,以后每趟减半,直至为1
     *   2.中间循环for语句进行一趟扫描,序列分为delta组,每组由相距 delta 远的 n/delta 个元素组成,每组元素分别进行直接插入排序
     *   3.最内层循环for语句进行一组直接插入排序,将一个元素keys[i]插入到其所在组前面的排序子序列中去
     */
    public static void shellSort(int[] keys) {

        for (int delta = keys.length / 2; delta > 0; delta /= 2) {  //若干趟,控制增量没趟减半
            for (int i = delta; i < keys.length; i++) {  //一趟分为若干组,每组直接插入排序
                int temp = keys[i];  //keys[i]是当前待插入的元素

                int j;

                for (j = i - delta; j >= 0 && temp < keys[j]; j -= delta) {  //组内进行插入排序,寻找插入位置
                    keys[j + delta] = keys[j];  //每组元素相距delta远
                }

                keys[j + delta] = temp;  //插入元素
            }

            System.out.println("delta = " + delta + " " + Arrays.toString(keys));
        }
    }








}
