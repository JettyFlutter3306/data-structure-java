package cn.element.tree;

/**
 * 声明中序线索二叉树类
 */
public class ThreadBinaryTree<T> {

    public ThreadNode<T> root;  //根结点

    /**
     * 构造空二叉树
     */
    public ThreadBinaryTree() {

        this.root = new ThreadNode<>(null);
    }

    /**
     * 以标明空子树的先根遍历序列构造二叉树
     * @param preList       先根次序遍历序列
     */
    public ThreadBinaryTree(T[] preList) {

        this.root = create(preList);

        this.inOrderThread(this.root);  //中序线索化二叉树
    }

    private void inOrderThread(ThreadNode<T> root) {

    }

    /**
     * 构建二叉树,算法同 BinaryTree 类
     */
    private ThreadNode<T> create(T[] preList){


        return null;
    }

    /**
     * 判断是否是空二叉树
     */
    public boolean isEmpty(){

        return this.root.left == null && this.root.right == null;
    }


}
