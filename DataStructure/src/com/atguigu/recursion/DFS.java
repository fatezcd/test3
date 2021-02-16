package com.atguigu.recursion;


public class DFS {

    //迷宫出口的位置
    static int fx = 6, fy = 5;
    //迷宫
    static int[][] map = new int[8][7];
    //上下左右移动
    static int[][] temp = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    //标记数组，走过就标记为1
    static int[][] book = new int[8][7];
    //最短步数
    static int min = Integer.MAX_VALUE;
    //总共多少条路经
    static int count = 0;

    public static void main(String[] args) {

        //使用1表示有墙
        //先将上下设为墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右设为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置障碍
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("地图的情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        book[1][1] = 1;
        dfs(1, 1, 0);
        System.out.printf("共有%d条路经\n", count);
        System.out.printf("最短为%d步", min);
        /*for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(book[i][j]==1){
                    System.out.println("<"+i+","+j+">->");
                }
            }
        }*/
    }

    public static void dfs(int x, int y, int step) {
        //如果到达地点，结束
        if (x == fx && y == fy) {

            count++;
            //System.out.println("找到出口");
            if (step < min) {
                min = step;
            }

            return;
        }
        //循环移动到四个方向
        for (int i = 0; i < 4; i++) {
            int tx = temp[i][0];
            int ty = temp[i][1];
            //如果该方向越界了，改变到另一个方向
           /* if(x+tx<0||x+tx>=n)
                continue;
            if(y+ty<0||y+ty>=n)
                continue;*/
            //如果该位置没有障碍物并且也没有走过，走
            if (map[x + tx][y + ty] == 0 && book[x + tx][y + ty] == 0) {
                //标记为走过
                book[x + tx][y + ty] = 1;
                //搜索过程
                //System.out.println("" + (x + tx) + "," + (y + ty) + "->");
                //往下一层递归
                dfs(x + tx, y + ty, step + 1);
                //取消标记，回到上一层
                book[x + tx][y + ty] = 0;
            }
        }
        return;
    }
}
