package com.atguigu.binarysorttree;


public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int arr[] = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历二叉排序树:");
        binarySortTree.infixOrder();

        //测试删除节点

        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        binarySortTree.delNode(9);
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(12);

        System.out.println("root="+binarySortTree.getRoot());
        System.out.println("删除节点后:");
        binarySortTree.infixOrder();
    }
}

//二叉排序树
class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;//如果根节点为空，直接让root指向node
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 返回以node为根节点的二叉排序树的最小节点的值
     * 删除node为根节点的二叉排序树的最小节点
     *
     * @param node 传入节点当作二叉排序树的根节点
     * @return
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环查找左子节点找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //target指向最小值
        //删除最小节点
        delNode(target.value);
        return target.value;

    }    //删除节点

    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //先找到要删除的节点
            Node targetNode = search(value);
            //没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //当前二叉树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到targetNode的父节点
            Node parentNode = searchParent(value);
            //如果要删除的节点为叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                if (parentNode.left != null && parentNode.left.value == value) {
                    parentNode.left = null;
                } else if (parentNode.right != null & parentNode.right.value == value) {
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {
                //删除只有一颗子树的节点
                //如果要删除的节点有左子节点

                if (targetNode.left != null) {
                    if (parentNode != null) {
                        //如果targetNode是parent的左子节点
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.left;
                        } else {
                            //如果targetNode是parent的右子节点
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//如果要删除的节点有右子节点
                    //如果targetNode是parent的左子节点
                    if (parentNode != null) {
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.right;
                        } else {
                            //如果targetNode是parent的右子节点
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

//节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点的方法
    //递归的形式添加节点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入节点值与当前节点值的大小
        if (node.value < this.value) {
            //如果当前节点的左子节为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    /**
     * 查找要删除的节点
     *
     * @param value 要删除节点的值
     * @return 返回要删除的节点
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;//当前节点就是要删除的节点
        } else if (value < this.value) {
            //如果查找的节点值小于当前节点，向左子树递归查找
            //先判断左子节点不为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }

    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要找的节点的值
     * @return 返回要删除节点的父节点
     */
    public Node searchParent(int value) {
        //如果当前节点就是要删除节点的父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;//没有找到父节点
            }
        }
    }
}
