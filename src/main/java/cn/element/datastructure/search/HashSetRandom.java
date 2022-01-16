package cn.element.datastructure.search;

import cn.element.datastructure.hash.HashSet;

import java.util.Arrays;

public class HashSetRandom {

    /**
     * 产生 n 个互异的随机数,范围是 0 ~ size-1,返回整数对象数组
     * @param n             数量
     * @param size          范围
     * @return              Integer[]
     */
    public static Integer[] randomDifferent(int n, int size) {
        Integer[] values = new Integer[n];

        HashSet<Integer> set = new HashSet<>();  //构造空散列表

        int i = 0;

        while (i < n) {
            int key = (int)(Math.random() * size);  //生成 0 ~ size-1 的随机数

            if (set.add(key)) {  //添加一个随机数到散列表成功
                values[i++] = key;
            } else {
                System.out.println(key + "重复,插入失败!");
            }
        }

        return values;
    }

    public static void main(String[] args) {
        int n = 10;
        int size = 100;

        Integer[] values = randomDifferent(n,size);

        System.out.println(n + "个元素 0~size 之间的互异随机数集合: " + Arrays.toString(values));

    }
}
