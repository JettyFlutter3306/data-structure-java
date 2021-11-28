package cn.element.tree;

import cn.element.common.AbstractTree;
import cn.element.common.Queue;
import cn.element.queue.LinkedQueue;
import cn.element.stack.LinkedStack;
import cn.element.common.Stack;
import cn.element.util.SerializeUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 声明二叉树类 BinaryTree<T>
 * 采用二叉链表存储,其中成员变量 root 指向二叉树的根结点
 */
public class BinaryTree<T> extends AbstractTree<T> {

    public BinaryNode<T> root;  //根结点,二叉链表结点结构

    /**
     * 构造空二叉树
     */
    public BinaryTree() {
        this.root = null;
    }

    /**
     * 实现二叉树的深拷贝
     * @param binaryTree        二叉树
     */
    public BinaryTree(BinaryTree<T> binaryTree) throws IOException, ClassNotFoundException {
        this.root = SerializeUtil.deepClone(binaryTree.root);

        this.i = 0;
    }

    /**
     * 构造二叉树,preList 数组指定二叉树标明空子树的先根遍历序列
     * @param preList           先根遍历序列
     */
    public BinaryTree(T[] preList) {
        this.root = createBinaryTree(preList);
    }

    private int i = 0;  //声明 i 为全局变量,表示数组的索引, i 不可以作为方法的参数和局部变量
    /**
     * 以从 i 开始的标明空子树的先根遍历,创建一棵以 preList[i] 为根的子树,返回根结点,递归算法
     * @param preList       先根遍历序列
     * @return              BinaryNode
     */
    private BinaryNode<T> createBinaryTree(T[] preList) {
        BinaryNode<T> p = null;

        if (i < preList.length) {
            T element = preList[i];  //拿到先根遍历序列的对应元素

            i++;  //i自增1

            if (element != null) {  //不能 element != "^",因为 T 不一定是 String
                p = new BinaryNode<>(element);  //创建叶子结点

                p.left = createBinaryTree(preList);  //创建 p 的左子树,递归调用

                p.right = createBinaryTree(preList);  //创建 p 的右子树,递归调用
            }
        }

        return p;
    }

    /**
     * 判断是否是空二叉树
     */
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * 返回关键字为key结点所在的层次
     * @param key           关键字
     * @return              层次
     */
    @Override
    public int level(T key) {
        return 0;
    }

    /**
     * 返回二叉树结点数
     */
    @Override
    public int size() {
        return size(this.root);
    }

    /**
     * 求以 p 结点为根结点的二叉树的结点数量
     */
    private int size(BinaryNode<T> p){
        int temp = 0;

        if(p != null){
            temp++;

            temp += size(p.left);

            temp += size(p.right);
        }

        return temp;
    }

    /**
     * 返回二叉树高度
     */
    @Override
    public int height() {
        return height(this.root);
    }

    /**
     * 求以 p 结点为根结点的二叉树的高度
     * 深度优先遍历(递归)
     * 求出左子树和右子树二者最大深度再加1
     */
    private int height(BinaryNode<T> p) {
        if (p == null) {
            return 0;
        }

        return 1 + Math.max(height(p.left), height(p.right));
    }

    /**
     * 先根次序遍历二叉树,递归算法
     */
    @Override
    public void printPreOrder() {
        printPreOrder(this.root);

        System.out.println();
    }

    /**
     * 先根次序遍历以 root 结点为根的二叉树 (根 -> 左 -> 右)
     * @param p         根结点
     */
    private void printPreOrder(BinaryNode<T> p) {
        if (p != null) {  //若二叉树不为空
            System.out.print(p.data + " ");  //先访问当前结点元素

            printPreOrder(p.left);  //按先根次序遍历 p 的左子树,递归调用,参数为左子树

            printPreOrder(p.right);  //按先根次序遍历 p 的右子树,递归调用,参数为右子树
        }
    }

    /**
     * 后根遍历二叉树
     */
    @Override
    public void printPostOrder() {
        printPostOrder(this.root);

        System.out.println();
    }

    /**
     * 后根次序遍历以  p 结点为根的子树,递归方法
     */
    private void printPostOrder(BinaryNode<T> p) {
        if (p != null) {
            printPostOrder(p.left);

            printPostOrder(p.right);

            System.out.print(p.data + " ");
        }
    }

    @Override
    public void printInOrder() {
        printInOrder(this.root);

        System.out.println();
    }

    /**
     * 中根次序遍历以 p 结点为根的子树.递归方法
     * @param p         结点
     */
    private void printInOrder(BinaryNode<T> p) {
        if (p != null) {
            printInOrder(p.left);

            System.out.print(p.data + " ");

            printInOrder(p.right);
        }
    }

    /**
     * 先根遍历二叉树的非递归算法
     */
    public void preOrderTraverse() {
        System.out.print("先根次序遍历(非递归算法):  \n");

        Stack<BinaryNode<T>> stack = new LinkedStack<>();  //多态创建空栈

        BinaryNode<T> p = this.root;

        while (p != null || !stack.isEmpty()) {  //p非空或栈非空时
            if (p != null) {
                System.out.print(p.data + " ");  //访问当前结点

                stack.push(p);  //p结点入栈

                p = p.left;  //进入左子树
            } else {  //p为空且栈非空时
                System.out.print("^ ");

                p = stack.pop();  //p指向出栈结点

                p = p.right;  //进入右子树
            }
        }

        System.out.println();
    }

    /**
     * 层次遍历二叉树
     */
    @Override
    public void printLevelOrder() {
        System.out.print("层次遍历:  \n");

        Queue<BinaryNode<T>> queue = new LinkedQueue<>();  //创建空队列

        BinaryNode<T> p = this.root;  //根结点没有入队

        while (p != null) {
            System.out.print(p.data + " ");  //访问 p 结点

            if (p.left != null) {
                queue.add(p.left);  //p结点的左结点入队
            }

            if (p.right != null) {
                queue.add(p.right);  //p结点的右结点入队
            }

            p = queue.poll();  //p指向出队结点,若队列为空则返回 null
        }

        System.out.println();
    }

    @Override
    public BinaryNode<T> insert(T x) {
        return this.root = new BinaryNode<>(x,this.root,null);
    }

    /**
     * 插入 x 为 parent 结点的左/右孩子,leftChild指定孩子,取值为 true(左), false(右)
     * parent 的原左/右孩子成为 x 结点的左/右孩子,返回插入结点
     * 若 x == null,不插入,返回 null,若 parent == null, Java 抛出空对象异常
     * @param parent            根结点
     * @param x                 元素
     * @param leftChild         是否是左子树
     * @return                  TreeNode
     */
    @Override
    public BinaryNode<T> insert(BinaryNode<T> parent, T x, boolean leftChild) {
        if (x == null) {
            return null;
        }

        if (leftChild) {  //是左子树
            return parent.left = new BinaryNode<>(x,parent.left,null);
        }

        return parent.right = new BinaryNode<>(x,null,parent.right);  //是右子树
    }

    @Override
    public void remove(BinaryNode<T> p, int i) {

    }

    /**
     * 删除二叉树的所有结点
     */
    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public BinaryNode<T> search(T key) {
        return null;
    }

    @Override
    public boolean contains(T key) {
        return false;
    }

    @Override
    public T remove(T key) {
        return null;
    }

    /**
     * 删除 parent 结点的左或右子树, leftChild 指定子树,取值为 true(左), false(右)
     * @param parent                父结点
     * @param leftChild             是否是左子树
     */
    public void remove(BinaryNode<T> parent,boolean leftChild) {
        if (leftChild) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }

    /**
     * 输出二叉树的广义表示字符串
     */
    public void printGenList() {
        System.out.println("二叉树的广义表示: ");

        printGenList(this.root);

        System.out.println();
    }

    private void printGenList(BinaryNode<T> p) {
        if (p == null) {  //若二叉树为空
            System.out.print("^");  //输出空子树标记
        } else {
            System.out.print(p.data);  //访问当前结点

            if (p.left != null || p.right != null) {  //非叶子结点,有子树
                System.out.print("(");

                printGenList(p.left);  //输出 p 的左子树,递归调用

                System.out.print(",");

                printGenList(p.right);  //输出 p 的右子树,递归调用

                System.out.print(")");
            }
        }
    }

    /**
     * 二叉树结构示例:
     *              1
     *            /   \
     *          2       3
     *        /   \   /   \
     *       4     5  6    7
     */
    @Override
    public String toString() {
        return this.toString(this.root);
    }

    /**
     * 返回先根次序遍历以 p 为根的子树描述串,递归算法
     * @param p         根结点
     * @return          描述串
     */
    public String toString(BinaryNode<T> p) {
        if (p == null) {
            return "^ ";  //输出空子树标记
        }

        return p.data.toString() + " " + toString(p.left) + toString(p.right);  //递归调用
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }
}
