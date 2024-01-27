import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.IndexMinPQ;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;  // 保存最短路径
    private double[] distTo;    // 保存最短路径长度
    private IndexMinPQ<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            // 按照 distTo的长度来relax
            relax(G, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e :
                G.adj(v)) {
            int w=e.to(); // e边的顶点 w
            if(distTo[w]>distTo[v]+e.weight()){
                distTo[w]=distTo[v]+e.weight();
                edgeTo[w]=e;
                if(pq.contains(w)) pq.change(w,distTo[w]);
                else               pq.insert(w,distTo[w]);
            }
        }
    }
}