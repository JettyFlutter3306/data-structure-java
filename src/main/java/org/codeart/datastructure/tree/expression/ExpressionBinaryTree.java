package org.codeart.datastructure.tree.expression;

import org.codeart.datastructure.tree.BinaryTree;
import org.codeart.datastructure.tree.BinaryNode;

/**
 * 声明表达式二叉树类
 * 表达式二叉树类是指采用二叉树表达式,例如,表示 45+(10-15)*((25+35)/(60-40))-11 中缀表达式的一棵表达式二叉树
 * 每个结点包含操作数和运算符,操作数结点都是叶子结点,双目运算符都是 2 度结点,参加运算的两个操作数分别是运算符的左
 * ,右孩子结点,按后根次序遍历可得到后缀表达式
 */
public class ExpressionBinaryTree extends BinaryTree<ExpData> {

    /**
     * 构造空二叉树
     */
    public ExpressionBinaryTree() {
        super();
    }

    /**
     * 以前缀表达式 prefix 构造表达式二叉树
     * @param prefix            前缀表达式
     */
    public ExpressionBinaryTree(String prefix) {
        this.root = this.createPrefix(prefix);
    }

    private int i = 0;  //声明全局变量,表示前缀表达式的索引

    /**
     * 返回所创建子树的根结点,递归算法
     * @param prefix        前缀表达式
     * @return              BinaryNode
     */
    private BinaryNode<ExpData> createPrefix(String prefix) {
        BinaryNode<ExpData> p = null;

        if (i < prefix.length()) {
            char ch = prefix.charAt(i);

            if (ch >= '0' && ch <= '9') {  //遇到数字字符
                int value = 0;

                while (i < prefix.length() && ch != ' ') {  //将整数字符串转换为整数值
                    value = value * 10 + ch - '0';

                    i++;

                    if (i < prefix.length()) {
                        ch = prefix.charAt(i);
                    }
                }

                p = new BinaryNode<>(new ExpData(value,' '));  //创建数值结点,叶子结点

                i++;  //跳过整数后的一个空格
            } else {
                p = new BinaryNode<>(new ExpData(0,prefix.charAt(i)));

                i++;

                p.left = createPrefix(prefix);  //创建左子树

                p.right = createPrefix(prefix);  //创建右子树
            }
        }

        return p;
    }

    /**
     * 计算算术表达式,返回整数值
     */
    public int toValue() {
        return this.toValue(this.root);
    }

    /**
     * 后根次序遍历并计算以 p 结点为根的子树, p 结点的 value 存储运算结果,递归算法
     */
    private int toValue(BinaryNode<ExpData> p) {
        if (p == null) {
            return 0;
        }

        if (!p.isLeaf()) {
            switch (p.data.operator){  //非叶子结点,根据运算符分别计算
                case '+': p.data.value = toValue(p.left) + toValue(p.right);break;
                case '-': p.data.value = toValue(p.left) - toValue(p.right);break;
                case '*': p.data.value = toValue(p.left) * toValue(p.right);break;
                case '/': p.data.value = toValue(p.left) / toValue(p.right);break;  //整除,若除数为 0,Java将抛出算术异常
            }
        }

        return p.data.value;
    }

    //不支持插入结点,删除子树方法




}
