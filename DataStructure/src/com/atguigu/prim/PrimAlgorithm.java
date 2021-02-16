package com.atguigu.prim;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图是否创建成功
        char data[] = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int weight[][] = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        MGraph graph = new MGraph(verxs);
        minTree minTree = new minTree();
        minTree.createGraph(graph, verxs, data, weight);
        minTree.showGraph(graph);
        minTree.prim(graph,3);
    }
}

//创建最小生成树
class minTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param graph  图对象
     * @param verxs  图对应的顶点的个数
     * @param data   图中各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int weight[][]) {
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的临界举证
    public void showGraph(MGraph graph) {
        for (int link[] : graph.weight) {
            System.out.println(Arrays.toString(link));

        }
    }

    /**
     * 编写prim算法，得到最小生成树
     *
     * @param graph 图
     * @param v     从图的第几个顶点开始生成 'A'->0 'B'->1
     */
    public void prim(MGraph graph, int v) {
        //visited[] 标记节点是否被访问过，初始为0
        int visited[] = new int[graph.verxs];

        //将当前节点设为以访问,0代表为访问，1代表以访问
        visited[v] = 1;
        //h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;

        int minWeight = 10000;//将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) { //因为verxs个顶点，普利姆算法结束后，会有verxs-1个顶点

            //确定每次生成的子图和那个节点距离最近
            for (int i = 0; i < graph.verxs; i++) { //i节点表示被访问过的节点
                for (int j = 0; j < graph.verxs; j++) { //j节点表示没有被访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && minWeight > graph.weight[i][j]) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            //找到一条最小的边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前这个节点标记为已访问
            visited[h2] = 1;
            //将minweight重置为10000
            minWeight = 10000;
        }


    }
}

class MGraph {
    int verxs;//表示图的节点个数
    char[] data;// 表示节点数据的值
    int[][] weight;//存放边，邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
