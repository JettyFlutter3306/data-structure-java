package cn.element.datastructure.common;

/**
 * 声明Map<K,V>映射接口如下
 * 接口映射,K,V分别指定映射元素的关键字和值的数据类型
 */
public interface Map<K,V> {

    boolean isEmpty();  //判断是否为空

    int size();  //返回元素个数

    V get(K key);  //返回关键字key映射的值

    V put(K key,V value);  //添加映射元素(键,值),关键字相同时,替换值

    V remove(K key);  //删除关键字为key的元素,返回被删除元素的值

    boolean containsKey(K key);  //判断是否包含关键字为key元素

    void clear();  //删除所有元素

    Object[] values();  //返回包含值集合的数组,值可以重复

}
