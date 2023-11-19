import java.awt.*;

public class Q_1_4_3 extends DoublingTest{
    //修改DoublingTest 使用StdDraw产生类似于正文中的标准图像和对数图像,根据需要调整比例使图像总能够充满窗口的大部分区域
    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 10);
        for(int N=250;true;N+=N)
        {
            DoublingTest test=new DoublingTest();
            double time=test.timeTrial(N);

            // Plot points on the graph
            StdDraw.setPenRadius(0.02);
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.point(N, time);
            StdOut.printf("%7d %5.1f\n",N,time);
        }
    }
}
