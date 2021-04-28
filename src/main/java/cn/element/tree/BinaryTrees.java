package cn.element.tree;

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
    public static BinaryTree<String> createByGenList(String genList){

        BinaryTree<String> binaryTree = new BinaryTree<>();

        i = 0;

        if(genList.length() > 0){
            binaryTree.root = create(genList);  //创建子树,子树根值是 genList[0]
        }

        return binaryTree;
    }

    /**
     * 以从 i 开始的广义表表示字符串创建一棵以 genList[i] 为根的子树,返回根结点,递归算法
     * @param genList           广义表字符串
     * @return                  二叉树
     */
    private static BinaryNode<String> create(String genList){

        BinaryNode<String> p;

        char ch = genList.charAt(i);

        if(ch == '^'){
            i++;

            return null;
        }

        int end = i;

        while(ch != '(' && ch != ',' && ch != ')'){
            end++;  //一个元素占多个字符,以'(' ')'分割

            ch = genList.charAt(end);
        }

        String str = genList.substring(i,end);  //获得从i ~ end - 1 的子串,深拷贝

        i = end;

        p = new BinaryNode<>(str);  //创建叶子结点

        if(genList.charAt(i) == '('){
            i++;  //跳过'('

            p.left = create(genList);  //创建左子树,递归调用

            i++;  //跳过','

            p.right = create(genList);  //创建右子树,递归调用

            i++;  //跳过')'
        }

        return p;
    }


}
