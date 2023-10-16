import static org.junit.Assert.assertEquals;

public class Test {
    @org.junit.Test
    public void TestDate()
    {
        Date date=new Date(9,22,2003);
        String s=date.toString();
        assertEquals("9/22/2003",s);
    }
}
