package cn.element.datastructure.hash;

import cn.element.datastructure.list.SinglyList;
import cn.element.datastructure.list.node.Node;

/*
 * 声明散列表类HashSet<T>
 * 采用地址链法,成员变量table表示散列数组,元素是SinglyList<T>对象
 * 表示同义词单链表,散列函数hash(x)采用除留余数法
 */
public class HashSet<T> {

    private SinglyList<T>[] table;  //散列表,同义词单链表对象数组

    private int count = 0;  //元素个数

    private static final float LOAD_FACTOR = 0.75f;  //填装因子,元素个数与容量之比

    /**
     * 构造容量为length的散列表
     *
     * @param length 容量
     */
    public HashSet(int length) {
        if (length < 10) {
            length = 10;  //设置最小容量
        }

        this.table = new SinglyList[length];

        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = new SinglyList<>();  //构造空单链表
        }
    }

    /**
     * 构造空散列表,默认容量
     */
    public HashSet() {
        this(16);
    }

    /**
     * 构造散列表,有values数组提供元素
     *
     * @param values 数组
     */
    public HashSet(T[] values) {
        this(values.length);

        for (T value : values) {
            int hashCode = this.hash(value);  //计算出hash值

            this.table[hashCode].insert(value);  //然后插入单链表
        }
    }

    /**
     * 散列函数,计算关键字为x元素的散列地址,若 x == null 抛出空对象异常
     *
     * @param x 元素
     * @return HashCode
     */
    private int hash(T x) {
        int key = Math.abs(x.hashCode());  //每个对象的hashCode()方法返回int

        return key % this.table.length;  //除留余数法,除数是散列表容量
    }

    /**
     * 返回查找到的关键字为 key 的元素,若查找失败则返回null
     *
     * @param key 关键字
     * @return T
     */
    public T search(T key) {
        Node<T> find = this.table[this.hash(key)].search(key);  //在单链表中查找关键字为key的元素

        return find == null ? null : find.data;
    }

    /**
     * 插入 x 元素
     */
    public boolean add(T x) {
        if (this.count >= this.table.length * LOAD_FACTOR) {  //若散列表满了,则扩充容量
            SinglyList<T>[] temp = this.table;  //散列表,暂时保存同义词单链表对象数组

            this.table = new SinglyList[this.table.length * 2];

            for (int i = 0; i < this.table.length; i++) {
                this.table[i] = new SinglyList<>();
            }

            this.count = 0;

            for (int i = 0; i < temp.length; i++) {  //遍历原各同义词单链表,添加原所有元素
                for (Node<T> p = temp[i].head.next; p != null; p = p.next) {
                    this.add(p.data);  //添加元素,递归调用
                }
            }
        }

        boolean flag = this.table[this.hash(x)].insertDifferent(x) != null;

        if (flag) {  //单链表尾插入关键字不重复元素
            this.count++;
        }

        return flag;
    }

    /**
     * 删除关键字为key的元素,返回被删除的元素,若查找不成功返回null
     *
     * @param key 关键字
     * @return T
     */
    public T remove(T key) {
        T x = this.table[this.hash(key)].remove(key);  //同义词单链表删除key元素结点

        if (x != null) {
            this.count--;
        }

        return x;
    }

    /**
     * 判断是否包含关关键字为key的元素
     *
     * @param key 关键字
     * @return boolean
     */
    public boolean contains(T key) {
        return table[this.hash(key)].search(key) != null;
    }

    /**
     * 返回元素的个数
     */
    public int size() {
        return this.count;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * 插入values数组的所有元素
     */
    public void addAll(T[] values) {
        for (T value : values) {
            this.add(value);
        }
    }

    /**
     * 删除所有元素
     * 让引用table重新指向新的数组即可
     * 自动垃圾回收
     */
    public void clear() {
        this.table = new SinglyList[this.table.length];
    }

    public SinglyList<T>[] getTable() {
        return this.table;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        for (SinglyList<T> list : this.table) {
            sb.append(list).append(", ");
        }
        
        sb.append("]");
        return sb.toString();
    }
}
