package cn.element.datastructure.stack;

import cn.element.datastructure.common.Stack;

public class Expression {

    //返回将infix中缀表达式准换成的后缀表达式
    public static StringBuffer toPostfix(String infix) {
        Stack<String> stack = new SeqStack<>(infix.length());//运算符栈,顺序栈

        StringBuffer postfix = new StringBuffer(infix.length() * 2);//后缀表达式字符串

        int i = 0;

        while (i < infix.length()) {
            char ch = infix.charAt(i);

            switch (ch) {
                case '+':
                case '-':
                    while (!stack.isEmpty() && !stack.peek().equals("(")) { //与栈顶运算符比较
                        postfix.append(stack.pop());   //出栈运算符添加到后缀表达式串
                    }

                    stack.push(ch + "");  //当前运算符入栈

                    i++; break;
                case '*':
                case '/':
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                        postfix.append(stack.pop()); //栈顶优先级高的运算符出栈
                    }

                    stack.push(ch + "");

                    i++; break;
                case '(':
                    stack.push(ch + "");

                    i++; break;
                case ')':
                    String out = stack.pop(); //遇到右括号,出栈,若栈空则返回null

                    while (out != null && !out.equals("(")) {  //直到出栈运算符为左括号
                        postfix.append(out);

                        out = stack.pop();
                    }

                    i++; break;
                default:  //遇到数字,添加到后缀表达式
                    while (i < infix.length() && ch >= '0' && ch <= '9') {
                        postfix.append(ch);

                        i++;

                        if (i < infix.length()) {
                            ch = infix.charAt(i);
                        }
                    }

                    postfix.append(" "); //添加空格作为数值之间的分隔符
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix;
    }

    //计算后缀表达式的值
    public static int toValue(StringBuffer postfix) {
        Stack<Integer> stack = new LinkedStack<>(); //操作数栈,链式栈

        int value = 0;

        //逐个检查后缀表达式中的字符
        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);

            if (ch >= '0' && ch <= '9') { //遇到数字字符
                value = 0;

                while (ch != ' ') {
                    value = value * 10 + ch - '0'; //将整数字符串转换为整数值

                    ch = postfix.charAt(++i);
                }

                stack.push(value);  //new Integer(value)整数对象入栈
            } else {
                if (ch != ' ') {
                    //出栈两个操作数,注意出栈次序 java会自动调用intValue()方法将Integer对象转换成int整数
                    int y = stack.pop();
                    int x = stack.pop();

                    switch (ch) {
                        case '+': value = x + y; break;
                        case '-': value = x - y; break;
                        case '*': value = x * y; break;
                        case '/': value = x / y; break; //整除,若除数为0,则抛出算术异常
                    }

                    System.out.print(x+(ch+"")+y+"="+value+", "); //显示运算过程

                    stack.push(value);  //运算结果入栈
                }
            }
        }

        return stack.pop();
    }

}
