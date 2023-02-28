package org.codeart.datastructure.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class RadixExchange {

    /**
     * 从键盘输入一个正实数（十进制），将该数转换成二进制、八进制和十六进制输出。
     * 例如：
     * 输入：25.8125
     * 输出：二进制是11001.1101
     * 八进制是31.64
     * 十六进制是19.D
     */
    public static void changeRadix(int base) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入一个数");

        String num = scanner.next();

        Stack<String> stack = new Stack<>();   //定义一个栈 存放整数部分
        Queue<String> queue = new LinkedList<>(); //定义一个队列 存放小数部分

        while (!num.contains(".")) {
            System.out.println("请输入标准格式!例如 25.9818");

            num = scanner.next();
        }

        //字符串分割
        String[] arr = num.split("\\.");

        //获得整数部分
        int a = Integer.parseInt(arr[0]);

        //获得小数部分
        double b = Double.parseDouble(arr[1]) / Math.pow(10, arr[1].length());

        //处理整数部分
        while (a != 0) {
            int answer = a / base;  //获得两数之商

            int remainder = a % base; //获得余数

            stack.push(radixFormat(remainder + "", base)); //余数入栈

            a = answer; //将原来的数变成商 一直循环
        }

        /* 处理小数部分
         * 可能会出现循环小数 保留6位小数
         */
        int count = 0;  //定义一个计数器

        while (b != 0.0) {
            if (count == 6) {
                break;
            }

            double product = b * base; //获得两数之积

            String[] split = (product + "").split("\\."); //字符串正则分割

            queue.offer(radixFormat(split[0], base)); //整数部分入队

            b = Double.parseDouble(split[1]) / Math.pow(10, split[1].length()); //缩小固定倍数

            count++;
        }


        StringBuilder sb = new StringBuilder("");

        while (!stack.isEmpty()) {
            sb.append(stack.pop()); //出栈字符串拼接
        }

        sb.append(".");

        while (!queue.isEmpty()) {
            sb.append(queue.poll()); //出队
        }

        //打印
        switch (base) {
            case 2:
                System.out.println("二进制是: " + sb);
                break;
            case 8:
                System.out.println("八进制是: " + sb);
                break;
            case 16:
                System.out.println("十六进制是: " + sb);
                break;
        }
    }

    /**
     * @param num  要转换的字符串数
     * @param base 数制
     * @return 主要是针对十六进制
     */
    private static String radixFormat(String num, int base) {
        if (base > 10) {
            int temp = Integer.parseInt(num);
            return (char)(temp - 10 + 'A') + "";
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println(radixFormat("11", 16));
    }
}
