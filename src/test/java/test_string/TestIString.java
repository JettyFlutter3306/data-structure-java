package test_string;

import org.junit.Test;
import org.codeart.datastructure.string.IString;
import org.codeart.datastructure.string.IStringBuffer;

import java.util.Arrays;

public class TestIString {

    /**
     * 测试IString(IStringBuffer msb)方法
     */
    @Test
    public void testConstructor() {
        IStringBuffer msba = new IStringBuffer("abcdf");
        System.out.println("msba = " + msba);
        IString msa = new IString(msba);
        System.out.println("msa = " + msa);
    }

    /**
     * 测试大小写转换
     */
    @Test
    public void testUpperCase() {
        IString str = new IString("ACSDFabefwed234DFGDG");
        IString str1 = str.toUpperCase();
        
        System.out.println(str == str1);
        System.out.println("str1 = " + str1);
        System.out.println("str = " + str);
        
        IString str2 = str1.toLowerCase();
        System.out.println("str2 = " + str2);
    }

    @Test
    public void testCharArray() {
        char[] value = {'a', 's', 'v', 'w', 'Q', 'o'};
        for (char c : value) {
            c -= 32; //自动类型转换
        }

        // 不起作用的原因是,增强for循环仅仅是把数组里的元素拷贝一份,但是并没有改变数组元素的值
        System.out.println(Arrays.toString(value)); //[a, s, v, w, Q, o]

        int[] arr = {1, 23, 43, 53, 456, 21};
        for (int i : arr) {
            i += 2;
        }

        System.out.println(Arrays.toString(arr)); //[1, 23, 43, 53, 456, 21]
    }

    /**
     * 测试忽略大小写比较相等
     */
    @Test
    public void testEqualsIgnoreCase() {
        IString str = new IString("abcdEFqq");
        IString str1 = new IString("ABCDefqq");
        boolean b = str.equalsIgnoreCase(str1);
        
        System.out.println("b = " + b);
        System.out.println(str.compareToIgnoreCase(str1));
    }

    /**
     * 测试字符串倒序输出
     */
    @Test
    public void testReverseStringPrint() {
        IString s = new IString("sdsd dfd effg ergs qwe sf  ");
        IString string = IString.upperFirst(s);

        System.out.println("string = " + string);
        IString.printPrevious(s);
    }

    /**
     * 测试判断标识符
     */
    @Test
    public void testIdentifier() {
        IString s = new IString("$sfs");
        System.out.println(IString.isIdentifier(s)); // true
    }

    /**
     * 测试两串求交集
     */
    @Test
    public void testIntersection() {
        IString s1 = new IString("abcdefretdgdg");
        IString s2 = new IString("sfgsdgabcdferg");

        System.out.println(IString.getSameChars(s1, s2));
    }

    /**
     * 测试串逆转
     */
    @Test
    public void testReverseString() {
        IString s = new IString("abcdefg");
        System.out.println(IString.reverse(s));
    }

    /**
     * 测试BF算法
     */
    @Test
    public void testBF() {
        IString s = new IString("abcsdwfabcdf");
        IString pattern = new IString("abcd");
        int i = s.indexOf(pattern);
        System.out.println("i = " + i);
    }

    /**
     * 测试根据索引删除字符
     */
    @Test
    public void testDeleteIndex() {
        IString s = new IString("asfasffasv");
        System.out.println("s = " + s);
        IString s1 = s.deleteString(3);
        System.out.println("s1 = " + s1);
    }

    /**
     * 测试删除子串
     */
    @Test
    public void testDeleteSubString() {
        IString s = new IString("asdfascascv");
        IString str = s.deleteString(2, 5);
        System.out.println(str);
    }

    /**
     * 测试KMP算法
     */
    @Test
    public void testKMP() {
        String target = "abcosvjsdjvabcjsaklaslabcd";
        String pattern = "abcd";
        int i = IString.indexOf(target, pattern);
        System.out.println("i = " + i);
    }
}
