package cn.element.datastructure.algorithm.heap;

import java.util.*;

/**
 * 手写加强堆
 * T 一定要是非基础类型
 */
public class C03HeapGreater<T> {

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

    public C03HeapGreater(Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }
    
    public boolean isEmpty() {
        return heapSize == 0;
    }
    
    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }
    
    public T peek() {
        return heap.get(0);
    }

    /**
     * 堆加入,元素可能需要往上调整
     */
    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }
    
    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapDown(0);
        return ans;
    }
    
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
    
    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapDown(indexMap.get(obj));
    }

    /**
     * 返回堆的所有元素
     */
    public List<T> getAllElements() {
        return new ArrayList<>(heap);
    }

    /**
     * 堆插入,这边是调整为小根堆
     */
    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);    
            index = (index - 1) / 2;
        }
    }

    /**
     * 同理这边是调成小根堆
     */
    private void heapDown(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int better = left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
            better = comparator.compare(heap.get(better), heap.get(index)) < 0 ? better : index;
            
            if (better == index) {
                break;
            }
            
            swap(better, index);
            index = better;
            left = index * 2 + 1;
        }
    }
    
    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o2, i);
        indexMap.put(o1, j);
    }

    public static void main(String[] args) {
        
    }
    
    
    
    
    
    
}
