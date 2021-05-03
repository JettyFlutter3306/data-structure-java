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

    private ThreadNode<T> front = null;  //front指向p在中根次序下的前驱结点

    /**
     * 中序线索化以 p 结点为根的子树,算法描述如下:
     * 1.中序线索化 p 的结点的左子树
     * 2.若 p 的左子树为空,则设置 p.left 链为指向前驱结点 front 的线索,设置左线索标记 p.lTag 为1(true)
     * 3.若 p 的右子树为空,则设置右线索标记 p.rTag 为1,此时还无法设置 p.right 链尾右线索,但是可以设置
     * 其前驱 front 的 right 链尾右线索,若前驱 front 的右线索标记 front.rTag 为1,则设置前驱 front.right
     * 链为指向后继 p 的线索
     * 4.使 front 指向结点 p,意为当前 p 结点是 p 在中根次序下的下一个访问结点的前驱
     * 5.中序线索化 p 结点的右子树
     * @param p          结点
     */
    private void inOrderThread(ThreadNode<T> p) {

        if(p != null){
            inOrderThread(p.left);  //中序线索化 p 的左子树

            if(p.left == null){  //若 p 的左子树为空
                p.lTag = true;  //设置左线索标记
                p.left = front;  //设置 p.left 为指向前驱 front 的线索
            }

            if(p.right == null){  //若 p 的右子树为空
                p.rTag = true;  //设置右线索标记
            }

            if(front != null && front.rTag){   //设置前驱 front.right 为指向后继 p 的线索
                front.right = p;
            }

            front = p;  //front记得当前 p, 即是 p 下个访问结点的前驱

            inOrderThread(p.right);  //中序线索化 p 的右子树
        }
    }

    /**
     * 返回 p 在中根次序下的后继结点
     * 1.若p.rTag == -1则p.right是指向p的后继结点的线索,或p没有后继结点
     * 2.若p.rTag == 0表示p.right指向p的右孩子,则p的后继结点是p右子树在中根次序下的第一个访问结点.也就是p右孩子的最左边的一个
     * 后代结点
     * @param p         结点
     * @return          后继结点
     */
    public ThreadNode<T> inNext(ThreadNode<T> p){

        if(p.rTag){  //右线索标记,则 p.right 指向 p 的后继结点
            return p.right;
        }

        p = p.right;  //进入 p 的右子树

        while(!p.lTag){  //找到最左边的后代结点
            p = p.left;
        }

        return p;
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

    /**
     * 中根次序遍历中序线索二叉树,非递归算法
     */
    public void inOrder(){

    }

    /**
     * 返回p结点在中根次序下的前驱结点
     * @param p         结点
     * @return          前驱结点
     */
    public ThreadNode<T> inPrev(ThreadNode<T> p){

        return null;
    }

    /**
     * 返回p在先根次序下的后继结点
     * @param p         结点
     * @return          后继结点
     */
    public ThreadNode<T> preNext(ThreadNode<T> p){

        if(!p.lTag){  //若p有左孩子,则p的左孩子是p的后继
            return p.left;
        }

        while(p.rTag && p.right != null){  //否则,p后继是最远中序祖先的右孩子
            p = p.right;  //沿着右线索向上,寻找到最远中序祖先
        }

        return p.right;  //祖先的右孩子是后继
    }

    /**
     * 先根次序遍历中序线索二叉树
     */
    public void preOrder(){

    }

    /**
     * 返回p在后根次序下的前驱结点
     * @param p         结点
     * @return          前驱结点
     */
    public ThreadNode<T> postPrev(ThreadNode<T> p){

        return null;
    }

    /**
     * 后根次序遍历
     */
    public void postOrderPrevious(){

    }


}
