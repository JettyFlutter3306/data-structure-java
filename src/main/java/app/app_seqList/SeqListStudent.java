package app.app_seqList;

import list.SeqList;
import list.SortedSeqList;

public class SeqListStudent {

    //分类统计线性表list的元素信息,分段信息存于grade数组,返回保存统计结果的数组
    public static int[] groupCount(SeqList<Student> list,int grade[]){

        int[] result = new int[grade.length];   //result数组保存统计结果

        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);

            for (int j = 0; j < grade.length - 1; j++) {        //遍历成绩分段数组
                if(student.getScore() >= grade[j] && student.getScore() < grade[j+1]){ //判断score范围

                    result[j]++;

                    break;  //退出内层循环
                }
            }
        }

        return result;
    }

    //输出线性表list元素及分类统计结果
    public static void printCount(SeqList<Student> list,String[] titles,int[] result){

        System.out.println("学生集合:" + list + "\n 共" + list.size() + "人, 成绩统计: ");

        for (int i = 0; i < titles.length; i++) {
            System.out.print(titles[i] + result[i] + "人, ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Student[] group = {
                new Student("王红",85),
                new Student("张明",75),
                new Student("李强",90),
                new Student("崔小兵",80),
                new Student("陈新诺",60),
                new Student("吴宇",65)
        };

        SeqList<Student> seqList = new SeqList<>(group);    //构造顺序表,由数组提供初始值

        seqList.insert(new Student("崔小兵",70));  //尾插入

        int[] grade = {0,60,70,80,90,100};  //指定分段信息

        String[] titles = {"不及格","及格","中等","良好","优秀"};  //字符串数组指定分类的名称

        int[] result = SeqListStudent.groupCount(seqList,grade);    //分类统计,返回存放统计结果的数组

        SeqListStudent.printCount(seqList,titles,result);   //打印统计结果

        String name = "崔小兵";

        Student key = new Student(name,0);      //key包含姓名,比较相等,按姓名查找

        System.out.println("\"" + name + "\"的成绩是: " + seqList.get(seqList.search(key)).getScore());

        System.out.println("删除" + seqList.remove(key));

        SeqList<Student> seqList1 = new SortedSeqList<>(seqList);   //拷贝构造

        result = SeqListStudent.groupCount(seqList1,grade); //分类统计

        SeqListStudent.printCount(seqList1,titles,result);

        int score = 70;

        key = new Student("",score);

        System.out.println("成绩为" + score + "分的学生是: " + seqList1.get(seqList1.search(key)).getName());




    }
}
