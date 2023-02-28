package org.codeart.datastructure.algorithm;

import org.codeart.datastructure.common.Queue;
import org.codeart.datastructure.list.SeqList;
import org.codeart.datastructure.list.SortedSeqList;
import org.codeart.datastructure.queue.SeqQueue;

/**
 * 求解素数环问题
 */
public class PrimeRing {

    public PrimeRing(int max) { //求1~max的素数环
        SortedSeqList<Integer> primeList = this.createPrime(max);  //排序顺序表存储

        System.out.println("素数集合: " + primeList);

        SeqList<Integer> ring = new SeqList<>(max);     //顺序表,存储素数环

        ring.insert(1);         //素数环添加Integer(1)

        Queue<Integer> queue = new SeqQueue<>(max);  //创建空队列,链式队列也可以

        for (int i = 2; i <= max; i++) {
            queue.add(i);
        }

        System.out.println("队列: " + queue);

        int i = 0;

        while (!queue.isEmpty()) {
            int key = queue.poll();     //出队

            if (primeList.contains(ring.get(i) + key)) {  //判断素数,排序顺序表包含(查找)
                i++;

                ring.insert(key);       //素数环添加Integer(key)
            } else {
                queue.add(key);     //key再次入队
            }
        }

        System.out.println("1 ~ " + max + "素数环: " + ring);
    }

    /**
     * 返回包含2~max中所有素数的排序顺序表,也可以返回循环双链表
     *
     * @param max 最大值
     * @return 排序顺序表
     */
    private SortedSeqList<Integer> createPrime(int max) {
        if (max <= 0) {
            return null;
        }

        SortedSeqList<Integer> primeList = new SortedSeqList<>(max * 2);

        primeList.insert(2);        //添加已知最小素数

        for (int i = 3; i < max * 2; i += 2) {  //测试奇数,其他偶数不需要测试
            int j = 0;

            while (j < primeList.size() && i % primeList.get(j) != 0) {
                j++;
            }

            if (j == primeList.size()) {  //i是素数
                primeList.insert(i);        //排序顺序表尾插入最大值
            }
        }

        return primeList;
    }

    public static void main(String[] args) {
        //tip: max为30时无解
        new PrimeRing(10);
    }
}
