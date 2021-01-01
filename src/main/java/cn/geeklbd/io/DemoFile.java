package cn.geeklbd.io;

import java.io.File;

public class DemoFile {

    public static void main(String[] args) {
        //与系统相关的分隔符
//        System.out.println(File.pathSeparator);

        //路径分隔符
//        System.out.println(File.separator);

        //File构造方法
        File file = new File("myout.txt");

        String absolutePath = file.getAbsolutePath(); //绝对路径
        String path = file.getPath(); //相对路径
        boolean b = file.exists();  //是否存在
        long length = file.length();    //获取大小
        boolean b1 = file.isDirectory();    //是否是文件夹

        System.out.println("file = " + file);
        System.out.println("b = " + b);
        System.out.println("absolutePath = " + absolutePath);
        System.out.println("path = " + path);
        System.out.println("length = " + length);
        System.out.println("b1 = " + b1);



    }
}
