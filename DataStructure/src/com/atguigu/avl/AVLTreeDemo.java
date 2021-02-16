package com.atguigu.avl;


public class AVLTreeDemo {
    public static void main(String[] args) {
        //int arr[] = {4, 3, 6, 5, 7, 8};
        //int arr[] = {10, 12, 8, 9, 7, 6};
        int arr[] = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历:");
        avlTree.infixOrder();
        // System.out.println("没有平衡处理前");
        System.out.println("平衡处理后:");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("左子树的高度=" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度=" + avlTree.getRoot().rightHeight());

    }
}

//创建AVL树
class AVLTree {
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

//创建Node节点
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

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();

    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();

    }

    //返回以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转
    public void leftRotate() {
        //创建一个新的节点
        Node newNode = new Node(value);
        //把新节点的左子树设置为当前节点的左子树
        newNode.left = left;
        //把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值换为右子节点的值
        value = right.value;
        //把当前节点的右子树设置为右子树的右子树
        right = right.right;
        //把当前节点的左子树设置为新节点
        left = newNode;
    }

    //右旋
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
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
        //当添加完一个节点后,右子树的高度与左子树高度的差大于1
        if (rightHeight() - leftHeight() > 1) {
            //它的右子树的左子树高度大于它的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对当前节点的右子树进行右旋
                right.rightRotate();
                //再对当前节点进行左旋
                leftRotate();
            } else {
                leftRotate();//直接左旋
            }
            return;//必须加
        }
        //当添加完一个节点后,左子树的高度与右子树高度的差大于1
        if (leftHeight() - rightHeight() > 1) {
            //它的左子树的右子树高度大于它的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左子树进行左旋
                left.leftRotate();
                //再对当前节点进行右旋
                rightRotate();
            } else {
                rightRotate();//直接右旋
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