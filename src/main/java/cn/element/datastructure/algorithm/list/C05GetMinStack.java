package cn.element.datastructure.algorithm.list;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 获取一个栈的里面的最小元素要求时间复杂度为O(1)
 */
public class C05GetMinStack {

    /**
     * 定义数据栈和最小值栈,这两个栈同步压入数据
     */
    private static final Deque<Integer> dtStack = new LinkedList<>();
    private static final Deque<Integer> minStack = new LinkedList<>();

    public static void init(int[] values) {
        for (int i : values) {
            dtStack.push(i);
            if (minStack.isEmpty()) {
                minStack.push(i);
            } else if (minStack.peek() >= i) {
                minStack.push(i);
            }
        }
    }

    public static int getMin() {
        assert !minStack.isEmpty();
        return minStack.peek();
    }

    public static void main(String[] args) {
        int[] values = {2, 3, 2, 19, 0, 2, 3, 5, 9, 6, 7, 8, 3, 4, 1, 5};
        init(values);
        System.out.println(dtStack);
        System.out.println(minStack);
        System.out.println(getMin());
    }

}
