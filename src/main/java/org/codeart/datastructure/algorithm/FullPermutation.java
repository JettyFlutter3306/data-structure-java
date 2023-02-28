package org.codeart.datastructure.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 实现全排列
 * <p>
 * 例如: 数据序列{1,2,3}的全排列如下
 * 123 132 213 231 312 321
 * <p>
 * 思路:
 * 要求1 2 3的全排列,
 * 那么先求1 和 2 3的全排列,再求2 3的全排列记为perm(1,perm(2,3))
 * 递归 + 回溯
 */
public class FullPermutation {

    public List<List<String>> getFullPermutation(String[] arr) {
        List<List<String>> lists = new ArrayList<>();

        Stack<String> stack = new Stack<>();

        dfs(arr, stack, lists, 0);

        return lists;
    }

    private void dfs(String[] arr, Stack<String> stack, List<List<String>> lists, int index) {
        if (arr.length <= 0) {
            lists.add(new ArrayList<>(stack));
        } else {
            for (int i = 0; i < arr.length; i++) {
                String[] temp = new String[arr.length - 1];

                //拷贝arr数组中除了第 i 个之外的元素
                System.arraycopy(arr, 0, temp, 0, i);
                System.arraycopy(arr, i + 1, temp, i, arr.length - i - 1);

                stack.push(arr[i]);  //元素入栈
                dfs(temp, stack, lists, i);  //递归
                stack.pop();  //状态重置,回溯
            }
        }
    }

    public static void main(String[] args) {
        String[] arr = {"1", "2", "3"};

        FullPermutation a = new FullPermutation();

        List<List<String>> lists = a.getFullPermutation(arr);

        lists.forEach(System.out::println);

        /*
         * [1, 2, 3]
         * [1, 3, 2]
         * [2, 1, 3]
         * [2, 3, 1]
         * [3, 1, 2]
         * [3, 2, 1]
         */

    }
}
