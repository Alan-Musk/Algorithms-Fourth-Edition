import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import static org.junit.Assert.*;
import org.junit.Test;

public class Date {
    private final int month;
    private final int day;
    private final int year;
    public Date()
    {
        month=0;
        day=0;
        year=0;
    }
    public Date(int m,int d,int y)
    {
        month=m;
        day=d;
        year=y;
    }
    public int month()
    {
        return month;
    }
    public int day()
    {
        return day;
    }
    public int year()
    {
        return year;
    }
    public String toString()
    {
        return month()+"/"+day()+"/"+year();
    }
    public boolean equals(Object x)
    {
        if(this==x) return true;
        if(x==null) return false;
        if(this.getClass()!=x.getClass()) return false;
        Date that=(Date) x;
        if(this.day!=that.day) return false;
        if(this.month!=that.month) return false;
        if(this.year!=that.year) return false;
        return true;
    }
    @Test
    public void TestDate()
    {
        Date date=new Date(9,22,2003);
        assertEquals("9/22/2003",toString());
    }

}
