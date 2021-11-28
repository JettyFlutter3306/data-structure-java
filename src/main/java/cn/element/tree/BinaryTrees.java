package cn.element.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BinaryTree工具类
 */
public class BinaryTrees {

    private static int i = 0;  //声明全局变量 i 表示广义表字符串的索引

    /**
     * 返回以广义表表示字符串 genList 构造的二叉树
     * @param genList           广义表字符串
     * @return                  二叉树
     */
    public static BinaryTree<String> createByGenList(String genList) {
        BinaryTree<String> binaryTree = new BinaryTree<>();

        i = 0;

        if (genList.length() > 0) {
            binaryTree.root = create(genList);  //创建子树,子树根值是 genList[0]
        }

        return binaryTree;
    }

    /**
     * 以从 i 开始的广义表表示字符串创建一棵以 genList[i] 为根的子树,返回根结点,递归算法
     * @param genList           广义表字符串
     * @return                  二叉树
     */
    private static BinaryNode<String> create(String genList) {
        BinaryNode<String> p;

        char ch = genList.charAt(i);

        if (ch == '^') {
            i++;

            return null;
        }

        int end = i;

        while (ch != '(' && ch != ',' && ch != ')') {
            end++;  //一个元素占多个字符,以'(' ')'分割

            ch = genList.charAt(end);
        }

        String str = genList.substring(i,end);  //获得从i ~ end - 1 的子串,深拷贝

        i = end;

        p = new BinaryNode<>(str);  //创建叶子结点

        if (genList.charAt(i) == '(') {
            i++;  //跳过'('

            p.left = create(genList);  //创建左子树,递归调用

            i++;  //跳过','

            p.right = create(genList);  //创建右子树,递归调用

            i++;  //跳过')'
        }

        return p;
    }

    /**
     * 根据层次遍历序列构建一棵二叉树
     */
    public static <T> BinaryTree<T> createTreeNodeByLevel(T[] value) {
        BinaryNode<T> p = new BinaryNode<>(value[0]);

        BinaryNode<T> q = p;

        Queue<BinaryNode<T>> queue = new LinkedList<>();

        int i = 0;

        while (p != null) {
            if (2 * i + 1 < value.length) {
                if (value[2 * i + 1] != null) {
                    p.left = new BinaryNode<>(value[2 * i + 1]);
                }

                queue.add(p.left);  //p的左子树入队
            }

            if (2 * i + 2 < value.length) {
                if (value[2 * i + 2] != null) {
                    p.right = new BinaryNode<>(value[2 * i + 2]);
                }

                queue.add(p.right);  //p的右子树入队
            }

            p = queue.poll();  //出队,p指向下一层

            i += 1;  //索引+1
        }

        BinaryTree<T> tree = new BinaryTree<>();

        tree.root = q;

        return tree;
    }

    /**
     * 打印树形结构图
     */
    public static <T> void show(BinaryTree<T> tree) {
        if (tree.isEmpty()) {
            System.out.println("EMPTY TREE");
        }

        int h = tree.height();  //获得树的高度

        /*
         * 最后一行的宽度为2的（n - 1）次方乘3，再加1
         * 作为整个二维数组的宽度
         */
        int arrayHeight = h * 2 - 1;
        int arrayWidth = (2 << (h - 2)) * 3 + 1;

        String[][] res = new String[arrayHeight][arrayWidth];  // 用一个字符串数组来存储每个位置应显示的元素

        for (int i = 0; i < arrayHeight; i ++) {  // 对数组进行初始化，默认为一个空格
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(tree.root, 0, arrayWidth/ 2, res, h);  //从根节点开始，递归处理整个树

        for (String[] line: res) {  //此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);

                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }

            System.out.println(sb.toString());
        }
    }

    private static <T> void writeArray(BinaryNode<T> node, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        if (node == null) {  //保证输入的树不为空
            return;
        }

        res[rowIndex][columnIndex] = String.valueOf(node.data.toString());  //先将当前的结点保存到二维数组中去

        int currLevel = (rowIndex + 1) / 2;  //计算当前位于树的第几层

        if (currLevel == treeDepth) {  //若到了最后一层,则返回
            return;
        }

        int gap = treeDepth - currLevel - 1;  //计算当前行到下一行,每个元素之间的间隔(下一行的列索引与当前元素的列索引之间的间隔)

        if (node.left != null) {  // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
            res[rowIndex + 1][columnIndex - gap] = "/";

            writeArray(node.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        if (node.right != null) {  // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
            res[rowIndex + 1][columnIndex + gap] = "\\";

            writeArray(node.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }


}
