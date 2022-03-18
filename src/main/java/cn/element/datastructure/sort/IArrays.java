package cn.element.datastructure.sort;

/**
 * Array工具类
 */
public class IArrays {

    /**
     * 交换数组对应下标的数值
     * 前提条件是: i != j
     */
    public static void swap(int[] keys, int i, int j) {
        if (i != j) {
            keys[i] = keys[i] ^ keys[j];
            keys[j] = keys[i] ^ keys[j];
            keys[i] = keys[i] ^ keys[j];
        }
    }
}
