package cn.element.datastructure.hash;

import cn.element.datastructure.common.Map;
import cn.element.datastructure.list.SinglyList;
import cn.element.datastructure.list.node.Node;

/**
 * 声明HashMap<K, V>散列映射如下,实现Map<K, V>接口使用散列表存储映射元素实现查找,插入,删除方法
 *
 * @param <K> 键
 * @param <V> 值
 */
public class HashMap<K, V> implements Map<K, V> {

    protected HashSet<KeyValue<K, V>> set;  //散列表,元素是 KeyValue<K,V>

    /**
     * 构造容量为length的散列映射
     *
     * @param length 容量
     */
    public HashMap(int length) {
        this.set = new HashSet<>(length);
    }

    /**
     * 构造默认容量的散列表
     */
    public HashMap() {
        this.set = new HashSet<>();
    }

    @Override
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    @Override
    public int size() {
        return this.set.size();
    }

    /**
     * 返回关键字key的值
     *
     * @param key 关键字
     * @return V
     */
    @Override
    public V get(K key) {
        KeyValue<K, V> find = this.set.search(new KeyValue<>(key, null));  //查找

        return find != null ? find.value : null;  //查找成功,返回值,否则返回null
    }

    /**
     * 添加映射元素,关键字相同时,替换值
     *
     * @param key   关键字
     * @param value 值
     * @return V
     */
    @Override
    public V put(K key, V value) {
        KeyValue<K, V> keyValue = new KeyValue<>(key, value);

        if (!this.set.add(keyValue)) {  //插入不成功,表示关键字重复
            this.set.search(keyValue).value = value;  //查找关键字重复元素,替换值
        }

        return value;
    }

    @Override
    public V remove(K key) {
        return this.set.remove(new KeyValue<K, V>(key, null)).value;
    }

    @Override
    public boolean containsKey(K key) {
        return this.set.contains(new KeyValue<>(key, null));
    }

    @Override
    public void clear() {
        this.set = new HashSet<>();
    }

    @Override
    public Object[] values() {
        SinglyList<KeyValue<K, V>>[] lists = this.set.getTable();

        Object[] arr = new Object[this.set.size()];

        int i = 0;

        for (SinglyList<KeyValue<K, V>> list : lists) {
            for (Node<KeyValue<K, V>> p = list.head.next; p != null; p = p.next) {
                V value = p.data.getValue();
                arr[i++] = value;
            }
        }

        return arr;
    }

    /**
     * 返回关键字集合
     */
    public HashSet<K> keySet() {
        HashSet<K> set = new HashSet<>();

        SinglyList<KeyValue<K, V>>[] lists = this.set.getTable();

        for (SinglyList<KeyValue<K, V>> list : lists) {
            for (Node<KeyValue<K, V>> p = list.head.next; p != null; p = p.next) {
                K key = p.data.getKey();
                set.add(key);
            }
        }

        return set;
    }

    @Override
    public String toString() {
        return this.set.toString();
    }
}
