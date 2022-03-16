package cn.element.datastructure.search.sort_tree;

/**
 * 声明二叉树的三叉链表结点类 TriNode<T>
 * 二叉树的三叉链表的结点类
 */
public class TriNode<T> {

    public T data;  //数据域,存储数据

    public TriNode<T> parent;  //父母结点

    public TriNode<T> left;  //左孩子结点

    public TriNode<T> right;  //右孩子结点

    public TriNode(T data, TriNode<T> parent, TriNode<T> left, TriNode<T> right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public TriNode(T data) {
        this.data = data;
    }

    public TriNode() {

    }

    @Override
    public String toString() {
        return "(" + this.data + ")";
    }

    /**
     * 判断是否是叶子结点
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }


}
