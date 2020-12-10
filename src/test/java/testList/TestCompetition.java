package testList;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class TestCompetition {

    private static LinkedList<Integer> list = new LinkedList<>();

    /**
     * 从键盘输入一个100000~999999的正整数，该数的约数中最大的三位数是多少？
     * 例如：
     * 输入：555555
     * 输出：The max factor with 3 digits in 555555 is:777
     */
    public static void getBiggestThreeNumber(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入一个100000~999999的正整数");

        int num = scanner.nextInt();

        int biggest = 0;

        for (int i = 999; i > 99; i--) {
            if(num % i == 0){
                biggest = i;

                break;
            }
        }

        System.out.println("The max factor with 3 digits in "+ num +" is :" + biggest);
    }

    /**
     * 从键盘输入一个正实数（十进制），将该数转换成二进制、八进制和十六进制输出。
     * 例如：
     * 输入：25.8125
     * 输出：二进制是11001.1101
     *      八进制是31.64
     *      十六进制是19.D
     */
    public static void changeRadix(int base){

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入一个数");

        String num = scanner.next();

        Stack<String> stack = new Stack<>();   //定义一个栈 存放整数部分

        Queue<String> queue = new LinkedList<>(); //定义一个队列 存放小数部分

        while (!num.contains(".")){
            System.out.println("请输入标准格式!例如 25.9818");

            num = scanner.next();
        }

        //字符串分割
        String[] arr = num.split("\\.");

        //获得整数部分
        int a = Integer.valueOf(arr[0]);

        //获得小数部分
        double b = Double.valueOf(arr[1]) / Math.pow(10,arr[1].length());

        //处理整数部分
        while (a != 0){

            int answer = a / base;  //获得两数之商

            int remainder = a % base; //获得余数

            stack.push(radixFormat(remainder + "",base)); //余数入栈

            a = answer; //将原来的数变成商 一直循环
        }

        /* 处理小数部分
         * 可能会出现循环小数 保留6位小数
         */
        int count = 0;  //定义一个计数器

        while (b != 0.0){

            if(count == 6){
                break;
            }

            double product = b * base; //获得两数之积

            String[] split = (product + "").split("\\."); //字符串正则分割

            queue.offer(radixFormat(split[0],base)); //整数部分入队

            b = Double.valueOf(split[1]) / Math.pow(10,split[1].length()); //缩小固定倍数

            count++;
        }


        StringBuilder sb = new StringBuilder("");

        while (!stack.isEmpty()){
            sb.append(stack.pop()); //出栈字符串拼接
        }

        sb.append(".");

        while (!queue.isEmpty()){
            sb.append(queue.poll()); //出队
        }

        //打印
        switch (base){
            case 2 :
                System.out.println("二进制是: "+ sb);break;
            case 8 :
                System.out.println("八进制是: "+ sb);break;
            case 16 :
                System.out.println("十六进制是: " + sb);break;
        }
    }

    /**
     *
     * @param num   要转换的字符串数
     * @param base  数制
     * @return      主要是针对十六进制
     */
    public static String radixFormat(String num,int base){

        if(base > 10){
            switch (num){
                case "10":
                    return "A";
                case "11":
                    return "B";
                case "12":
                    return "C";
                case "13":
                    return "D";
                case "14":
                    return "E";
                case "15":
                    return "F";
            }
        }

        return num;
    }

    /**
     * 输入两个整数n和m，从数列1、2、3、…、n中随意取几个数，使其和等于m。
     * 要求将其中所有可能的组合列出来，编程求解。最后将结果按文本文件方式写在当前目录的myout.txt中。
     *
     * 求解思路：
     *   1.首先判断，如果n>m,则n中大于m的数不可能参与组合，此时置n=m；
     *   2.将最大的数n加入且n==m，则满足条件，输出；
     *   3.将n分两种情况求解：n没有加入，取n=n-1,m=m,递归；
     *   4.n加入，取n=n-1,m=m-n,递归。
     *   5.结束。
     */
    public static void combinationNum(int m,int n){

        if(m < 1 || n < 1){
            return;
        }

        if(m > n){
            list.add(n);

            combinationNum(m -  n,n - 1);   //n加入,取n = n -1,m = m - n

            list.pop();

            combinationNum(m,n - 1);    //n没有加入.取n = n - 1,m = m
        }else{
            System.out.print(m);

            for (int i = 0; i < list.size(); i++) {
                System.out.print(" "+list.get(i));
            }

            System.out.println();
        }

    }

    /**
     * 输入一个自然数，若为偶数，则把它除以2，若为奇数，则把它乘以3加1。
     * 经过如此有限次运算后，总可以得到自然数值1。
     * 求经过多少次可得到自然数1，以及每次变换的中间值。
     */
    public static void getMiddleNum(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入一个自然数");

        int n = scanner.nextInt();

        int count = 0;

        if(n == 1){
            count = 2;

            System.out.println("count = " + count);
        }

        while (n != 1){
            if(n % 2 == 0){
                n /= 2;

                System.out.print(n + "\t");

                count++;
            }else{
                n = n * 3 + 1;

                System.out.print(n + "\t");

                count++;
            }
        }

        System.out.println();

        System.out.println("count = " + count);
    }

    /**
     * 从键盘输入一个字符串，统计出现的单词个数，并将出现次数最多的单词显示出来。
     */
    public static void getMostWord(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入一句话");

        String str = scanner.nextLine();

        Map<String,Integer> map = new HashMap<>();

        String[] split = str.split("\\s+");

        for (String s : split) {
            if(!map.containsKey(s)){
                map.put(s,1);
            }else{
                map.put(s,map.get(s) + 1);
            }
        }

        int count = 0;

        String temp = "";

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if(value > count){
                temp = key;

                count = value;
            }

            System.out.print(key+",");

            System.out.println(value);
        }

        System.out.println("出现最多的单词是"+temp+",共"+count+"次");
    }

    /**
     * 请编程实现两个大的正整数的乘法。
     */
    public static void bigNumberMultiPly(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入第一个数");

        String x = scanner.next();

        System.out.println("请输入第二个数");

        String y = scanner.next();

        BigDecimal a = new BigDecimal(x);

        BigDecimal b = new BigDecimal(y);

        System.out.println(a.multiply(b));
    }

    /**
     * 求最短路径
     */
    public static void getShortestPath(int[][] arr){

        int sum = 0;    //定义sum用于记录路径长度

        for (int i = 0; i < arr.length; i++) {
            int min = 0; //用于记录每一行的最小值

            for (int j = 0; j < arr[i].length - 1; j++) {
                min = arr[i][j] < arr[i][j+1] ? arr[i][j] : arr[i][j+1];
            }

            sum += min;
        }

        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {

//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("请先输入一个数");
//
//        int n = scanner.nextInt();
//
//        System.out.println("请再次输入一个数");
//
//        int m = scanner.nextInt();
//
//        combinationNum(m,n);

//        getMiddleNum();

//        bigNumberMultiPly();

//        getMostWord();

        int[][] arr = {{2},{3,4},{6,5,7},{4,1,8,3}};

        getShortestPath(arr);

    }
}
