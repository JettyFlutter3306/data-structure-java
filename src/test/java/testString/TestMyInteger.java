package testString;

import org.junit.Test;
import cn.geeklbd.string.MyInteger;

public class TestMyInteger {

    /**
     * 测试parseInt()方法
     */
    @Test
    public void test01(){

        System.out.println(MyInteger.parseInt("587943"));
    }

    /**
     * 测试i十进制转十六进制方法
     */
    @Test
    public void test02(){

        //整数十六进制的原码
        String[] str = {"-289","-1","+7f","3e56"};

        for (String s : str) {

            int value = MyInteger.parseInt(s,16);

            System.out.println(value+", 0x"+MyInteger.toHexString(value));
        }

        String[] str1 = {"123","8","762","999","1000"};

        for (String s : str1) {

            int value = MyInteger.parseInt(s);

            System.out.println(value+", "+MyInteger.toBinaryString(value));
        }

        for (String s : str1) {

            int value = MyInteger.parseInt(s);

            System.out.println(value+", 0"+MyInteger.toOctalString(value));
        }
    }

    @Test
    public void test03(){

    }
}
