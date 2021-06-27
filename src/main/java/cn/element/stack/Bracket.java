package cn.element.stack;

import cn.element.common.Stack;

public class Bracket {

    //检查infix表达式中的圆括是否匹配,若匹配,返回空串,否则返回错误信息
    public static String isMatched(String infix){

        //声明接口对象stack,引用实现MyStack<T>接口的顺序栈类的实例,创建空栈
        Stack<String> stack = new SeqStack<>(infix.length());

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            switch (ch){
                case '(': stack.push(ch + "");break; //左括号入栈
                case ')': if(stack.isEmpty() || !stack.pop().equals("(")) //遇见右括号,出栈
                    return "期望(";                       //检查出栈字符是否为左括号

            }
        }

        return (stack.isEmpty() ? "" : "期望)");  //返回空串表示没有错误
    }

}
