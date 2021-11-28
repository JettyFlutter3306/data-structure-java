package cn.element.sort;

import java.util.Arrays;

/**
 * 选择排序算法有两种: "直接选择排序" 和 "堆排序"
 */
public class SelectSort {

    /**
     * 直接选择排序(Straight Select Sort) 算法思想:
     * 第一趟从 n 个元素的数据序列中选出关键字最小/大的元素并放到最前/后位置,
     * 下一趟再从 n-1 个元素中选出最小/大的元素放到次前/后的位置,以此类推,经过 n-1 趟完成排序
     *
     * 算法分析:
     *   直接选择排序的比较次数与数据序列的初始排列无关,第 i 趟排序的比较次数是 n - i;移动次数与初始排列有关,排序序列移动 0 次
     *   反序排列的数据序列,每趟排序都要交换,移动 3(n-1) 次,
     *   算法总比较次数 C = ∑(n - i) (from i = 1 to i = n- 1) = n(n-1)/2 ~ n^2 / 2
     *   时间复杂度O(n²)  空间复杂度O(1)  直接选择排序算法不稳定
     */
    public static void straightSelectSort(int[] keys){
        for (int i = 0; i < keys.length - 1; i++) {  //n-1趟排序
            int min = i;

            for (int j = i + 1; j < keys.length; j++) {  //每趟再从keys[i]开始的子序列中寻找最小值
                min = keys[j] < keys[min] ? j : min;  //min记住本趟最小元素下标
            }

            if(min != i){  //将本趟最小元素交换到前边
                IArrays.swap(keys,i,min);
            }

            System.out.println("第" + (i + 1) + "趟: " + Arrays.toString(keys));
        }
    }

    public static void heapSort(int[] keys){
        heapSort(keys,true);
    }

    /**
     * 堆排序,当 minHeap 为true时,创建最小堆,降序排列,否则创建最大堆,升序排列
     * 算法分析:
     *   时间复杂度 O(n × log n)  空间复杂度 O(1)
     *   堆排序算法不稳定
     */
    public static void heapSort(int[] keys,boolean minHeap){
        for (int i = keys.length / 2; i >= 0 ; i--) {  //创建最小/大堆,根结点值最小/大
            sift(keys,i,keys.length - 1,minHeap);
        }

        for (int i = keys.length - 1; i > 0 ; i--) {  //每趟将最小/大值交换到最后面,再调整成最小/大堆
            IArrays.swap(keys,0,i);  //换值

            sift(keys,0,i-1,minHeap);
        }
    }

    /**
     * 将 keys 数组中以parent为根的子树调整为最小/大堆,子序列范围为 parent ~ end
     * 私有方法,只被堆排序方法调用,确保 parent, end 在范围内
     */
    private static void sift(int[] keys,int parent,int end,boolean miniHeap){
        System.out.println("sift " + parent + ".." + end + " ");

        int child = 2 * parent + 1;  //child是parent的左孩子

        int value = keys[parent];

        while(child <= end){  //沿着较小/大值孩子结点向下筛选
            if(child < end && (miniHeap ? keys[child] > keys[child + 1] : keys[child] < keys[child + 1])){
                child++;  //child记住孩子值较小/大者
            }

            if(miniHeap ? value > keys[child] : value < keys[child]){  //若父母结点值较小/大
                keys[parent] = keys[child];  //将较小/大孩子结点值上移

                parent = child;  //parent child 两者都向下一层

                child = 2 * parent + 1;
            }else{
                break;
            }
        }

        keys[parent] = value;  //当前子树的原根值调整后的位置

        System.out.println(Arrays.toString(keys));
    }

    /**
     * 判断value指定数据序列是否为堆
     */
    public static boolean isHeap(int[] value,boolean miniHeap){


        return false;
    }
}
