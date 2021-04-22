package test_list;

import cn.element.algorithm.Josephus;
import cn.element.list.SeqList;
import org.junit.Test;

public class TestSeqList {

    private String[] arr = {"X","I","A","Q","W","J","X","Z","M","A"};


    @Test
    public void test01(){

        SeqList<String> seqList = new SeqList<>(arr);

        System.out.println(seqList);

        System.out.println(seqList.toPreviousString());
    }

    @Test
    public void test02(){

//        System.out.println((char)('A' + 1));

        new Josephus(5,0,2);

        Josephus.Josephus1(5,0,2);
    }

    @Test
    public void test03(){

        SeqList<String> seqList = new SeqList<>(arr);

        System.out.println(seqList.search("a"));

        System.out.println(seqList.contains("C"));

        seqList.insert("E");

        System.out.println(seqList);

        seqList.remove("A");

        System.out.println(seqList);
    }

    @Test  //测试深拷贝
    public void test04(){

        SeqList<String> seqList = new SeqList<>(arr);

        System.out.println("seqList = " + seqList);

        SeqList<String> seqList1 = new SeqList<>(seqList);

        System.out.println("seqList1 = " + seqList1);

    }

    @Test
    public void test05(){       //集合并运算

        SeqList<String> seqList = new SeqList<>(arr);

        System.out.println("seqList = " + seqList);

        String[] arr1 = {"as","ads","sdcv","qwx","X","X","Q"};

        SeqList<String> seqList1 = new SeqList<>(arr1);

        seqList.addAll(seqList1);

        System.out.println("seqList1 = " + seqList1);

        System.out.println("seqList = " + seqList);

    }





}
