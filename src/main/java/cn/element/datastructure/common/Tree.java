package cn.element.datastructure.common;


import cn.element.datastructure.tree.BinaryNode;

/**
 * 声明 "树" 抽象数据类型
 * TreeNode<T> 是树结点类
 * 虽然无序树没有孩子结点约定次序,但是一旦存储,各孩子结点就是有次序的,
 * 因此,获得,插入,删除对孩子结点的操作采用序号 i (o <= i < 孩子结点数)作为识别各孩子结点的标记
 * 若 i 指定结点序号超出范围,则抛出序号越界异常
 */
public interface Tree<T> extends Collection<T> {

    boolean isEmpty();  //判断是否是空树

    int level(T key);  //返回关键字为key结点所在的层次

    int size();  //返回树的结点数

    int height();  //返回树的高度

    void printPreOrder();  //输出树的先根次序遍历序列

    void printPostOrder();  //输出树的后根次序遍历序列

    void printInOrder();  //输出树的中根次序遍历序列

    void printLevelOrder();  //输出树的层次遍历序列

    BinaryNode<T> insert(T x);  //插入元素 x 作为根结点并返回

    BinaryNode<T> insert(BinaryNode<T> parent,T x,boolean leftChild);  //插入 x 作为 p 结点

    void remove(BinaryNode<T> parent,int i);  //删除 p 结点的第 i(i >= 0) 棵子树

    void clear();  //删除树的所有结点

    BinaryNode<T> search(T key);  //先根次序遍历查找首个关键字为 key 的结点

    boolean contains(T key);  //判断是否包含关键字为 key 的元素

    T remove(T key);  //删除以 key 结点为根的子树
}
