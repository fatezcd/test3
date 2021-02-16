package com.atguigu.stack;

//用数组来模拟栈
public class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据放在这里面
    private int top = -1;//栈顶,初始为-1

    //构造器

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //入栈
    public void push(int value) {
        //先判断是否栈满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈,将栈顶的数据弹出
    public int pop() {
        //先判断是否栈空
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }

        int value = stack[top];
        top--;
        return value;
    }
    //返回当前栈顶的值
    public int peek(){
        return stack[top];
    }

    //遍历,从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //判断运算级
    //返回数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }
        if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前表达式只有+，-，*，/
        }
    }

    //判断是否是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '/' || val == '-' || val == '*';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//定义计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;

    }

}
