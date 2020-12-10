package list.test;

import java.util.*;

public class TestCompetition2 {

    public static void main(String[] args) {

//        question1();

//        question2();

//        question4();

//        question5(36);

//        question6(new String[]{"鸡蛋","面包","汉堡","披萨"},new Stack<>());

        question8(1444,3);


    }

    /**
     * 有两个五位整数ABCDE和EDCBA，一个位于2-9之间的一位整数X，满足ABCDE * X = EDCBA。求A，B，C，D，E和X。
     */
    public static void question1(){

        int a,b,c,d,e,x;

        for(a=1;a<=9;a++){
            for(b=0;b<=9;b++){
                for(c=0;c<=9;c++){
                    for(d=0;d<=9;d++){
                        for(e=1;e<=9;e++){
                            for(x=2;x<=9;x++){
                                if((a*10000+b*1000+c*100+d*10+e) * x == (e*10000+d*1000+c*100+b*10+a)){
                                    System.out.print("A = " + a + " ");
                                    System.out.print("B = " + b + " ");
                                    System.out.print("C = " + c + " ");
                                    System.out.print("D = " + e + " ");
                                    System.out.print("E = " + d + " ");
                                    System.out.print("X = " + x + " ");
                                    System.out.println();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 给定N个数字，从中选择R×C个数字，并将其填入R×C矩阵中。令D(i)为矩阵中第i行中最大值和最小值之差（1≤i≤R）
     * ，令F为整个矩阵各行D(i) 的最大值。请设计一种选择方法使得矩阵的F值最小，并输出这个最小值。
     * 输入：
     * 输入非负整数T≤20约定测试的次数；输入非负整数N、R、C，约定输入数字个数，预期矩阵行数、列数；输入N个非负整数P_i。
     * 范围：1≤R,C≤10^4, R×C≤〖N≤5×10〗^5, 0≤P_i≤10^9
     * 输出：
     * 对于每个测试例输出单独的一行“Case X:Y”，X表示测试的序号，Y表示最小值F。
     */
    public static void question2(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入N");
        int n = scanner.nextInt();

        System.out.println("请输入R");
        int r = scanner.nextInt();

        System.out.println("请输入C");
        int c = scanner.nextInt();

        int[][] arr = new int[r][c];

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                arr[i][j] = scanner.nextInt();
            }
        }

        List<Integer> list = new ArrayList<>();

        for(int i=0;i< arr.length;i++){

            int max = arr[i][0];//定义最大值

            int min = arr[i][0];//定义最小值

            for(int j=0;j<arr[i].length-1;j++){

                max = max > arr[i][j+1] ? max : arr[i][j+1];

                min = min < arr[i][j+1] ? min : arr[i][j+1];
            }

            list.add(max - min);
        }

        System.out.println("list = " + list);

        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }



    }

    /**
     * 求14的14次方的最后三位数
     */
    public void question3(){



    }

    /**
     * 有一种简单的加密方法可以描述如下：
     * 对存储在string数组内的英文句子中所有以a开始并以e结尾的单词做加密处理。
     * 加密规则如下：若单词长度为偶数个字符，则将组成该单词的所有字母循环左移一次，
     * 否则循环右移一次。例如：单词able经循环左移一次后变为blea；单词abide经循环右移一次后变为eabid。
     */
    public static void question4(){

        String[] str = new String[]{"alike","aside","alive","safasf","able","absolute","acknowledge"};

        List<String> list = new ArrayList<>();//声明一个列表存储改变后的单词

        for (String s : str) {
            if(s.startsWith("a") && s.endsWith("e")){
                //左移
                if(s.length() % 2 == 0){

                    char[] chars = s.toCharArray();

                    char ch = chars[0];

                    for(int i=0;i<chars.length-1;i++){
                        chars[i] = chars[i+1];
                    }

                    chars[chars.length-1] = ch;

                    String temp = new String(chars);

                    list.add(temp);
                }else{ //右移

                    char[] chars = s.toCharArray();

                    char ch = chars[chars.length-1];

                    for(int i=chars.length-1;i>0;i--){
                        chars[i] = chars[i-1];
                    }

                    chars[0] = ch;

                    String temp = new String(chars);

                    list.add(temp);
                }
            }else{
                list.add(s);
            }
        }

        System.out.println(list);

        System.out.println(Arrays.toString(str));
    }

    /**
     * 一个正整数有可能可以被表示为 n(n>=2) 个连续正整数之和，如：
     * 15=1+2+3+4+5 15=4+5+6 15=7+8
     * 请编写程序， 根据输入的任何一个正整数，找出符合这种要求的所有连续正整数序列。
     * 输入数据： 一个正整数，以命令行参数的形式提供给程序。
     * 输出数据： 在标准输出上打印出符合题目描述的全部正整数序列，
     * 每行一个序列，每个序列都从该序列的最小正整数开始、
     * 以从小到大的顺序打印。如果结果有多个序列，
     * 按各序列的最小正整数的大小从小到大打印各序列。此外，序列不允许重复，
     * 序列内的整数用一个空格分隔。如果没有符合要求的序列，输出 “ NONE” 。
     * 例如，对于 15，其输出结果是：
     * 1 2 3 4 5
     * 4 5 6
     * 7 8
     */
    public static void question5(int n){

        int sum;    //连续的几个数的和

        boolean flag = false; //用于不符合条件的输出信息

        for(int i=1;i<=n/2+1;i++){ //从1~n/2+1逐一尝试是否满足要求

            sum = 0;

            for(int j=i;j<=n/2+1;j++){

                sum += j;

                //判断和是否等于n 并且 要保证至少有两个数构成和
                if(sum == n && sum != j){

                    System.out.print(n+"=");

                    for(int k=i;k<=j;k++){
                        if(k == j){
                            System.out.println(k);
                        }else{
                            System.out.print(k+"+");
                        }

                        flag = true;
                    }
                }
            }
        }

        if(!flag){
            System.out.println("NONE");
        }


    }

    /**
     * Bob平时每天要吃四顿饭：早、中、晚、夜，但是他的食谱很单调，只吃鸡蛋、面包、汉堡和披萨这四种东西，并且每餐只吃其中的一种。
     * 如果从12月1号开始，要求Bob每天的食谱都不重复，请给出一个不重复的周期内Bob每天的食谱。
     * 例如：1号 鸡蛋、面包、汉堡、披萨，2号 鸡蛋、面包、披萨、汉堡。即每天四餐吃的东西不重复
     *
     * 思路:  设perm(ri,Ri)是求元素ri和集合Ri的全排列
     *        e.g 求1,2,3的全排列就是求
     *        1,perm(2,3)
     *        2,perm(1,3)
     *        3,perm(1,2)
     *        的全排列即
     *        perm(1,perm(2,3))
     *        perm(2,perm(1,3))
     *        perm(3,perm(1,2))
     *
     *       perm(2,3)又是等于求
     *       perm(2,perm(3))
     *       perm(3,perm(2))
     */
    private static int index = 1;
    public static void question6(String[] array, Stack<String> stack){

        if(array.length <= 0){
            System.out.print(index++ +"号:");
            //进入叶子结点,输出栈中的内容
            System.out.println(stack);
        }else{
            //不是叶子结点
            for(int i = 0;i < array.length;i++){
                //tempArray是一个临时数组
                String[] tempArray = new String[array.length-1];

                System.arraycopy(array,0,tempArray,0,i);

                System.arraycopy(array,i+1,tempArray,i,array.length-i-1);

                stack.push(array[i]);

                question6(tempArray,stack);

                stack.pop();
            }
        }
    }

    /**
     * 国庆假期，小张选定苏州、无锡、常州、南京、杭州作为旅游候选城市，小张按照按照如下规则决定最终旅游地：
     * （1）如果去苏州，那么也去无锡； suzhou && wuxi == 1
     * （2）无锡和常州两个城市只能去一个； wuxi 异或 changzhou == 1
     * （3）常州和南京要么都去，要么都不去；  changzhou 异或 nanjing == 0
     * （4）南京和杭州至少要去一个；  nanjing || hangzhou == 1
     * （5）如果去杭州，那么苏州、南京也都要去。 hangzhou
     * 根据上述条件，判断小张去了哪些城市旅游。
     */
    public void question7(){

        boolean suzhou,wuxi,changzhou,nanjing,hangzhou;







    }

    /**
     * 给定一个十进制正整数n(0 < n < 1000000000)，每个数位上数字均不为0。n的位数为m。
     * 现在从m位中删除k位(0<k < m)，求生成的新整数最小为多少？
     * 例如: n = 9128456, k = 2, 则生成的新整数最小为12456
     * 输入
     * 第一行t, 表示有t组数据；
     * 接下来t行，每一行表示一组测试数据，每组测试数据包含两个数字n, k。
     * 输出
     * t行，每行一个数字，表示从n中删除k位后得到的最小整数。
     * 样例输入
     * 2
     * 9128456 2
     * 1444 3
     * 样例输出
     * 12456
     * 1
     */
    public static void question8(int srcNumber,int k){

        String str = srcNumber + "";    //转成字符串方便求出长度

        int arrLength = str.length();

        int[] intArray = new int[arrLength];//声明一个长度为str.length()的数组

        for(int i=0;i<intArray.length;i++){

            int quotient = srcNumber / 10; //商

            int remainder = srcNumber % 10; //余数

            intArray[arrLength-1-i] = remainder; //从后面向前存入余数

            srcNumber = quotient; //将原数字变成上面的商
        }

        for(int i=0;i<k;i++){
            //定义一个最大值
            int max = 0;

            int count = 0;

            for(int j=0;j<intArray.length;j++){
                if(max < intArray[j]){
                    max = intArray[j]; //赋值给max

                    count = j;  //记录索引
                }
            }

            intArray[count] = -1; //将最大值处置为-1
        }

        for (int i : intArray) {
            if(i == -1){
                continue;
            }

            System.out.print(i);
        }
    }



}
