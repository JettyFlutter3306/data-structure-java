package test_list;

import cn.element.algorithm.Josephus;
import cn.element.list.SeqList;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;

public class TestSeqList {

    private final String[] arr = {"X","I","A","Q","W","J","X","Z","M","A"};

    /**
     * 测试创建SeqList
     */
    @Test
    public void test01(){

        SeqList<String> seqList = new SeqList<>(arr);

        System.out.println(seqList);

        System.out.println(seqList.toPreviousString());
    }

    /**
     * 测试使用SeqList实现约瑟夫环算法
     */
    @Test
    public void test02(){

//        System.out.println((char)('A' + 1));

        Josephus.josephus1(5,0,2);

        Josephus.josephus2(5,0,2);
    }

    /**
     * 测试SeqList的CRUD
     */
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

    /**
     * 测试深拷贝
     */
    @Test
    public void test04() throws IOException, ClassNotFoundException {

        SeqList<String> seqList = new SeqList<>(arr);

        System.out.println("seqList = " + seqList);

        SeqList<String> seqList1 = new SeqList<>(seqList);

        System.out.println("seqList1 = " + seqList1);

        //二者hashcode不相等,成功实现深拷贝
        System.out.println(seqList.hashCode());
        System.out.println(seqList1.hashCode());
    }

    /**
     * 测试集合并运算
     */
    @Test
    public void test05(){

        SeqList<String> seqList = new SeqList<>(arr);

        System.out.println("seqList = " + seqList);

        String[] arr1 = {"as","ads","sdcv","qwx","X","X","Q"};

        SeqList<String> seqList1 = new SeqList<>(arr1);

        seqList.addAll(seqList1);

        System.out.println("seqList1 = " + seqList1);

        System.out.println("seqList = " + seqList);
    }

    /**
     * 测试SeqList实现Iterable接口,并使用forEach遍历
     */
    @Test
    public void test06(){

        SeqList<Integer> list = new SeqList<>();

        for (int i = 0; i < 10; i++) {
            list.insert(i * 2 + 1);
        }

        list.forEach(v -> {  //使用JDK8的函数式编程forEach(Consumer consumer)遍历列表
            if(v != null){
                System.out.print(v + "\t");
            }
        });

        System.out.println();

        Iterator<Integer> iterator = list.iterator();  //获取SeqList的迭代器

        while(iterator.hasNext()){  //使用迭代器遍历列表
            System.out.print(iterator.next() + "\t");
        }

    }


}
