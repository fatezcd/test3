package com.atguigu.stack;

//使用单向链表来模拟栈
public class SingleLinkedListStack {
    private int maxSize;//栈的大小
    private SingleLinkedList stack;//单链表模拟栈
    private int top = -1;//栈顶

    //构造器

    public SingleLinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new SingleLinkedList();
        stack.createLinkedList(this.maxSize);
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int data){
        if(isFull()){
            System.out.println("栈满，无法入栈");
            return;
        }
        top++;
        stack.add(top,data);
    }

    //出栈
    public void pop(){
        if(isEmpty()){
            System.out.println("栈空，无法出栈");
            return;
        }
        System.out.println("stack["+top+"]="+stack.getHead(top));
        top--;
    }
    //遍历
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，请添加数据");
            return;
        }
        stack.list(top);
    }
}
