package com.atguigu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "卢俊义");
        HeroNode node3 = new HeroNode(3, "吴用");
        HeroNode node4 = new HeroNode(4, "公孙胜");
        HeroNode node5 = new HeroNode(5, "关胜");
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        //测试
        /*System.out.println("前序遍历：");
        binaryTree.preOrder();
        System.out.println("中序遍历：");
        binaryTree.infixOrder();
        System.out.println("后序遍历：");
        binaryTree.postOrder();*/

        //测试
        System.out.println("后序遍历查找...");
        HeroNode resNode = binaryTree.postOrderSearch(5);
        if (resNode == null) {
            System.out.printf("没有找到no=%d的英雄", 5);
        } else {
            System.out.printf("找到了no=%d name=%s", resNode.getNo(), resNode.getName());
        }

        /*System.out.println("前序遍历：");
        binaryTree.preOrder();
        binaryTree.delNode(3);
        System.out.println("删除后");
        binaryTree.preOrder();*/


    }
}

//定义二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            //判断root节点是否是要删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);

            }
        } else {
            System.out.println("空树，无法删除");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {

        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);//输出父节点
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {

        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.postOrder();
        }

        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进行前序遍历");
        //先判断当前节点是不是
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        //判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//说明在左子树找到
            return resNode;
        }
        //左子树没有找到
        //判断当前节点的右子节点是否为空，如果不为空，则递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进行中序遍历");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;

    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进行后序遍历");
        if (this.no == no) {
            return this;
        }
        return resNode;

    }

    //递归删除节点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树

    public void delNode(int no) {
        //1.因为我们的二叉树是单向的，所以我们判断当前节点的子节点是否是要删除的节点

        //2.如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left=null,并且return结束递归
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //3.如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right=null,并且return结束递归
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //4.我们需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }

        //5.向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }


    }
}
