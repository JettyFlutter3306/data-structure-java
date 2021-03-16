package testStack;

import org.junit.Test;
import cn.element.stack.Bracket;

public class TestBracket {

    @Test
    public void test01(){

        String infix = "((1+2)*3+4))(";

        System.out.println(infix + ",编译错误: "+ Bracket.isMatched(infix));
    }
}
