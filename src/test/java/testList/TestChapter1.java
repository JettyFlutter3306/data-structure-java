package testList;

import cn.element.algorithm.MyArray;
import org.junit.Test;

import java.util.Arrays;

public class TestChapter1 {

    @Test
    public void test01(){

        Integer[] integers = MyArray.randomDifferent(10, 20);

        System.out.println(Arrays.toString(integers));

        Integer[] integers1 = MyArray.randomSorted(10, 20);

        System.out.println(Arrays.toString(integers1));

        Integer[] integers2 = MyArray.randomSorted(10, 20);

        System.out.println(Arrays.toString(integers2));
    }

    @Test
    public void test02(){

        System.out.println((char)('a'+2));
    }


}
