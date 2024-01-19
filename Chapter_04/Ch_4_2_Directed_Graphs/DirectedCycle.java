import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;   // 有向环中的所有顶点 （如果存在）
    private boolean[] onStack;  // 递归调用的栈上的所有顶点

    // 构造函数 对各部分初始化
    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            // 如果未被标记 对该顶点v进行Depth first Search
            if (!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true; // 将当前点进入递归调用
        marked[v] = true; // 将当前点标记已经访问过
        for (int w : G.adj(v)) {
            if(this.hasCycle()) return; // 如果 cycle栈中存在数据 则直接 return 退出
            else if(!marked[w]){    // v 的邻点w 未被访问 则对其dfs
                edgeTo[w]=v;
                dfs(G,w);
            } else if (onStack[w]) {    // onStack【w】第二次访问的时候
                cycle=new Stack<>();
                for (int x = v; x !=w ; x=edgeTo[x]) { // 按照edgeTo 路径push进cycle栈
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v]=false; // 如果以上for 之后没有cycle 则将改点移除起点
    }
    public boolean hasCycle(){
        return cycle!=null;
    }
    public Iterable<Integer> cycle(){
        return cycle;
    }

}
