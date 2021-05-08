package test_sort;

import cn.element.sort.InsertionSort;
import cn.element.sort.MergeSort;
import cn.element.sort.SelectSort;
import cn.element.sort.SwapSort;
import org.junit.Test;

import java.util.Arrays;

/**
 * 排序测试类
 */
public class TestSort {

    /**
     * 测试直接插入排序
     */
    @Test
    public void test01(){

        int[] arr = {32,26,87,72,26,17};

        InsertionSort.straightInsertionSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 测试泛型类直接插入排序
     */
    @Test
    public void test02(){

        class Student implements Comparable<Student>{
            final String name;
            final int age;

            public Student(String name, int age) {
                this.name = name;
                this.age = age;
            }

            @Override
            public int compareTo(Student o) {

                return this.age - o.age;
            }

            @Override
            public String toString() {
                return "Student{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        }

        Student[] students = new Student[]{
                new Student("张三",12),
                new Student("洛必达",100),
                new Student("特朗普",62),
                new Student("李四",19),
                new Student("宋江",8),
                new Student("雷军",42),
                new Student("马化腾",15),
                new Student("马云",73),
        };

        InsertionSort.straightInsertSort(students);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    /**
     * 测试希尔排序算法
     */
    @Test
    public void test03(){

        int[] array = {38,55,65,97,27,76,27,13,19};

        InsertionSort.shellSort(array);

        System.out.println(Arrays.toString(array));
    }

    /**
     * 测试冒泡排序
     */
    @Test
    public void test04(){

        int[] arr = {32,26,97,72,26,17};

        SwapSort.bubbleSort(arr,false);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 测试快速排序
     */
    @Test
    public void test05(){

        int[] arr = {38,38,97,75,61,19,26,49};

        SwapSort.quickSort(arr);

        System.out.println(Arrays.toString(arr));  //[19, 26, 38, 38, 49, 61, 75, 97]
    }

    /**
     * 测试直接选择排序
     */
    @Test
    public void test06(){

        int[] arr = {38,97,26,19,38,15};

        SelectSort.straightSelectSort(arr);

        System.out.println(Arrays.toString(arr));  //[15, 19, 26, 38, 38, 97]
    }

    /**
     * 测试堆排序
     */
    @Test
    public void test07(){

        int[] arr = {19,38,19,49,97,76,81,13};

        SelectSort.heapSort(arr);
    }

    /**
     * 测试归并排序
     */
    @Test
    public void test08(){

        int[] arr = {97,82,75,53,17,61,70,12,61,58,26};

        MergeSort.mergeSort(arr);

        System.out.println(Arrays.toString(arr));  //[12, 17, 26, 53, 58, 61, 61, 70, 75, 82, 97]
    }
}
