package testString;

import org.junit.Test;
import cn.element.string.MyString;
import cn.element.string.MyStringBuffer;

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

    /**
     * 测试字符串倒序输出
     */
    @Test
    public void test05(){

        MyString s = new MyString("sdsd dfd effg ergs qwe sf  ");

        MyString string = MyString.upperFirst(s);

        System.out.println("cn.geeklbd.string = " + string);

        MyString.printPrevious(s);
    }

    /**
     * 测试判断标识符
     */
    @Test
    public void test06(){

        MyString s = new MyString("$sfs");

        System.out.println(MyString.isIdentifier(s)); //true
    }

    /**
     * 测试两串求交集
     */
    @Test
    public void test07(){

        MyString s1 = new MyString("abcdefretdgdg");
        MyString s2 = new MyString("sfgsdgabcdferg");

        System.out.println(MyString.getSameChars(s1,s2));
    }

    /**
     * 测试串逆转
     */
    @Test
    public void test08(){

        MyString s = new MyString("abcdefg");

        System.out.println(MyString.reverse(s));
    }

    /**
     * 测试BF算法
     */
    @Test
    public void test09(){

        MyString s = new MyString("abcsdwfabcdf");

        MyString pattern = new MyString("abcd");

        int i = s.indexOf(pattern);
        System.out.println("i = " + i);
    }

    /**
     * 测试根据索引删除字符
     */
    @Test
    public void test10(){

        MyString s = new MyString("asfasffasv");

        System.out.println("s = " + s);

        MyString s1 = s.deleteString(3);

        System.out.println("s1 = " + s1);
    }

    /**
     * 测试删除子串
     */
    @Test
    public void test11(){

        MyString s = new MyString("asdfascascv");

        s.deleteString(2, 5);

        System.out.println(s);
    }

    /**
     * 测试KMP算法
     */
    @Test
    public void test12(){

        String target = "abcosvjsdjvabcjsaklaslabcd";

        String pattern = "abcd";

        int i = MyString.indexOf(target, pattern);

        System.out.println("i = " + i);
    }








}
