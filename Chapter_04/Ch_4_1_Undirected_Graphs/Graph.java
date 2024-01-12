import edu.princeton.cs.algs4.Bag;

public class Graph {
    private final int V;    //顶点数目
    private int E; // 边的数目
    private Bag<Integer>[] adj; // 邻接表
    // API1:创建一个含有V个顶点但不含有边的图
    public Graph(int V){
        this.V=V;this.E=0;
        adj=(Bag<Integer>[])  new Bag[V];// 创建邻接表
        // 将所有链表初始化为空
        for(int v=0;v<V;v++){
            adj[V]=new Bag<Integer>();
        }
    }
    // API2:从标准输入流in读取一幅图
    public Graph(In in){
        this(in.readInt()); // 读取V并将图初始化
        int E=in.readInt(); // 读取E
        for (int i = 0; i < E; i++) {
            // 添加一条边
            int v=in.readInt();
            int w=in.readInt();
            addEdge(v,w); // 添加一条连接它们的边
        }
    }
    // API3: 顶点数
    public int V(){return V;}
    // API4: 边数
    public int E(){return V;}
    // API 5: 向图中添加一条边 v-w
    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    // API 6: 和v相邻的所有顶点
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}