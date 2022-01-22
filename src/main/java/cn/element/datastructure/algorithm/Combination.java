package cn.element.datastructure.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 求一个整数集合的组合
 * 例如求 ∁_n^m
 */
public class Combination {

    public List<List<Integer>> getCombination(int[] arr, int k) {
        List<List<Integer>> lists = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();

        backTrack(lists, stack, k, 0, arr);

        return lists;
    }

    /**
     * 记 arr.length 为 n
     * 论为什么 i <= n - (k - stack.size())
     * 举例: n = 7,k = 4
     * 当遍历到 4 的时候就可以停止了,没有继续下去的必要了,因为一个组合元素个数才是4个,就算遍历到5底下也不满足条件了,
     * 把 5 入栈底下得到的组合里面的元素只能有3个即 {5,6,7},不满足条件!
     */
    public void backTrack(List<List<Integer>> lists, Stack<Integer> stack, int k, int index, int[] arr) {
        if (stack.size() == k) {
            lists.add(new ArrayList<>(stack));
        } else {
            // i <= n - (k - stack.size()) 重要!!!
            for (int i = index; i <= arr.length - (k - stack.size()); i++) {
                if (!stack.empty() && stack.peek() >= arr[i]) {
                    continue;
                }

                stack.push(arr[i]);
                backTrack(lists, stack, k, index + 1, arr);
                stack.pop();
            }
        }

    }

    public static void main(String[] args) {
        Combination combination = new Combination();

        int[] arr = {1, 3, 5, 7, 8, 9};  //假定给的数组是升序的,方便计算,不是排序的数组那么就要对它进行一波快速排序

        List<List<Integer>> lists = combination.getCombination(arr, 2);

        lists.forEach(System.out::println);

        /* 结果:
         * [1, 3]
         * [1, 5]
         * [1, 7]
         * [1, 8]
         * [1, 9]
         * [3, 5]
         * [3, 7]
         * [3, 8]
         * [3, 9]
         * [5, 7]
         * [5, 8]
         * [5, 9]
         * [7, 8]
         * [7, 9]
         * [8, 9]
         */

    }

}
