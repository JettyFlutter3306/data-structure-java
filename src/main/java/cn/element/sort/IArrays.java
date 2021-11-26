package cn.element.sort;

/**
 * Array工具类
 */
public class IArrays {

    /**
     * 交换数组对应下标的数值
     */
    public static void swap(int[] keys,int i,int j){

        int temp = keys[i];
        keys[i] = keys[j];
        keys[j] = temp;
    }
}
