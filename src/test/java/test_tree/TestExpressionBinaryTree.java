package test_tree;

import org.codeart.datastructure.tree.expression.ExpressionBinaryTree;
import org.junit.Test;

public class TestExpressionBinaryTree {

    /**
     * 测试创建表达式二叉树
     */
    @Test
    public void testSuffixExpression(){
        String prefix = "-+45 *-10 15 /+25 35 -60 40 11";  //前缀表达式

        ExpressionBinaryTree tree = new ExpressionBinaryTree(prefix);

        System.out.println("前缀表达式:  ");
        tree.printPreOrder();  //输出先根次序遍历序列

        System.out.println("中缀表达式:  ");
        tree.printInOrder();  //输出中根次序遍历序列

        System.out.println("后缀表达式:  ");
        tree.printPostOrder();  //输出后根次序遍历序列

        System.out.println("value = " + tree.toValue());

    }
}
