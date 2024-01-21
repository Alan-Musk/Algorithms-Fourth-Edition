import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;

public class Practice_4_4_2 {
    public class EdgeWeightedDigraph {
        private final int V; // 顶点总数
        private int E;      // 边的总数
        private Bag<DirectedEdge>[] adj; // 邻接表
        public EdgeWeightedDigraph(int V){
            this.V=V;
            this.E=0;
            adj=(Bag<DirectedEdge>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v]=new Bag<>();
            }
        }
        public EdgeWeightedDigraph(In in){
            this(in.readInt());
            int E=in.readInt();
            DirectedEdge edge;
            for (int i = 0; i < E; i++) {
                edge=new DirectedEdge(in.readInt(),in.readInt(),in.readDouble());
                addEdge(edge);
            }
        }
        public int V(){
            return V;
        }
        public int E(){
            return E;
        }
        public void addEdge(DirectedEdge e){
            adj[e.from()].add(e);
            E++;
        }
        public Iterable<DirectedEdge> adj(int v){
            return adj[v];
        }
        public Iterable<DirectedEdge> edges(){
            Bag<DirectedEdge> bag=new Bag<>();
            for (int v = 0; v < V; v++) {
                for (DirectedEdge e :
                        adj[v]) {
                    bag.add(e);
                }
            }
            return bag;
        }
        public String toString(){
            StringBuilder sb=new StringBuilder();
            sb.append("V: "+V+"\n");
            sb.append("E: "+E+"\n");
            for (int v = 0; v < V; v++) {
                sb.append("Vertex "+v+": ");
                for (DirectedEdge edge: adj[v]) {
                    sb.append(edge+"    ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

}
