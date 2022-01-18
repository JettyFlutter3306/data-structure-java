package cn.element.datastructure.hash;

import cn.element.datastructure.common.Map;
import cn.element.datastructure.search.sort_tree.BinarySortedTree;

public class TreeMap<K extends Comparable<? super K>,V> implements Map<K,V> {

    BinarySortedTree<SortedKeyValue<K,V>> set;  //二叉排序树表示互异的排序集合

    public TreeMap() {  //构造空树映射
        this.set = new BinarySortedTree<>();  //构造空二叉树
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 返回关键字key映射的值
     */
    @Override
    public V get(K key) {
        SortedKeyValue<K,V> keyValue = new SortedKeyValue<>(key,null);

        SortedKeyValue<K,V> node = this.set.search(keyValue);  //查找

        return node != null ? node.value : null;
    }

    /**
     * 添加映射元素,关键字相同时,替换值
     */
    @Override
    public V put(K key, V value) {
        SortedKeyValue<K,V> keyValue = new SortedKeyValue<>(key,value);

        if(!this.set.add(keyValue)){  //插入不成功,表示关键字重复
            this.set.search(keyValue).value = value;  //查找关键字重复元素,替换值
        }

        return value;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object[] values() {
        return new Object[0];
    }

    public BinarySortedTree<K> keySet() {

        return null;
    }
}
