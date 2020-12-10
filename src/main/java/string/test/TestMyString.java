package string.test;

import org.junit.Test;
import string.MyString;
import string.MyStringBuffer;

import java.util.Arrays;

public class TestMyString {

    /**
     * 测试MyString(MyStringBuffer msb)方法
     */
    @Test
    public void test01(){

        MyStringBuffer msba = new MyStringBuffer("abcdf");

        System.out.println("msba = " + msba);

        MyString msa = new MyString(msba);

        System.out.println("msa = " + msa);
    }

    /**
     * 测试大小写转换
     */
    @Test
    public void test02(){

        MyString str = new MyString("ACSDFabefwed234DFGDG");

        MyString str1 = str.toUpperCase();

        System.out.println(str == str1);

        System.out.println("str1 = " + str1);

        System.out.println("str = " + str);

        MyString str2 = str1.toLowerCase();

        System.out.println("str2 = " + str2);

    }

    @Test
    public void test03(){

        char[] value = {'a','s','v','w','Q','o'};

        for (char c : value) {
            c -= 32; //自动类型转换
        }

        //不起作用的原因是,增强for循环仅仅是把数组里的元素拷贝一份,但是并没有改变数组元素的值
        System.out.println(Arrays.toString(value)); //[a, s, v, w, Q, o]

        int[] arr = {1,23,43,53,456,21};

        for (int i : arr) {
            i += 2;
        }

        System.out.println(Arrays.toString(arr)); //[1, 23, 43, 53, 456, 21]
    }

    /**
     * 测试忽略大小写比较相等
     */
    @Test
    public void test04(){

        MyString str = new MyString("abcdEFqq");

        MyString str1 = new MyString("ABCDefed");

        boolean b = str.equalsIgnoreCase(str1);

        System.out.println("b = " + b);

        System.out.println(str.compareToIgnoreCase(str1));
    }


}
