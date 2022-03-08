package cn.element.datastructure.util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树工具类
 */
public class TreeNodes {

    private static int i = 0;

    /**
     * 用先根遍历序列构建二叉树结点
     */
    public static TreeNode createTreeNode(Integer[] value) {
        TreeNode p = null;

        if (i < value.length) {
            Integer element = value[i];  //拿到先根遍历序列的对应元素

            i++;  //i自增1

            if (element != null) {  //不能 element != "^",因为 T 不一定是 String
                p = new TreeNode(element);  //创建叶子结点

                p.left = createTreeNode(value);  //创建 p 的左子树,递归调用
                p.right = createTreeNode(value);  //创建 p 的右子树,递归调用
            }
        }

        return p;
    }

    /**
     * 根据层次遍历序列构建一棵二叉树
     */
    public static TreeNode createTreeNodeByLevel(Integer[] value) {
        TreeNode p = new TreeNode(value[0]);
        TreeNode q = p;

        Queue<TreeNode> queue = new LinkedList<>();

        int i = 0;

        while (p != null) {
            if (2 * i + 1 < value.length) {
                if (value[2 * i + 1] != null) {
                    p.left = new TreeNode(value[2 * i + 1]);

                    queue.add(p.left);
                }
            }

            if (2 * i + 2 < value.length) {
                if (value[2 * i + 2] != null) {
                    p.right = new TreeNode(value[2 * i + 2]);

                    queue.add(p.right);
                }
            }

            p = queue.poll();

            i += 1;
        }

        return q;
    }

    /**
     * 打印树形结构图
     */
    public static void show(TreeNode node) {
        if (node == null) {
            System.out.println("EMPTY TREE");
        }

        int h = node != null ? node.height() : 0;  //获得树的高度

        /*
         * 最后一行的宽度为2的（n - 1）次方乘3，再加1
         * 作为整个二维数组的宽度
         */
        int arrayHeight = h * 2 - 1;
        int arrayWidth = (2 << (h - 2)) * 3 + 1;

        String[][] res = new String[arrayHeight][arrayWidth];  // 用一个字符串数组来存储每个位置应显示的元素

        for (int i = 0; i < arrayHeight; i++) {  // 对数组进行初始化，默认为一个空格
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(node, 0, arrayWidth / 2, res, h);  //从根节点开始，递归处理整个树

        for (String[] line : res) {  //此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
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

    private static void writeArray(TreeNode node, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        if (node == null) {  //保证输入的树不为空
            return;
        }

        res[rowIndex][columnIndex] = String.valueOf(node.val);  //先将当前的结点保存到二维数组中去

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
