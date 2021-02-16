package com.atguigu.stack;

//用数组来模拟栈
public class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据放在这里面
    private int top=-1;//栈顶,初始为-1

    //构造器

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack= new int[this.maxSize];
    }

    //判断栈空
    public boolean isEmpty(){
        return top== -1;
    }
    //判断栈满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //入栈
    public void push(int value){
        //先判断是否栈满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top ++;
        stack[top] = value;
    }
    //出栈,将栈顶的数据弹出
    public int pop(){
        //先判断是否栈空
        if(isEmpty()){
          throw new RuntimeException("栈空");
        }

        int value = stack[top];
        top--;
        return value;
    }
    //遍历,从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
