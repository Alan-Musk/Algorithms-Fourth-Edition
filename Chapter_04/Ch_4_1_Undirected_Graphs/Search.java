import edu.princeton.cs.algs4.UF;

public class Search {
    private int sum; // 与s连通的顶点总数
    private UF search;
    // API 1: 找到和起点s连通的所有顶点
    public Search(Graph G,int s){
        for (Integer i :
                G.adj(s)) {
            search.union();
        }
    }
    // API 2: v 和 s是连通的吗
    public boolean marked(int v){

    }
    // API 3: 与s连通的顶点总数
    public int count(){return sum;}
}
