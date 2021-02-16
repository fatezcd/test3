package com.atguigu.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {

        //测试压缩文件
        /*String srcFile = "e://123.txt";
        String dstFile = "e://123.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩成功");*/


        //测试解压文件
        String zipFile = "e://123.zip";
        String dstFile = "e://1231.txt";
        unZipFile(zipFile, dstFile);
        System.out.println("解压成功");

/*
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("原始数组长度:" + contentBytes.length);
        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes" + nodes);
        //测试霍夫曼树
        System.out.println("霍夫曼树:");
        Node huffmanTreeroot = createHuffmanTree(nodes);
        //System.out.println("前序遍历：");
        huffmanTreeroot.preOrder();
        //测试霍夫曼编码
        //getCodes(huffmanTreeroot, "", stringBuilder);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeroot);
        System.out.println("生成的霍夫曼编码:" + huffmanCodes);

       // byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
        System.out.println("压缩后数组的长度:" + huffmanCodeBytes.length);

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符为:" + new String(sourceBytes));*/
    }

    /**
     * @param bytes 接受字节数组
     * @return 返回list形式
     */
    private static List<Node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();
        Map<Byte, Integer> counts = new HashMap<>();
        //遍历bytes，统计每一个byte出现的次数
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }

        }
        //把每一个键值对转成一个Node对象，加入到nodes集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    public static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);

            //取出根节点权值最小的两颗二叉树
            //取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            //取出权值第二小的节点（二叉树）
            Node rightNode = nodes.get(1);

            //新建一二叉树,他的根节点没有data，只有权重
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //从集合中删掉处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes
            nodes.add(parent);
        }
        //返回霍夫曼树的root节点
        return nodes.get(0);
    }

    //生成霍夫曼树对应的霍夫曼编码
    //1.将霍夫曼编码表存放在Map<Byte,String>形式，生成的霍夫曼编码表{32=01，97=100，，，}
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2.在生成霍夫曼编码表时，需要去拼接路径，定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了方便，重载
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的node节点的所有叶子节点的霍夫曼编码得到，并放入到huffmanCodes中
     *
     * @param node          传入节点
     * @param code          路径：左边节点为0，右边节点为1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {//如果node==null不处理
            //判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {
                //递归
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                //说明是一个叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }

    //编写一个方法，将字符串对应的byte[]数组，通过生成的霍夫曼编码表，返回一个霍夫曼编码压缩后的byte[]

    /**
     * @param bytes        原始字符串对应的byte[]
     * @param huffmanCodes 生成的霍夫曼编码map
     * @return 返回霍夫曼编码处理后的byte[]
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //利用huffmancodes 将bytes 转换成霍夫曼编码对应的字符串
        StringBuffer stringBuffer = new StringBuffer();
        //遍历bytes
        for (byte b : bytes) {
            stringBuffer.append(huffmanCodes.get(b));
        }
        //System.out.println("测试 stringBuffer="+stringBuffer.toString());
        //将stringbuffer串转换为byte[]
        //统计返回byte[] huffmanCodeBytes长度
        int len;
        if (stringBuffer.length() % 8 == 0) {
            len = stringBuffer.length() / 8;
        } else {
            len = stringBuffer.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuffer.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuffer.length()) {
                //不够8位
                strByte = stringBuffer.substring(i);
            } else {
                strByte = stringBuffer.substring(i, i + 8);
            }
            //将strByte转成一个byte，放入huffmancodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //使用一个方法，将前面的方法封装起来

    /**
     * @param bytes 原始字符串对应的字节数组
     * @return 霍夫曼编码后的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建霍夫曼树
        Node huffmanTreeroot = createHuffmanTree(nodes);
        //生成霍夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeroot);
        //根据生成的霍夫曼编码，得到压缩后的霍夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;

    }

    //完成数据的解压
    //将huffmanCodeBytes重写成霍夫曼编码对应的二进制字符串
    //霍夫曼编码对应的二进制字符串对照霍夫曼编码

    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;//将b转换为int
        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        //把字符串按照指定的霍夫曼编码进行编码
        //把霍夫曼编码表进行调换，因为反向查询
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解为索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {//没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到count
        }
        //当for循环结束后，list中存放了所以的字符
        //把list中的数据放到byte[]中
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //文件压缩
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件输入流
        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //对文件进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectputStream
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());

            }

        }
    }

    //文件解压
    public static void unZipFile(String zipFile, String dstFile) {
        //创建文件的输入流
        InputStream is = null;
        //创建对象输入流
        ObjectInputStream ois = null;
        //创建文件输出流
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            //读取byte数组huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取霍夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes写入目标文件
            os = new FileOutputStream(dstFile);
            //写数据到dstFile文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }
}

//创建Node
class Node implements Comparable<Node> {

    Byte data;//存放数据本身
    int weight;//权重，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
