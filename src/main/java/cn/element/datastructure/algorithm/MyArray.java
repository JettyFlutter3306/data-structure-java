package cn.element.datastructure.algorithm;

import java.util.*;

public class MyArray {

    /**
     * 返回n个互异的随机数,范围是0 ~ size-1
     * @param n         个数
     * @param size      范围
     * @return          数组
     */
    public static Integer[] randomDifferent(int n,int size) {
        Set<Integer> set = new HashSet<>();

        Random random = new Random();

        while (set.size() < n) {
            int temp = random.nextInt(size);

            set.add(temp);
        }

        Integer[] arr = new Integer[set.size()];

        int index = 0;

        for (Integer integer : set) {
            arr[index++] = integer;
        }

        return arr;
    }

    /**
     * 返回n个排序的随机数
     * @param n         个数
     * @param size      范围
     * @return          范围
     */
    public static Integer[] randomSorted(int n,int size) {
        List<Integer> list = new ArrayList<>();

        Random random = new Random();

        while (list.size() < n) {
            int temp = random.nextInt(size);

            list.add(temp);
        }

        Integer[] arr = new Integer[list.size()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    /**
     * 返回n个互异的排序的随机数
     * @param n         个数
     * @param size      范围
     * @return          数组
     */
    public static Integer[] randomDifferentSorted(int n,int size) {
        Integer[] integers = randomDifferent(n, size);

        Arrays.sort(integers);

        return integers;
    }




}
