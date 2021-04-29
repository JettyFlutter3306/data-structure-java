package cn.element.tree.expression;

/**
 * 声明表达式二叉树的元素类
 *
 */
public class ExpData {

    int value;  //元素值

    char operator;  //单字符运算符

    public ExpData(int value, char operator) {
        this.value = value;
        this.operator = operator;
    }

    @Override
    public String toString() {

        return this.operator == ' ' ? this.value + "" : this.operator + "";
    }
}
