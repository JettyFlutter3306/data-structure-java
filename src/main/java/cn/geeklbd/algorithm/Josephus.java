package cn.geeklbd.algorithm;

import cn.geeklbd.list.SeqList;
import cn.geeklbd.list.SinglyList;

public class Josephus {

    /**
     * 求解约瑟夫环问题     顺序表法
     *
     * 创建约瑟夫环并求解,参数指定环长度,起始位置,计数
     *
     * @param number        人数
     * @param start         起始位置
     * @param distance      间隔
     */
    public Josephus(int number,int start,int distance){

        System.out.println("Josephus(" + number + "," + start + "," + distance + "),");

        //创建顺序表实例,元素类型是字符串,构造方法参数指定顺序表容量
        SeqList<String> seqList = new SeqList<>(number);

        for(int i = 0;i < number;i++){
            seqList.insert((char)('A'+i) + "");         //顺序表尾插入
        }

        System.out.println(seqList);

        int index = start;                                  //计数起始位置

        while (seqList.size() > 1){                         //多于一个元素的循环时 O(1)

            index = (index + distance - 1) % seqList.size();    //按循环方式对顺序表进行遍历  被处决的人的索引位置

            System.out.print("删除" + seqList.remove(index) + ",");

            System.out.println(seqList);
        }

        System.out.println("被赦免者是" + seqList.get(0));
    }

    /**
     * 求解约瑟夫环问题     单链表法
     *
     * 创建约瑟夫环并求解,参数指定环长度,起始位置,计数
     *
     * @param number        人数
     * @param start         起始位置
     * @param distance      间隔
     */
    public static void Josephus1(int number,int start,int distance){

        System.out.println("Josephus(" + number + "," + start + "," + distance + "),");

        String[] arr = new String[number];

        for(int i=0;i<number;i++){
            arr[i] = (char)('A'+i) + "";
        }

        //创建单链表实例
        SinglyList<String> singlyList = new SinglyList<>(arr);

        System.out.println(singlyList);

        int index = start; //起始位置

        while (singlyList.n > 1){

            index = (index + distance - 1) % singlyList.n;

            System.out.print("删除" + singlyList.remove(index) + ",");

            System.out.println(singlyList);
        }

        System.out.println("被赦免者是:"+singlyList.get(0));
    }

}
