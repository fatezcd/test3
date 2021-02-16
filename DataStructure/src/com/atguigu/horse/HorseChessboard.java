package com.atguigu.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {
    private static int X;//棋盘的行
    private static int Y;//棋盘的列
    //创建数组，标记棋盘的各个位置是否被访问过
    private static boolean[] visited;
    //标记棋盘的所有位置是否都已经走完
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法开始---");
        X = 8;
        Y = 8;
        int row = 1; //马的初始行位置，从1开始编号
        int column = 1;//马的初始列位置，从1开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];
        //测试耗时
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "ms");
        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }


    }

    /**
     * 完成骑士周游问题的算法
     *
     * @param chessboard 棋盘
     * @param row        马当前的位置的行，从0开始
     * @param column     马当前的位置的列 从0开始
     * @param step       第几步，初始为1
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        //初始位置设为以访问
        visited[row * X + column] = true;
        //获取当前位置下一步可以走的位置的集合
        ArrayList<Point> ps = next(new Point(row, column));
        //对 ps 进行排序,排序的规则就是对 ps 的所有的 Point 对象的下一步的位置的数目，进行非递减排序
        // sort(ps);
        //遍历ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);//取出下一步可以走的第一个位置
            if (!visited[p.x * X + p.y]) {//这个点还没有被访问
                traversalChessboard(chessboard, p.x, p.y, step + 1);

            }
        }
        //判断是否完成任务,使用step和应该走的步数比较
        //如果没有达到数量，表示没有完成任务，将整个棋盘置0
        //step<x*y成立的两种情况，1，棋盘到目前的位置仍然没有走完 2.棋盘处于一个回溯的过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }

    }

    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList存放棋子下一步能走的位置
        ArrayList<Point> ps = new ArrayList<>();
        //创建一个Point
        Point p1 = new Point();
        //马下一步走5这个位置
        if ((p1.y = curPoint.y - 2) >= 0 && (p1.x = curPoint.x - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //马下一步走6这个位置
        if ((p1.y = curPoint.y - 1) >= 0 && (p1.x = curPoint.x - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //马下一步走7这个位置
        if ((p1.y = curPoint.y + 1) < Y && (p1.x = curPoint.x - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //马下一步走0这个位置
        if ((p1.y = curPoint.y + 2) < Y && (p1.x = curPoint.x - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //马下一步走1这个位置
        if ((p1.y = curPoint.y + 2) < Y && (p1.x = curPoint.x + 1) < X) {
            ps.add(new Point(p1));
        }
        //马下一步走2这个位置
        if ((p1.y = curPoint.y + 1) < Y && (p1.x = curPoint.x + 2) < X) {
            ps.add(new Point(p1));
        }
        //马下一步走3这个位置
        if ((p1.y = curPoint.y - 1) >= 0 && (p1.x = curPoint.x + 2) < X) {
            ps.add(new Point(p1));
        }
        //马下一步走4这个位置
        if ((p1.y = curPoint.y - 2) >= 0 && (p1.x = curPoint.x + 1) < X) {
            ps.add(new Point(p1));
        }

        return ps;

    }

    //根据当前这一步的所有下一步选择的位置，进行非递减排序，减少回溯
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取o1下一步可走的所有位置的个数
                int count1 = next(o1).size();
                //获取o2下一步可走的所有位置的个数
                int count2 = next(o2).size();
                if(count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
