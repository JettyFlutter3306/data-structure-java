package cn.element.datastructure.tree.huffman;

/**
 * 声明HuffmanTree类如下,构造Huffman树,获得各字符的Huffman编码
 */
public class HuffmanTree {

    private String charset;  //字符集合

    private final TriElement[] hufTree;  //静态三叉链表结点数组

    /**
     * 构造Huffman树,weights指定权值集合,数组长度为叶子结点数,默认字符集合从A开始
     *
     * @param weights 权值集合
     */
    public HuffmanTree(int[] weights) {
        this.charset = "";

        for (int i = 0; i < weights.length; i++) {  //默认字符集合是从'A'开始的weights.length个字符
            this.charset += (char) ('A' + i);
        }

        int n = weights.length;  //叶子结点数

        this.hufTree = new TriElement[2 * n - 1];  //n个叶子的Huffman树有 2n-1 个结点

        for (int i = 0; i < n; i++) {
            this.hufTree[i] = new TriElement(weights[i]);  //构造无父母的叶子结点
        }

        for (int i = n; i < 2 * n - 1; i++) {  //构造 n-1 个 2 度结点
            int min1 = Integer.MAX_VALUE;  //最小权值,初始值为最大整数
            int min2 = min1;                //次小权值

            int x1 = -1, x2 = -1;  //最小和次小权值结点下标

            for (int j = 0; j < i; j++) {               //寻找两个无父母的最小权值结点下标
                if (this.hufTree[j].parent == -1) {       //第 j 个结点无父母
                    if (this.hufTree[j].data < min1) {    //第 j 个结点权值最小
                        min2 = min1;                    //min2记得次小权值
                        x2 = x1;                        //x2记得次小权值结点下标

                        min1 = this.hufTree[j].data;        //min1记得最小权值
                        x1 = j;                         //x1记得最小权值结点下标
                    } else if (this.hufTree[j].data < min2) {      //第 j 个结点权值次小
                        min2 = hufTree[j].data;

                        x2 = j;
                    }
                }
            }

            this.hufTree[x1].parent = i;                //合并两棵权值最小的子树,左孩子最小
            this.hufTree[x2].parent = i;
            this.hufTree[i] = new TriElement(min1 + min2, -1, x1, x2);  //构造结点
        }
    }

    /**
     * 返回charset第 i 个字符的Huffman编码字符串
     *
     * @param i 序列
     * @return String
     */
    private String getCode(int i) {
        int n = 8;

        char[] hufCode = new char[n];       //声明字符数组暂存 Huffman 编码

        int child = i;
        int parent = this.hufTree[child].parent;

        for (i = n - 1; parent != -1; i--) {  //由叶结点向上直到根结点,反序列储存编码
            hufCode[i] = (hufTree[parent].left == child) ? '0' : '1';  //左,右孩子编码0,1

            child = parent;

            parent = hufTree[child].parent;
        }

        return new String(hufCode, i + 1, n - 1 - i);  //由hufCode数组从i+1开始的n-i-1个字符构造串
    }

    /**
     * 数据压缩,将text个字符转换成Huffman编码存储,返回压缩字符串
     *
     * @param text 文本
     * @return 压缩字符串
     */
    public String encode(String text) {
        StringBuilder compressed = new StringBuilder();  //被压缩的数据,以字符串显示

        for (int i = 0; i < text.length(); i++) {
            compressed.append(getCode(text.charAt(i) - 'A'));  //默认字符集是从A开始的 n 个字符
        }

        return compressed.toString();
    }

    /**
     * 数据解压缩,将压缩compressed中的0/1序列进行Huffman编码,返回译码字符串
     *
     * @param compressed 压缩字符串
     * @return Huffman编码
     */
    public String decode(String compressed) {
        StringBuilder text = new StringBuilder();

        int node = this.hufTree.length - 1;  //node搜索一条从根到达叶子的路径

        for (int i = 0; i < compressed.length(); i++) {  //根据0,1分别向左或右孩子走
            if (compressed.charAt(i) == '0') {
                node = hufTree[node].left;
            } else {
                node = hufTree[node].right;
            }

            if (hufTree[node].isLeaf()) {  //到达叶子结点
                text.append(this.charset.charAt(node));  //获得一个字符

                node = this.hufTree.length - 1;  //node再从根结点开始
            }
        }

        return text.toString();
    }

    /**
     * 返回Huffman树的结点数组和所有字符的编码串
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Huffman树的结点数组: \n");

        for (TriElement triElement : this.hufTree) {
            str.append(triElement.toString()).append(",");
        }

        str.append("\nHuffman编码: \n");

        for (int i = 0; i < this.charset.length(); i++) {  //输出所有叶子结点的Huffman编码
            str.append(this.charset.charAt(i)).append(": ").append(getCode(i)).append(", ");
        }

        return str.toString();
    }


}
