package cn.element.tree.huffman;

/**
 * 二叉树的静态三叉链表结点类
 * 用于构建Huffman树
 */
public class TriElement {

    int data;  //数据域

    int parent;  //父母结点

    int left;  //左孩子结点下标

    int right;  //右孩子结点下标

    /**
     * 构造结点,各参数依次指定元素,父母结点下标,左右孩子结点下标
     * @param data          数据
     * @param parent        父母结点下标
     * @param left          左孩子结点下标
     * @param right         右孩子结点下标
     */
    public TriElement(int data, int parent, int left, int right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    /**
     * 构造元素值为data,无父母的叶子结点
     * @param data          数据
     */
    public TriElement(int data) {

        this(data,-1,-1,-1);
    }

    /**
     * 判断是否是叶子结点
     */
    public boolean isLeaf(){

        return this.left == -1 && this.right == -1;
    }

    @Override
    public String toString() {

        return "("+this.data+","+this.parent+","+this.left+","+this.right+")";
    }


}
