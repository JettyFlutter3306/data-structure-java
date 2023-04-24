package org.codeart.datastructure.algorithm.heap;

import java.util.*;

/**
 * 采用最小/大堆构造优先队列，选择最小/大权值元素优先出队。
 */
public class PriorityQueue<T extends Comparable<T>> { 

    /**
     * 堆元素
     */
    private final List<T> heap;

    /**
     * 倒排索引表
     */
    private final Map<T, Integer> indexMap;

    /**
     * 堆元素个数
     */
    private int heapSize;
    
    private final Comparator<? super T> comparator;

    public PriorityQueue(T[] values, Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;

        for (T value : values) {
            push(value);
        }
    }
    
    public boolean isEmpty() {
        return heapSize == 0;
    }
    
    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    /**
     * 返回堆顶元素,也就是最值
     */
    public T peek() {
        return heap.get(0);
    }

    /**
     * 加入堆
     */
    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    /**
     * 弹出并返回堆顶元素
     */
    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapDown(0);
        return ans;
    }

    /**
     * 移除堆元素
     */
    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);

        if (obj != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    /**
     * 加入元素后,整个调整堆
     * 先上升后下沉
     */
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapDown(indexMap.get(obj));
    }

    /**
     * 返回堆所有元素,这里返回了一份拷贝
     * 为的是后续操作不影响原有的堆
     */
    public List<T> elements() {
        return new ArrayList<>(heap);
    }

    /**
     * 堆插入,默认是小根堆
     */
    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 堆下沉
     */
    private void heapDown(int index) {
        // 左孩子索引
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 在左右孩子中找出最小的那一个的索引
            int better = left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? left + 1 : left;
            
            // 和 index 比较大小,找出较小的那一个
            better = comparator.compare(heap.get(better), heap.get(index)) < 0 ? better : index;
                
            // 说明 index 对应的元素比左右孩子对应的元素都要大或者相等
            if (better == index) {
                break;
            }
            
            swap(better, index);
            index = better;
            left = index * 2 + 1;
        }
    }

    /**
     * 交换两个位置元素
     * 同时操作map里面的元素索引
     */
    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }

    public static void main(String[] args) {
        Integer[] values = {2, 3, 4, 1, 0, 15, 10, 36, 28};
        // 构造小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(values, (o1, o2) -> o1.compareTo(o2));
        // 返回最小值
        System.out.println(minHeap.peek());
        
        // 构造大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(values, (o1, o2) -> o2.compareTo(o1));
        // 返回最大值
        System.out.println(maxHeap.peek());
    }
}
