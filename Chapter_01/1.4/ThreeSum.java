public class ThreeSum {
    public int count(int[] a)
    {
        //统计和为0的元组的数量
        int N=a.length;
        int cnt=0;
        for (int i=0;i<N;i++)
        {
            for (int j=i+1;j<N;j++)
            {
                for (int k=j+1;k<N;k++)
                {
                    if(a[i]+a[j]+a[k]==0)
                    {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        ThreeSum test=new ThreeSum();
        int[] a=In.readInts("1Kints.txt");
        StdOut.println(test.count(a));
    }
}
