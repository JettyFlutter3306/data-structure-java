package cn.element.matrix;

import cn.element.common.Addable;

/**
 * 声明描述稀疏矩阵非零元素的三元组类Triple
 * 实现 Comparable<T> 接口,提供比较三元组对象大小的 compareTo() 方法:
 * 实现 Addable<T> 接口,提供矩阵加法运算的 add(Triple) 和 removable() 方法
 */
public class Triple implements Comparable<Triple>, Addable<Triple> {

    public int row;  //行号,默认访问权限

    public int column;  //列号

    public int value;  //值

    public Triple() {

    }

    /**
     * 构造方法,参数依次为 行号,列号,值
     * 若行号,列号为负数,则抛出无效参数的异常
     * @param row           行号
     * @param column        列号
     * @param value         值
     */
    public Triple(int row, int column, int value) {

        if(row >= 0 && column >= 0){
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }

    /**
     * 拷贝构造方法,赋值一个三元组对象
     * @param triple            三元组对象
     */
    public Triple(Triple triple) {

        this(triple.row,triple.column,triple.value);
    }

    /**
     * 加法 += 操作,实现 Addable 接口
     * @param triple        三元组对象
     * @return              int
     */
    @Override
    public int add(Triple triple) {

        if(this.compareTo(triple) == 0){
            this.value += triple.value;
        }else{
            throw new IllegalArgumentException("两项的前置系数不同,不能相加.");
        }

        return this.value;
    }

    /**
     * 约定删除元素的条件,实现 Addable<T> 接口
     * 不存储值为0的元素
     */
    @Override
    public boolean removable() {

        return this.value == 0;
    }


    /**
     * 返回矩阵对称位置元素的三元组
     */
    public Triple toSymmetry(){

        return new Triple(this.column,this.row,this.value);
    }

    /**
     * 根据行,列位置比较三元组对象的大小,与元素的值无关,约定三元组的排序次序
     * @param triple            三元组对象
     * @return                  int
     */
    @Override
    public int compareTo(Triple triple) {

        if(this.row == triple.row && this.column == triple.column){
            return 0;
        }

        return (this.row < triple.row || (this.row == triple.row && this.column < triple.column)) ? -1 : 1;
    }

    /**
     * 比较三元组是否相等,比较位置和值
     */
    @Override
    public boolean equals(Object obj) {

        if(this == obj){
            return true;
        }

        if(obj instanceof Triple){
            Triple triple = (Triple) obj;

            if(this.compareTo(triple) == 0){
                return this.value == triple.value;
            }
        }

        return false;
    }

    /**
     * 返回描述字符串
     */
    @Override
    public String toString() {

        return "(" + row + "," + column + "," + value + ")";
    }


}
