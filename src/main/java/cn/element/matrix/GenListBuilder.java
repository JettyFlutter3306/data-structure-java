package cn.element.matrix;

import cn.element.matrix.node.GenNode;

/**
 * 广义表创建类
 * 用于生成广义表
 */
public class GenListBuilder {

    private static int i;

    /**
     * 返回以gStr字符串创建的广义表
     */
    public static GenList<String> createByString(String gStr) {
        i = 0;

        return createSub(gStr);
    }

    /**
     * 返回从gStr[i]开始的子串创建的子广义表,用字符串表示原子,递归算法
     * @param gStr          字符串
     * @return              GenList<String>
     */
    private static GenList<String> createSub(String gStr) {
        i++;                //跳过 '('

        GenList<String> genList = new GenList<>();  //构造空广义表,只有头结点

        GenNode<String> p = genList.head;  //指向头结点

        while (i < gStr.length()) {
            char ch = gStr.charAt(i);

            switch (ch){
                case ',': i++;break;
                case '(': {
                    p.next = new GenNode<>();  //创建子结点

                    p = p.next;

                    p.child = createSub(gStr);  //创建子表,递归调用

                    break;
                }
                case ')': i++;return genList;
                default: {  //用字符串表示原子
                    int j = i + 1;

                    ch = gStr.charAt(j);

                    while(ch != '(' && ch != ',' && ch != ')'){  //获得子串
                        ch = gStr.charAt(++j);
                    }

                    p.next = new GenNode<>(gStr.substring(i,j));  //创建结点

                    p = p.next;

                    i = j;
                }
            }
        }

        return null;
    }
}
