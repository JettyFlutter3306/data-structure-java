package org.codeart.datastructure.tree;

import org.codeart.datastructure.common.AbstractBaseEntity;

/**
 * 声明二叉树的二叉链表结点类
 */
public class BinaryNode<T> extends AbstractBaseEntity {

    public T data;  //数据域

    public BinaryNode<T> left;  //左子树

    public BinaryNode<T> right;  //右子树

    /**
     * 构造结点,data指定元素,left,right分别指向左子树和右孩子结点
     * @param data              数据
     * @param left              左子树
     * @param right             右子树
     */
    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * 构造元素为data的叶子结点
     * @param data          数据
     */
    public BinaryNode(T data) {
        this(data,null,null);
    }

    /**
     * 判断是否是叶子结点
     */
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
