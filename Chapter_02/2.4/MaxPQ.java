public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // 基于堆的完全二叉树
    private int N=0;    // 存储于pq[1..N]中,pq[0] 没有使用
    // 创建一个优先队列
    public void MaxPQ() {

    }

    // 创建一个初始容量为max的优先队列
    public void MaxPQ(int maxN) {
        pq=(Key[]) new Comparable[maxN+1];
    }

    // 用a[]中的元素创建一个优先队列
    public void MaxPQ(Key[] a) {

    }

    // 向优先队列中插入一个元素
    public void insert(Key v) {
        pq[++N]=v;
        swim(N);
    }

    // 返回最大元素
    public Key max() {
        return pq[1];
    }

    // 删除并返回最大元素
    public Key delMax() {
        Key max=pq[1];
        exch(1,N--);    // 将其和最后一个节点交换
        pq[N+1]=null;   // 防止对象游离
        sink(1);        // 恢复堆的有序性
        return max;
    }

    // 返回队列是否为空
    public boolean isEmpty()
    {
        return N==0;
    }

    // 返回优先队列中的元素个数
    public int size()
    {
        return N;
    }

    // 辅助方法
    // 比小
    private boolean less(int i,int j)
    {
        return pq[i].compareTo(pq[j])<0;
    }
    // 互换
    private void exch(int i,int j)
    {
        Key t=pq[i];
        pq[i]=pq[j];
        pq[j]=t;
    }
    // 上浮
    private void swim(int k)
    {
        while (k>1 && less(k/2,k))
        {
            exch(k/2,k);
            k=k/2;
        }
    }

    // 下沉
    private void sink(int k)
    {
        while(2*k<=N)
        {
            int j=2*k;
            if(j<N && less(j,j+1)) j++;
            if (!less(k,j)) break;
            exch(k,j);
            k=j;
        }
    }
}
