package com.atguigu.kruskal;

import java.util.Arrays;

public class KruskalCase {
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //代表两个顶点之间不连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        EData[] edges = kruskalCase.getEdges();
        System.out.println("未排序:" + Arrays.toString(edges));
        kruskalCase.sortEdges(edges);
        System.out.println("排序后:" + Arrays.toString(edges));
        kruskalCase.kruskal();

    }

    //构造器
    public KruskalCase(char vertexs[], int matrix[][]) {
        //初始化顶点个数和边的个数
        int vlen = vertexs.length;

        //初始化顶点，复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边，复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边的个数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }

    }

    //打印
    public void print() {
        System.out.println("邻接矩阵为:\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }


    }

    //对边进行排序处理，冒泡排序
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    //返回ch顶点对应的下标，找不到返回-1
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (ch == vertexs[i]) {
                return i;
            }
        }
        return -1;
    }

    //获取图中的边，放入EData[]数组中
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能: 获取下标为 i 的顶点的终点(), 用于后面判断两个顶点的终点是否相同
     *
     * @param ends 数组就是记录了各个顶点对应的终点是哪个,ends 数组是在遍历过程中，逐步形成
     * @param i    表示传入的顶点对应的下标
     * @return 返回的就是 下标为 i 的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) { //i=4[0,0,0,0,5,0,0,0,0,0,0,0] E点的终点为F
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;//表示最后结果的索引
        //用于保存"已有最小生成树" 中的每个顶点在最小生成树中的终点
        int ends[] = new int[edgeNum];
        EData[] rets = new EData[edgeNum];//结果数组，保存最后的最小生成树

        //获取所有边的集合
        EData[] edges = getEdges();
        System.out.println("图边的集合=" + Arrays.toString(edges) + ",共" + edges.length + "条边");

        sortEdges(edges);//把边按着权值大小进行排序，从小到大
        //遍历 edges 数组，将边添加到最小生成树中时，判断是准备加入的边否形成了回路，如果没有，就加入 rets,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的第一个顶点，在顶点数组中的位置
            int p1 = getPosition(edges[i].start);//p1=4,E
            //获取第i条边的第二个顶点，在顶点数组中的位置
            int p2 = getPosition(edges[i].end); // p2=5,F

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);//m=4
            //获取p2在已有最小生成树中的终点
            int n = getEnd(ends, p2); //n=5

            //判断是否形成回路
            if (m != n) {//没有构成回路
                ends[m] = n;//设置 m 在"已有最小生成树"中的终点 <E,F>[0,0,0,0,5,0,0,0,0,0,0,0]
                rets[index++] = edges[i]; //将边加入到rets数组
            }

        }
       // System.out.println(Arrays.toString(ends));

        //统计并打印最小生成树，并输出rets
        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

}

//创建一个类EData，它的对象实例表示一条边
class EData {
    char start;//表示边的起点
    char end; //表示边的终点
    int weight;//表示边的权重

    //构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                "," + end +
                ">=" + weight +
                '}';
    }
}


