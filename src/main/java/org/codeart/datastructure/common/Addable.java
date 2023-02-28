package org.codeart.datastructure.common;

/**
 * 可相加接口
 * T 表示数据元素的数据类型
 */
public interface Addable<T> {

    int add(T t);  // 加法,约定两个元素的相加规则

    boolean removable();  //约定删除元素条件

}
