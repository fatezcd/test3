package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //定义逆波兰表达式
        //String suffixExpression = "30 4 + 5 * 6 -";//(30+4)*5-6=164
        //String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";//76
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list=" + infixExpressionList);
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的list=" + suffixExpreesionList);
        int res = calculate(suffixExpreesionList);
        System.out.println("计算的结果是" + res);

    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> list = new ArrayList<>();
        int i = 0;//用于遍历中缀表达式字符串
        String str;//用于拼接多位数
        char c;//没遍历到一个字符，就放入ls
        do {
            //如果c不是数字，就直接加入ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);//
                i++;
            } else {
                //如果是一个数，要考虑多位数的情况
                str = "";//先将str置为空
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                list.add(str);
            }

        } while (i < s.length());
        return list;
    }

    //将中缀表达式对应的list转变为后缀表达式对应的list
    public static List<String> parseSuffixExpreesionList(List<String> list) {
        //新建一个栈为符号栈
        Stack<String> s1 = new Stack<>();
        //新建一个list代替栈存放数
        List<String> s2 = new ArrayList<>();

        //遍历中缀表达的list
        for (String item : list) {
            if (item.matches("\\d+")) {//如果是数直接加入s2
                s2.add(item);
            } else if (item.equals("(")) {
                //如果是左括号直接加入s1
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    //如果是右括号，则依次弹出s1栈顶的运算符，并加入s2，直到遇到左括号，此时将这一对括号丢弃
                    s2.add(s1.pop());
                }
                s1.pop();//丢弃左括号
            } else {
                //如果优先级比栈顶的运算符低，将s1栈顶的运算符弹出加入s2，再次循环
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }

                s1.push(item);
            }

        }
        //将s1剩余的运算符依次弹出加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }


    //将逆波兰表达式的元素存入Arraylist集合
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression按空格分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();

        //将元素添加到list中
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    public static int calculate(List<String> list) {
        //新建一个栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的是多位数
                //入栈
                stack.push(item);

            } else {
                //pop出两个数，并计算，然后将结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }

                //把res入栈
                stack.push("" + res);
            }

        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }


}

//编写一个类Operation返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    // 判断运算符的优先级
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}

