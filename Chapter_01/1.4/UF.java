import edu.princeton.cs.algs4.StdOut;

public class UF {
    private int[] id; //access to component id(site indexed)
    private int count; //number of components

    public UF(int N) {
        //Initialize component id array;
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {

        return id[p];
    }

    public void union(int p, int q) {
        //将P和Q归并到相同的分量重
        int pID=find(p);
        int qID=find(q);

        //如果p和q已经在相同的分量之中则不需要采取任何行动
        if(pID==qID)
        {
            return;
        }

        //将P的分量重命名为Q的名称
        for (int i = 0; i < id.length; i++) {
            if(id[i]==pID)
            {
                id[i]=qID;
            }
        }
        count--;
    }

    public static void main(String[] args) {
        // Solve dynamic connectivity problem on StdIn.
        int N=StdIn.readInt();  //Read number of sites
        UF uf=new UF(N);
        while(!StdIn.isEmpty())
        {
            int p=StdIn.readInt();
            int q=StdIn.readInt(); // Read pair to connect
            if(uf.connected(p,q)) continue; // Ignore if connected
            uf.union(p,q);          // Combine components
            StdOut.println(p+" "+q);
        }
        StdOut.println(uf.count()+" components");
    }
}
