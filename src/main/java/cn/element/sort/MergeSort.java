package cn.element.sort;

/**
 * 归并排序是将两个排序的子序列合并,形成一个新的排序数据序列,又称两路归并排序
 */
public class MergeSort {

    /**
     * 归并排序实现
     * 算法分析:
     *   n个元素归并排序,每趟比较n-1次,数据移动n-1次,进行 [log n] 趟,
     *   时间复杂度为 O(n × log n)  空间复杂度为 O(n)
     */
    public static void mergeSort(int[] x){
        int[] y = new int[x.length];  //y数组长度同x数组

        int n = 1;  //排序子序列长度,初始值为1

        while(n < x.length){
            mergePass(x,y,n);  //一趟归并,将x中若干相邻子序列归并到y

            n *= 2;  //子序列长度加倍

            if(n < x.length){
                mergePass(y, x, n);  //一趟归并,将y中若干相邻子序列再归并到x

                n *= 2;
            }
        }
    }

    /**
     * 将x中分别以begin1,begin2开始的两个相邻子序列归并(升序)到y中,子序列长度为n
     */
    private static void merge(int[] x,int[] y,int begin1,int begin2,int n){
        int i = begin1, j = begin2, k = begin1;

        while(i < begin1 + n && j < begin2 + n && j < x.length){  //将x中的两个相邻子序列归并到y中
            if(x[i] < x[j]){
                y[k++] = x[i++];
            }else{
                y[k++] = x[j++];
            }
        }

        while(i < begin1 + n && i < x.length){  //将前一个子序列剩余元素复制到y,序列长度可能不是n
            y[k++] = x[i++];
        }

        while(j < begin2 + n && j < x.length){  //将后一个子序列剩余元素复制到y中
            y[k++] = x[j++];
        }
    }

    /**
     * 实现一趟归并,将x中若干相邻子序列两两归并到y中,子序列长度为n
     */
    private static void mergePass(int[] x,int[] y,int n){
        System.out.println("子序列长度 n = " + n + " ");

        for (int i = 0; i < x.length; i += 2 * n) {  //将x中若干相邻子序列归并到y中切
            merge(x, y, i, i + n, n);  //一次归并
        }
    }


}
