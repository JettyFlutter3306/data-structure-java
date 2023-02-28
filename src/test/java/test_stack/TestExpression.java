package test_stack;

import org.junit.Test;
import org.codeart.datastructure.stack.Expression;

public class TestExpression {

    /**
     * 测试中缀表达式转后缀表达式,并计算值表达式值
     */
    @Test
    public void test01() {
        String infix = "123+10*(45-50+20)/((35-25)*2+10)-11"; //中缀表达式

        StringBuffer postfix = Expression.toPostfix(infix);

        System.out.println("postfix = " + postfix);

        System.out.println("value = " + Expression.toValue(postfix));

    }
}
