package com.atguigu.stack;

public class Calculator {
    public static void main(String[] args) {
        //定义一个表达式
        String expression = "30+2*6-1";
        //创建两个栈，一个用来存放数据，一个存放运算符
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描到的char保存到ch
        String keepNum = "";//拼接多位数
        //while循环扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch为什么
            //如果ch为运算符
            if (operStack.isOper(ch)) {
                //判断当前的符号栈是否为空
                if (operStack.isEmpty()) {
                    //如果符号栈为空，就直接入栈
                    operStack.push(ch);
                } else {
                    //如果符号栈不为空，就和当前的符号比较优先级，如果小于等于当前符号的优先级
                    //就从数栈中弹出最上面的两个数据，从符号栈中弹出一个符号来计算结果，然后把结果存入数栈,运算符存入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        //如果优先级大于栈中的符号，就直接存入栈中
                        operStack.push(ch);
                    }
                }
            } else {
                //如果是数字,就直接入数字栈
                //numStack.push(ch - 48);
                //处理多位数时，不能直接入栈，需要index再向后移一位，看是否还是数字，
                //如果是数字证明是多位数，直到后一位是符号
                keepNum += ch;
                //如果ch是最后一位直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //看index的后一位
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";//keepNum清空
                    }
                }

            }
            //index后移,到表达式最后直接break
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //当表达式扫描完毕，
        while (true) {
            //如果符号栈为空则证明已经计算完毕
            if (operStack.isEmpty())
                break;
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);

        }
        //将数栈的最后数，pop出就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式%s=%d", expression, res2);
    }
}
