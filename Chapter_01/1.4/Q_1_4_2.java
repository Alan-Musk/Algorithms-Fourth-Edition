public class Q_1_4_2 extends ThreeSum{
    // 修改 ThreeSum 正确处理两个较大的int值相加可能产出的溢出的情况
    @Override
    public int count(int[] a)
    {
        int N=a.length;
        int cnt=0;
        for (int i=0;i<N;i++)
        {
            for (int j=i+1;j<N;j++)
            {
                for (int k=j+1;k<N;k++)
                {
                    long sum=a[i]+a[j]+a[k];
                    if(sum==0)
                    {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
