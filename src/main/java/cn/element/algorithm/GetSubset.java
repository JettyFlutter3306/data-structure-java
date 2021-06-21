package cn.element.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 求一个集合(n个元素)的子集
 */
public class GetSubset {

    public List<List<Integer>> getSubSet(int[] nums){

        List<List<Integer>> res = new ArrayList<>();

        List<Integer> path = new ArrayList<>();

        dfs(nums, 0, nums.length, path, res);

        return res;
    }

    /**
     * 构造回溯递归辅助函数
     */
    private void dfs(int[] nums, int begin, int n, List<Integer> path, List<List<Integer>> res) {

        res.add(new ArrayList<>(path));

        for (int i = begin; i < n; i++) {
            path.add(nums[i]);

            dfs(nums, i + 1, n, path, res);

            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {

        GetSubset getSubset = new GetSubset();

        int[] arr = {1,3,5,7};

        List<List<Integer>> lists = getSubset.getSubSet(arr);

        lists.forEach(System.out::println);

        /* 结果:
         * []
         * [1]
         * [1, 3]
         * [1, 3, 5]
         * [1, 3, 5, 7]
         * [1, 3, 7]
         * [1, 5]
         * [1, 5, 7]
         * [1, 7]
         * [3]
         * [3, 5]
         * [3, 5, 7]
         * [3, 7]
         * [5]
         * [5, 7]
         * [7]
         */

    }
}
