public class DoublingTest {
    public double timeTrial(int N)
    {
        //为处理N个随机的六位整数ThreeSum.count()计时
        int MAX=1000000;
        int[] a=new int[N];
        for (int i=0;i<N;i++)
        {
            a[i]=StdRandom.uniform(-MAX,MAX);
        }
        Stopwatch timer=new Stopwatch();
        ThreeSum test=new ThreeSum();
        int cnt=test.count(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        //打印运行时间的表格
        for (int N=250;true;N+=N)
        {
            DoublingTest test=new DoublingTest();
            //打印问题规模为N时程序的用时
            double time=test.timeTrial(N);
            StdOut.printf("%7d %5.1f\n",N,time);
        }
    }
}
