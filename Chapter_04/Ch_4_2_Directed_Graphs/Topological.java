import edu.princeton.cs.algs4.SymbolDigraph;

public class Topological {
    private Iterable<Integer> order;// 顶点的拓扑顺序
    public Topological(Digraph G){
        DirectedCycle cyclefinder=new DirectedCycle(G);
        if(!cyclefinder.hasCycle()){
            DepthFirstOrder dfs=new DepthFirstOrder(G);
            order=dfs.reverPost();
        }
    }
    public Iterable<Integer> order(){
        return order;
    }
    public boolean isDAG(){
        return order!=null;
    }
    
}
