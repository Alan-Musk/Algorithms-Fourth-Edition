import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
    // edgeTo 数组，存储连接每个顶点到最小生成树的最短边
    private Edge[] edgeTo;

    // distTo 数组，存储连接每个顶点到最小生成树的最短边的权重
    private double[] distTo;

    // 标记数组，表示每个顶点是否已经在最小生成树中
    private boolean[] marked;

    // 优先队列，用于高效地找到当前未包含在最小生成树中的顶点与树之间的最短边
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G){
        // 初始化 edgeTo, distTo, 和 marked 数组
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];

        // 将所有顶点的距离（distTo 值）初始化为无限大
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        // 初始化优先队列
        pq = new IndexMinPQ<>(G.V());

        // 以顶点 0 为起点构建最小生成树
        distTo[0] = 0.0;
        pq.insert(0, 0.0); // 使用顶点 0 和权重 0 初始化优先队列

        // 当优先队列不为空时，继续构建最小生成树
        while (!pq.isEmpty()) {
            visit(G, pq.delMin()); // 删除并访问距离最小生成树最近的顶点
        }
    }

    private void visit(EdgeWeightedGraph G, int v){
        // 将顶点 v 添加到最小生成树中，并更新相关数据
        marked[v] = true;

        // 遍历与顶点 v 相邻的所有边
        for (Edge e : G.adj(v)) {
            int w = e.other(v); // 获取与 v 相邻的另一个顶点 w

            // 如果 w 已在最小生成树中，则跳过这条边
            if (marked[w]) continue;

            // 如果边 e 的权重小于当前连接 w 的最短边的权重
            if (e.weight() < distTo[w]) {
                // 更新连接顶点 w 的最短边为 e
                edgeTo[w] = e;
                distTo[w] = e.weight();

                // 如果优先队列中已包含 w，则更新其关联权重
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]); // 否则，将 w 插入优先队列
            }
        }
    }
}
