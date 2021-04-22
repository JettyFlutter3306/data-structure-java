package cn.element.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现全排列
 *
 * 例如: 数据序列{1,2,3}的全排列如下
 * 123 132 213 231 312 321
 *
 * 思路:
 * 要求1 2 3的全排列,
 * 那么先求1 和 2 3的全排列,再求2 3的全排列记为perm(1,perm(2,3))
 */
public class FullPermutation {

    List<String> list = new ArrayList<>();

    public void getFullPermutation(String[] arr,StringBuilder sb){

        if(arr.length <= 0){
            list.add(sb.toString());
        }else{
            //不是叶子结点
            for (int i = 0; i < arr.length; i++) {
                //tempArray是一个临时数组
                String[] tempArr = new String[arr.length - 1];

                System.arraycopy(arr,0,tempArr,0,i);
                System.arraycopy(arr,i+1,tempArr,i,arr.length-i-1);

                sb.append(arr[i]);

                getFullPermutation(tempArr,sb);

                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    public static void main(String[] args) {

        String[] arr = {"1","2","3"};

        FullPermutation a = new FullPermutation();

        a.getFullPermutation(arr,new StringBuilder());

        System.out.println(a.list);//[123, 132, 213, 231, 312, 321]

    }
}
