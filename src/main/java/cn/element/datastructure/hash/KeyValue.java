package cn.element.datastructure.hash;

/**
 * 声明KeyValue<K,V>映射元素类如下
 * @param <K>       键类型
 * @param <V>       值类型
 */
public class KeyValue<K,V> {

    final K key;  //键,最终变量,只能赋值一次

    V value;  //值

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    /**
     * 返回散列码,覆盖Object类的方法,最终方法,不能被覆盖
     */
    @Override
    public final int hashCode() {
        return this.key.hashCode();
    }

    /**
     * 比较对象是否相等,仅仅比较关键字,覆盖
     */
    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof KeyValue<?,?> && this.key.equals(((KeyValue<K,V>)obj).key);
    }

    @Override
    public String toString() {
        return "(" + this.key + "," + this.value + ")";
    }

}
