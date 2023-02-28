package org.codeart.datastructure.algorithm;

/**
 * 使用递归实现字符串的逆转操作
 */
public class ReverseString {

    public static String reverseStr(String str) {
        char[] chars = str.toCharArray();

        recurse(chars, 0, chars.length - 1);

        return new String(chars);
    }

    private static void recurse(char[] chars, int i, int j) {
        if (i > j) {
            return;
        }

        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;

        recurse(chars, i + 1, j - 1);
    }

    public static void main(String[] args) {
        String temp = "abcdefg";
        String s = reverseStr(temp);
        System.out.println(s);
    }
}
