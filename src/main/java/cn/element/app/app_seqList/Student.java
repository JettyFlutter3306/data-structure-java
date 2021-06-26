package cn.element.app.app_seqList;

import java.io.Serializable;

public class Student implements Comparable<Student>, Serializable {

    private String name;//姓名

    private int score;  //成绩

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {

        //调用String类的equals(Object obj)方法,比较两个字符串是否相等
        return this == obj || (obj instanceof Student) && this.name.equals(((Student) obj).name);
    }

    @Override
    public String toString() {

        return "(" + this.name + "," + this.score +")";
    }

    @Override
    public int compareTo(Student o) {   //比较对象大小

        return this.score - o.score;    //升序排列
    }




}
