package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点的集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//该节点是否被访问

    public static void main(String[] args) {
        //测试
        int n = 8;
        //String vertexs[] = {"A", "D", "W", "G", "E"};
        String vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(n);
        //插入顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
       /* graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);*/
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        graph.insertEdges(3, 7, 1);
        graph.insertEdges(4, 7, 1);
        graph.insertEdges(2, 5, 1);
        graph.insertEdges(2, 6, 1);
        graph.insertEdges(5, 6, 1);

        //显示
        graph.getGraph();
        //测试dfs
        System.out.println("深度遍历:");
        graph.dfs();

        System.out.println();
        //测试bfs
        System.out.println("广度遍历:");
        graph.bfs();

    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;

    }

    //返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //根据索引返回对应的顶点
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void getGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     v1表示点的下标即第几个顶点 A->0 B->1
     * @param v2     v2 表示第二个顶点表示的下标
     * @param weight 表示两点之间的边的权值
     */
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 得到第一个邻接节点的下标
     *
     * @param index 节点索引
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点
     *
     * @param v1 当前节点
     * @param v2 第一个邻接节点
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    public void dfs(boolean[] isVisited, int i) {
        //首先访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设为已经访问
        isVisited[i] = true;
        //查找该节点的第一个邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            //如果存在看是否被访问
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果已经被访问
            w = getNextNeighbor(i, w);
        }

    }

    //重载，遍历所有的节点
    public void dfs() {
        //遍历所有的节点，进行dfs
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //广度优先遍历算法
    public void bfs(boolean[] isVisited, int i) {
        int u; //表示队列头节点的下标
        int w; //邻接节点
        LinkedList queue = new LinkedList();//队列，记录节点访问的顺序
        //访问节点，输出信息
        System.out.print(getValueByIndex(i) + "->");
        //将节点设为已经访问
        isVisited[i] = true;
        //将节点添加到队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列头节点的下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接节点的下标
            w = getFirstNeighbor(u);
            while (w != -1) {//找到
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    //标记为已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);

            }
        }

    }

    //遍历所有的节点,bfs
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

}
