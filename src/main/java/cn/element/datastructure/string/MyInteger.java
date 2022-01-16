package cn.element.datastructure.string;

/**
 * 整数类,最终类
 */
public final class MyInteger implements Comparable<MyInteger> {

    public static final int MIN_VALUE = 0x80000000; //最小值常量, -2^31 = -2147483648

    public static final int MAX_VALUE = 0x7fffffff; //最大值常量, 2^31 - 1 = 2147483647

    private final int value; //私有最终变量,存储整数,赋值一次

    /**
     * 由int整数value构造整数对象
     */
    public MyInteger(int value) {
        this.value = value;
    }

    /**
     * 由十进制整数字符串s构造整数对象,构造方法只支持十进制, s包含正负号
     */
    public MyInteger(String s) throws NumberFormatException {
        this.value = parseInt(s);
    }

    /**
     * 返回整数值
     */
    public int intValue() {
        return this.value;
    }

    /**
     * 将串s按十进制转换为整数
     */
    public static int parseInt(String s) throws NumberFormatException {
        return MyInteger.parseInt(s,10);
    }

    /**
     * 将串s按radix进制转换为十进制整数,s指定整数的radix进制原码字符串,包含正负号
     * 2<=radix<=16,默认十进制,若不能将s转换成整数,则抛出数值格式异常
     */
    public static int parseInt(String s,int radix) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < 2 || radix > 16) {
            throw new NumberFormatException("radix="+radix+",进制超出2~16范围.");
        }

        int value = 0, i = 0;

        int sign = s.charAt(0) == '-' ? -1 : 1; //符号位,记住正负数标记

        if (s.charAt(0) == '+' || s.charAt(0) == '-') { //跳过符号位
            if (s.length() == 1) {  //只有"+","-"
                throw new NumberFormatException("\""+s+"\"");
            } else {
                i++;  //i记住当前符号
            }
        }

        while (i < s.length()) {
            char ch = s.charAt(i++);

            if (ch >= '0' && ch - '0' < radix) { //当2 <= radix <= 10时,radix进制要识别0~radix-1的数字
                value = value * radix + ch - '0'; //value记住当前获得的整数值
            } else { //当11<= radix <= 16时,radix进制还要识别从'a'/'A'开始的radix-10个字母表示的整数
                if (radix > 10 && ch >= 'a' && ch - 'a' < radix - 10) {
                    value = value * radix + ch - 'a' + 10;
                } else {
                    if (radix > 10 && ch >= 'A' && ch - 'A' < radix - 10) {
                        value = value * radix + ch - 'A' + 10;
                    } else {
                        throw new NumberFormatException(radix+"进制不能识别"+ch);
                    }
                }
            }
        }

        return value * sign;  //返回有符号的整数值
    }

    /**
     * 返回整数value的十六进制补码字符串,正数高位补0
     * @param value     传进来的值
     * @return          十六进制补码
     */
    public static String toHexString(int value) {
        char[] buffer = new char[8];        //一个int有8个十六进制位

        for (int i = buffer.length-1; i >= 0; i--) { //循环执行8次,高位补0
            int bit = value & 15;       //获得十六进制的个位

            buffer[i] = (char) (bit <= 9 ? bit + '0' : bit - 10 + 'a'); //获得十六进制的个位

            value >>>= 4;       //右移4位,高位填充0,即value除以16
        }

        return new String(buffer);  //返回由字符数组构造的字符串
    }

    /**
     * 返回value的二进制补码字符串,正数高位补0
     * @param value         十进制数
     * @return              二进制补码字符串
     */
    public static String toBinaryString(int value) {
        char[] buffer = new char[32];

        for(int i = buffer.length-1; i >= 0; i--) {
            int bit = value & 1;

            buffer[i] = (char) (bit + '0');

            value >>>= 1;
        }

        return new String(buffer);
    }

    /**
     * 返回value的八进制补码字符串,正数高位补0
     * @param value         十进制数
     * @return              八进制补码字符串
     */
    public static String toOctalString(int value) {
        char[] buffer = new char[11];

        for(int i = buffer.length-1; i >= 0; i--) {
            int bit = value & 7;

            buffer[i] = (char)(bit + '0');

            value >>>= 3;
        }

        return new String(buffer);
    }

    @Override
    public int compareTo(MyInteger o) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MyInteger && this.value == ((MyInteger) obj).intValue();
    }

    //返回当前整数的十进制字符串,覆盖
    @Override
    public String toString() {
        return this.value + ""; // "+"自动将整数转换为十进制整数字符串
    }


}
