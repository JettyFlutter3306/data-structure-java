package cn.element.tree;

import cn.element.common.AbstractBaseEntity;

/**
 * 声明线索二叉树的二叉链表结点类
 */
public class ThreadNode<T> extends AbstractBaseEntity {

    public T data;  //数据元素

    public ThreadNode<T> left; //左结点

    public ThreadNode<T> right;  //右结点

    public boolean lTag;  //左线索标记

    public boolean rTag;  //右线索标记

    public ThreadNode(T data, ThreadNode<T> left, ThreadNode<T> right, boolean lTag, boolean rTag) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.lTag = lTag;
        this.rTag = rTag;
    }

    public ThreadNode(T data) {
        this(data,null,null,false,false);
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
