package org.codeart.datastructure.search.sort_tree;

/**
 * 二叉排序树类,三叉链表存储,T或T的超类实现Comparable<T>接口
 */
public class BinarySortedTree<T extends Comparable<? super T>> {

    public TriNode<T> root;  //根结点

    /**
     * 构造空二叉排序树
     */
    public BinarySortedTree() {
        this.root = null;
    }

    /**
     * 构造二叉排序树,values数组提供元素
     */
    public BinarySortedTree(T[] values) {
        this();

        for (T value : values) {
            this.add(value);  //二叉树插入元素
        }
    }

    /**
     * 判空
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * 查找关键字为key的元素,若查找成功,返回结点,否则返回null,非递归算法
     */
    public TriNode<T> searchNode(T key) {
        TriNode<T> p = this.root;

        while (p != null && key.compareTo(p.data) != 0) {
            if (key.compareTo(p.data) < 0) {  //若key较小
                p = p.left;  //进入左子树
            } else {
                p = p.right;  //进入右子树
            }
        }

        return p;
    }

    /**
     * 查找关键字为key的元素,若查找成功,返回元素,否则返回null
     */
    public T search(T key) {
        TriNode<T> node = this.searchNode(key);

        return node != null ? node.data : null;
    }

    /**
     * 插入元素x结点,不插入关键字重复元素和空对象,返回插入与否状态
     */
    public boolean add(T x) {
        if (this.root == null) {
            this.root = new TriNode<>(x);  //创建根结点
        } else {
            TriNode<T> p = this.root;

            TriNode<T> parent = null;

            while (p != null) {  //查找确定插入位置
                if (x.compareTo(p.data) == 0) {
                    return false;  //查找不成功,不插入关键字重复的元素
                }

                parent = p;  //记录引用p变成null的前一步位置

                if (x.compareTo(p.data) < 0) {  //关键字x比p小,那么p进入左子树
                    p = p.left;
                } else {  //否则进入右子树
                    p = p.right;
                }
            }

            if (x.compareTo(parent.data) < 0) {  //插入x叶子结点作为parent的左/右孩子
                parent.left = new TriNode<>(x, parent, null, null);
            } else {
                parent.right = new TriNode<>(x, parent, null, null);
            }
        }

        return true;
    }

    /**
     * 删除关键字为key的结点,返回被删除的元素,若没找到则返回null
     */
    public T remove(T key) {
        TriNode<T> p = this.searchNode(key);  //查找关键字为key的元素,若查找不成功则返回null

        if (p != null && p.left != null && p.right != null) {  //找到待删除的结点p,若p是二度结点
            TriNode<T> inSuc = this.first(p.right);  //寻找p在中根次序下的后继结点

            T temp = p.data;  //交换待删除元素,作为返回值
            p.data = inSuc.data;
            inSuc.data = temp;

            p = inSuc;  //转化为删除inSuc,删除1,0度结点
        }

        if (p != null && p == this.root) {  //p是1度或叶子结点,删除根结点,p.parent == null
            if (this.root.left != null) {
                this.root = p.left;  //以p的左孩子顶替作为新的根结点
            } else {
                this.root = p.right;  //以p的右孩子顶替作为新的根结点
            }

            if (this.root != null) {
                this.root.parent = null;
            }

            return p.data;  //返回被删除根结点元素
        }

        if (p != null && p == p.parent.left) {  //p是1度或叶子结点,p是父母的左孩子
            if (p.left != null) {
                p.parent.left = p.left;  //以p的左孩子顶替

                p.left.parent = p.parent;  //p的左孩子的parent域指向p的父母
            } else {
                p.parent.left = p.right;  //以p的右孩子顶替

                if (p.right != null) {
                    p.right.parent = p.parent;
                }
            }
        }

        if (p != null && p == p.parent.right) {  //p是1度或叶子结点,p是父母的右孩子
            if (p.left != null) {
                p.parent.right = p.left;  //以p的左孩子顶替

                p.left.parent = p.parent;
            } else {
                p.parent.right = p.right;  //以p的右孩子顶替

                if (p.right != null) {
                    p.right.parent = p.parent;
                }
            }
        }

        return p != null ? p.data : null;
    }

    /**
     * 返回中根次序遍历二叉树所有结点的描述字符串,迭代遍历,非递归
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        TriNode<T> p = this.first(this.root);  //寻找第一个访问结点,最小值

        while (p != null) {
            if (p.parent == null) {
                sb.append(p.data);

                break;
            }

            sb.append(p.data).append(",");

            p = this.next(p);
        }

        sb.append("]");

        return sb.toString();
    }

    /**
     * 在以p为根的子树中,返回中根次序下第一个访问结点,即根的最左边的子孙结点,最小值
     */
    public TriNode<T> first(TriNode<T> p) {
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
        }

        return p;
    }

    /**
     * 返回p在中根次序下的后继结点
     */
    public TriNode<T> next(TriNode<T> p) {
        if (p != null) {
            if (p.right != null) {  //若p有右孩子,则p的后继是其右子树第一个访问结点
                return this.first(p.right);
            }

            while (p.parent != null) {  //若p没有右孩子,则向上寻找某个祖先结点
                if (p.parent.left == p) {  //若p是其父母的左孩子,则p的后继是其父母
                    return p.parent;
                }

                p = p.parent;
            }
        }

        return null;
    }

    /**
     * 中根次序遍历二叉树(逆序),输出所有结点元素
     */
    public void inOrderPrevious() {

    }

    /**
     * 返回以p为根的子树接中根次序最后一个访问结点
     */
    public TriNode<T> last(TriNode<T> p) {
        return null;
    }

    /**
     * 返回p在中根次序下的前驱结点
     */
    public TriNode<T> previous(TriNode<T> p) {
        return null;
    }

    /**
     * 插入values数组的所有元素
     */
    public void addAll(T[] values) {

    }

    /**
     * 判断是否包含关键字为key元素
     */
    public boolean contains(T key) {
        return false;
    }

    /**
     * 返回元素个数;
     */
    public int size() {
        return 0;
    }

    /**
     * 清空元素
     */
    public void clear() {
        this.root = null;
    }

}
