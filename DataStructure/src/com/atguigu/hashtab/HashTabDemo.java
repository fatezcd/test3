package com.atguigu.hashtab;


import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("add 添加雇员");
            System.out.println("list 显示雇员");
            System.out.println("find 查找雇员");
            System.out.println("delete 删除雇员");
            System.out.println("exit 退出系统");

            key = sc.next();
            switch (key) {
                case "add":
                    System.out.println("输入id：");
                    int id = sc.nextInt();
                    System.out.println("输入名字：");
                    String name = sc.next();

                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id：");
                    id = sc.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    System.out.println("请输入要删除的id：");
                    id = sc.nextInt();
                    hashTab.deleteEmpById(id);
                    break;
                case "exit":
                    sc.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

//创建HashTab管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedLists;

    private int size;//表示有多少条链表

    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedLists
        empLinkedLists = new EmpLinkedList[size];
        // 初始化每条emplinkedlist
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        //根据员工的id决定添加到哪一条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp田间到对应的链表
        empLinkedLists[empLinkedListNo].add(emp);
    }

    //遍历雇员
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    //根据id查找雇员
    public void findEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedLists[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到 雇员id=%d name=%s\n", (empLinkedListNo + 1), id, emp.name);
        } else {
            System.out.println("在表中没有找到该雇员");
        }
    }

    //根据编号删除雇员
    public void deleteEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        empLinkedLists[empLinkedListNo].deleteById(id);
    }

    //散列函数
    public int hashFun(int id) {
        return id % size;
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList {
    //这个链表的head是直接指向第一个Emp
    private Emp head;


    //添加雇员到链表
    //添加雇员的时候，id是自增长，即id的分配总是从小到大
    //因此直接将雇员加入到链表的最后

    public void add(Emp emp) {
        //如果添加的链表为空
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，使用一个辅助指针，遍历到链表最后
        Emp curEmp = head;
        while (curEmp.next != null) {
            //后移
            curEmp = curEmp.next;
        }
        //退出后，直接将emp加入到链表最后
        curEmp.next = emp;
    }

    //遍历
    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "链表的信息为：");
        Emp curEmp = head;
        while (curEmp != null) {
            System.out.printf(" =>id=%d name=%s\t", curEmp.id, curEmp.name);

            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //查找
    public Emp findEmpById(int id) {

        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break;
            }
            //退出
            if (curEmp.next == null) {//该链表中没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//后移
        }
        return curEmp;
    }

    //根据id删除员工
    public void deleteById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空,该员工不存在");
            return;
        }

        Emp curEmp = head;
        while (true) {
            if (head.id == id) {
                head = head.next;
                return;
            }
            if (curEmp.next.id == id) {
                curEmp.next = curEmp.next.next;
                break;
            }
            if (curEmp.next == null) {
                System.out.println("该员工不存在");
                return;
            }
            curEmp = curEmp.next;//后移
        }

    }
}



