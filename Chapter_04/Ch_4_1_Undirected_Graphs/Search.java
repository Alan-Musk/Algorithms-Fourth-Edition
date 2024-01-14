public class Search {
    private int s; // 与s连通的顶点总数
    private UF uf;
    // API 1: 找到和起点s连通的所有顶点
    public Search(Graph G,int s){
        uf=new UF(G.V());
        for (int i = 0; i < G.V(); i++) {
            for (int w :
                    G.adj(s)) {
                uf.union(w,i);
            }
        }
        this.s=s;
    }
    // API 2: v 和 s是连通的吗
    public boolean marked(int v){
        return uf.connected(s,v);
    }
    // API 3: 与s连通的顶点总数
    public int count(){return uf.count(uf.find(s));}

    private class UF{
        private int[] id; // 父链接数组(由触点索引)
        private int[] sz; // (由触点索引的)各个根节点所对应的分量的大小
        private int count; // 连通分量的数量
        // 实际上是 加权quick-union算法
        public UF(int N){
            count=N;
            id=new int[N];
            for (int i = 0; i < N; i++) {
                id[i]=i;
            }
            sz=new int[N];
            for (int i = 0; i < N; i++) {
                sz[i]=1;
            }
        }
        public int count(){return count;}
        public boolean connected(int p,int q){
            return find(p)==find(q);
        }
        public int find(int p){
            // 跟随链接找到根节点
            while(p!=id[p]) p=id[p];
            return p;
        }
        public void union(int p,int q){
            int i=find(p);
            int j=find(q);
            if(i==j) return;
            // 将小树的根节点连接到大树的根节点
            if(sz[i]<sz[j]){
                id[i]=j;
                sz[j]+=sz[i];
            }
            else{
                id[j]=i;
                sz[i]+=sz[j];
            }
            count--;
        }
    }
}

