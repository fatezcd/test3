package com.atguigu.sparsearray;


import java.io.*;

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //1.定义一个11*11的棋盘,1代表黑色棋子，2代表白色棋子，0代表没有棋子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[1][3] = 2;
        //2.输出原始二维数组
        System.out.println("原始的二维数组：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("--------------");
        //3.遍历二维数组，统计数组中非零数据的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0)
                    sum ++;
            }
        }
        System.out.println("sum=" + sum);
        System.out.println("--------------");
        //4.根据sum创建稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //5.将二维数组的非零数据存入稀疏数组
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //6.输出转化后的稀疏数组
        System.out.println("转化后的稀疏数组：");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组保存到磁盘上
        File f1 = new File("D:/project/DataStructure/map.data");
        FileWriter fw = new FileWriter(f1);
        for (int[] row : sparseArr) {
            for (int data : row) {
                fw.write(data + "\t");
            }
            fw.write("\r\n");
        }
        fw.close();

        //读取磁盘上的二维数组
        System.out.println("-------------------");
        System.out.println("从磁盘中读取的二维数组：");
        String file = "D:/project/DataStructure/map.data";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str = null;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
        br.close();

        //7.将稀疏数组转化为普通二维数组
        System.out.println("-------------");
        //先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //再读取稀疏数组后几行的数据，并赋值给原始的二维数组
        for (int i = 1; i < sparseArr.length; i++) {
            int row = sparseArr[i][0];
            int colum = sparseArr[i][1];
            chessArr2[row][colum] = sparseArr[i][2];
        }
        //8.输出转化后的二维数组
        System.out.println("转化后的二维数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
