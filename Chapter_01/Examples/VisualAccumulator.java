public class VisualAccumulator {
    private double total;
    private int N;
    public VisualAccumulator(int trails,double max)
    {
        StdDraw.setXscale(0,trails);
        StdDraw.setYscale(0,max);
        StdDraw.setPenRadius(0.005);
    }
    public void addDataValue(double val)
    {
        N++;
        total+=val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N,val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N,total/N);
    }
    public double mean()
    {
        return total/N;
    }
    public String toString()
    {
        return "Mean ("+N+"values):"+String.format("%7.5f",mean());
    }

    public static void main(String[] args) {
        VisualAccumulator a=new VisualAccumulator(1000,1.0);
        for(int t=0;t<1000;t++)
        {
            a.addDataValue(StdRandom.random());
        }
        StdOut.println(a);
    }
}
