package cn.element.datastructure.tree;

/**
 * 声明完全二叉树类,集成二叉树类
 */
public class CompleteBinaryTree<T> extends BinaryTree<T> {

    /**
     * 构造空二叉树
     */
    public CompleteBinaryTree() {
        super();
    }

    /**
     * 以层次遍历序列 levelList 构造完全二叉树
     * @param levelList         层次遍历序列
     */
    public CompleteBinaryTree(T[] levelList) {

    }

    /**
     * 在层次序列最后插入 x 结点,返回插入的结点,覆盖
     * @param x         结点
     * @return          插入的结点
     */
    @Override
    public BinaryNode<T> insert(T x) {
        return null;
    }

    /**
     * 删除层次序列的最后结点
     */
    public void removeLast(){

    }
}
