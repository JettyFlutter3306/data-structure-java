package test_stack;

import org.junit.Test;
import org.codeart.datastructure.stack.Bracket;

public class TestBracket {

    @Test
    public void test01() {
        String infix = "((1+2)*3+4))(";

        System.out.println(infix + ",编译错误: "+ Bracket.isMatched(infix));
    }
}
