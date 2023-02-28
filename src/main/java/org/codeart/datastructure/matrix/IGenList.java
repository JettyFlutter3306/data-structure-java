package org.codeart.datastructure.matrix;

import org.codeart.datastructure.matrix.node.GenNode;

/**
 * 广义表(Generalized List)接口
 * 广义表是一种复杂的数据结构,它是线性表的扩展,能够表示树结构和图结构
 * 广义表在文本处理,人工智能,计算机图形学领域中有着广泛的应用
 *
 * 1.广义表中没有元素时,是空广义表
 * 2.广义表中元素全是原子项时,是线性表
 * 3.广义表中的元素中有子表时,但是没有共享和递归的成分,是树形结构的 "纯表"
 * 4.广义表中的元素有子表时,而且有共享成分,是图结构的再入表
 * 5.广义表中的元素有子表且有递归成分时,是图结构的递归表
 */
public interface IGenList<T> {

    boolean isEmpty();      //判空

    int size();  //返回广义表的长度

    int depth();  //返回广义表的深度

    GenNode<T> insert(int i, T x);  //插入原子 x 作为第 i 个元素

    GenNode<T> insert(int i,GenList<T> genList);  //插入子表作为第 i 个元素

    void remove(int i);  //删除第 i 个元素
}
