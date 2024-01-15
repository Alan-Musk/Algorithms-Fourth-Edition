public class Search {
    private int s; // 与s连通的顶点总数
    private UF uf;
    // API 1: 找到和起点s连通的所有顶点
    public Search(Graph G, int s) {
        uf = new UF(G.V());
        for (int i = 0; i < G.V(); i++) {
            for (int w :
                    G.adj(i)) {
                uf.union(i,w);
            }
        }
        this.s = s;
    }
    // API 2: v 和 s是连通的吗
    public boolean marked(int v) {
        return uf.connected(s, v);
    }
    // API 3: 与s连通的顶点总数
    public int count() {
        return uf.count(uf.find(s));
    }
    private class UF {
        private int[] id; // 父链接数组(由触点索引)
        private int[] sz; // (由触点索引的)各个根节点所对应的分量的大小
        public UF(int N) {
            id = new int[N];
            for (int i = 0; i < N; i++) {
                id[i] = i;
            }
            sz = new int[N];
            for (int i = 0; i < N; i++) {
                sz[i] = 1;
            }
        }
        public int count(int p) {
            return sz[p];
        }
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
        public int find(int p) {
            while(p!=id[p]){
                id[p]=id[id[p]]; // 路径压缩: 将id的祖父节点的值 赋值给id[p]父节点
                p=id[p];        // 向上移动到父节点
            }
            return p;
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootQ == rootP) return;
            if(sz[rootP]<sz[rootQ]){
                id[rootP]=rootQ;
                sz[rootQ]+=sz[rootP];
            }else{
                id[rootQ]=rootP;
                sz[rootP]+=sz[rootQ];
            }
        }
    }
}

