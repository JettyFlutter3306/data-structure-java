package test_string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestAlgorithm {

    @Test
    public void test01(){

        List<Integer> list1 = new ArrayList<>();

        for(int i = 1;i <= 1500;i++){
            if(i % 3 == 2 && i % 5 == 4 && i % 7 == 6){
                list1.add(i);
            }
        }

        System.out.println("list1 = " + list1);
    }
}
