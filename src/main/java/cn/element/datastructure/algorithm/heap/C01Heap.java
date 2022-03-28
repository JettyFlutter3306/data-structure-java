package cn.element.datastructure.algorithm.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import static cn.element.datastructure.sort.IArrays.swap;

public class C01Heap {

    /**
     * 堆排序:
     * 时间复杂度: O(N * log N)
     * 空间复杂度: O(1)
     * 首先把整个数组变成大根堆
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {  // O(N)
            heapInsert(arr, i);  // O(log N)
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);

        while (heapSize > 0) {
            heapDown(arr, 0, heapSize);  // O(log N)
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 新加进来的数,现在停在了index位置,依次往上移动
     * 直到0位置或者比父节点小
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {  // 直到比父节点小或者相等
            swap(arr, index, (index - 1) / 2);  // 交换父子节点位置
            index = (index - 1) / 2;  // 改变索引位置
        }
    }

    /**
     * 堆下沉
     * 我的较大孩子都不再比父节点大,已经没有子节点
     */
    private static void heapDown(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {  // 说明有左孩子
            int larger = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            larger = arr[larger] > arr[index] ? larger : index;

            if (larger == index) {
                break;
            }

            swap(arr, larger, index);
            index = larger;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        // 优先队列默认是小根堆,可以传入lambda表达式变成大根堆
        Queue<Integer> heap = new PriorityQueue<>();
        heap.add(3);
        heap.add(5);
        heap.add(10);
        heap.add(8);

        System.out.println(heap.peek());
        System.out.println("=========================");

        int[] values = {2, 6, 9, 8, 1, 3, 8, 10, 15, 98};
        heapSort(values);
        System.out.println(Arrays.toString(values));
    }

}
