package org.codeart.datastructure.hash;

/**
 * 树映射元素类,继承KeyValue<K,V>映射元素类,K,V分别指定关键字和值的数据类型
 * 限定K或K的某个超类必须实现Comparable<K,V>接口
 */
public class SortedKeyValue<K extends Comparable<? super K>,V> extends KeyValue<K,V> implements Comparable<SortedKeyValue<K,V>> {

    public SortedKeyValue(K key, V value) {
        super(key, value);
    }

    @Override
    public int compareTo(SortedKeyValue<K, V> o) {
        return this.key.compareTo(o.key);
    }
}
