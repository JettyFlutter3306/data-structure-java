package test_tree;

import org.codeart.datastructure.tree.BinaryTree;
import org.codeart.datastructure.tree.BinaryTrees;
import org.junit.Test;

public class TestBinaryTree {

    /**
     * 测试创建二叉树
     */
    @Test
    public void testBinaryTree() {
        String[] preList = {"A", "B", "D", null, "G", null, null, null, "C", "E", null, null, "F", "H"};  //先根遍历序列

        BinaryTree<String> tree = new BinaryTree<>(preList);

        System.out.println(tree.size());  //返回结点数
        System.out.println(tree.height());  //返回高度

        System.out.println("先根遍历二叉树: ");  //A B D G C E F H
        tree.printPreOrder();

        System.out.println("中根遍历二叉树: ");  //D G B A E C H F
        tree.printInOrder();

        System.out.println("后根遍历二叉树: ");  //G D B E H F C A
        tree.printPostOrder();

        tree.insert(tree.root.left, "X", true);  //插入X结点作为B结点的左孩子
        tree.insert(tree.root.right, "Y", false);  //插入Y结点作为C结点的右孩子
        tree.insert("Z");  //插入根结点

        System.out.println(tree);

        tree.printGenList();  //输出广义表示  Z(A(B(X(D(^,G),^),^),C(E,Y(^,F(H,^)))),^)
    }

    /**
     * 测试使用广义表字符串创建二叉树
     */
    @Test
    public void testGenList() {
        String genList = "AA(BB(DD(^,G),^),C(E,F(H,^)))";  //二叉树广义表表示

        BinaryTree<String> tree = BinaryTrees.createByGenList(genList);  //创建二叉树

        tree.printGenList();  //AA(BB(DD(^,G),^),C(E,F(H,^)))
    }

    /**
     * 测试遍历二叉树非递归算法
     */
    @Test
    public void testBinaryTreeTraverse() {
        String[] preList = {"A", "B", "D", null, "G", null, null, null, "C", "E", null, null, "F", "H"};  //先根遍历序列

        BinaryTree<String> tree = new BinaryTree<>(preList);

        tree.preOrderTraverse();  //A B D ^ G ^ ^ ^ C E ^ ^ F H ^ ^
    }

    /**
     * 测试二叉树的层次遍历
     */
    @Test
    public void testLevelTraverse() {
        String[] preList = {"A", "B", "D", null, "G", null, null, null, "C", "E", null, null, "F", "H"};  //先根遍历序列

        BinaryTree<String> tree = new BinaryTree<>(preList);

        tree.printLevelOrder();
    }

    /**
     * 测试打印二叉树的层次结构
     */
    @Test
    public void testBinaryStructure() {
        String[] preList = {"A", "B", "D", null, "G", null, null, null, "C", "E", null, null, "F", "H"};  //先根遍历序列

        BinaryTree<String> tree = new BinaryTree<>(preList);

        BinaryTrees.show(tree);
    }
}







