package test_tree;

import cn.element.tree.huffman.HuffmanTree;
import org.junit.Test;

public class TestHuffmanTree {

    /**
     * 测试构建HuffmanTree
     */
    @Test
    public void test01(){

        String text = "AAAABBBCDDBBAAA";  //数据

        int[] weight = new int[]{7,5,1,2};  //指定权值集合

        HuffmanTree huffmanTree = new HuffmanTree(weight);  //构建HuffmanTree

        System.out.println(huffmanTree);

        String compressed = huffmanTree.encode(text);

        System.out.println("压缩后: \n" + compressed + "," + compressed.length() + "位");
        System.out.println("解码为: \n" + compressed + "->" + huffmanTree.decode(compressed));
    }
}
