package testList;

import list.SeqList;
import list.SortedSeqList;
import org.junit.Before;
import org.junit.Test;

public class TestSortedSeqList {

    private static String[] arr = {"x","as","acv","e","zc","po"};

    private static SeqList<String> sortedSeqList;  //多态模式

    static {

        sortedSeqList = new SortedSeqList<>(arr);
    }

    @Before
    public void testBefore(){

        System.out.println("sortedSeqList = " + sortedSeqList);
    }

    @Test
    public void test01(){

        sortedSeqList.insert("q");

        System.out.println("sortedSeqList = " + sortedSeqList);

        int q = sortedSeqList.insertDifferent("q");

        System.out.println("q = " + q);
    }

    @Test
    public void test02(){

        System.out.println(sortedSeqList.search("a"));

        String e = sortedSeqList.remove("e");

        System.out.println("e = " + e);

        System.out.println(sortedSeqList);
    }




}
