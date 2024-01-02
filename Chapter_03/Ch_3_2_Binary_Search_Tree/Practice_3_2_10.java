import edu.princeton.cs.introcs.StdOut;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Practice_3_2_10 {
    private  BST<Integer,String> test;
    @Before
    public void setUP(){
        test=new BST<>();
        test.put(10,"10");
        test.put(2,"2");
        test.put(0,"0");
        test.put(9,"9");
        test.put(14,"14");
    }
    @Test
    public void testMin(){
        Integer expectAnswer=0;
        assertEquals(expectAnswer,test.min());
    }
    @Test
    public void testMax(){
        Integer expectAnswer=14;
        assertEquals(expectAnswer,test.max());
    }
    @Test
    public void testFloor(){
        Integer expectAnswer=10;
        assertEquals(expectAnswer,test.floor(11));
    }
    @Test
    public void testCeiling()
    {
        Integer expectAnswer=2;
        assertEquals(expectAnswer,test.ceiling(1));
    }
    @Test
    public void testSelect()
    {
        Integer expectAnswer=2;
        assertEquals(expectAnswer,test.select(1));
    }
    @Test
    public void testRank()
    {
        int expectAnswer=4;
        assertEquals(expectAnswer,test.rank(14));
    }
    @Test
    public void testDelete()
    {
        test.delete(0);
        StdOut.println(test);
        assertEquals(4,test.size());
        assertNull(test.get(0));
    }
    @Test
    public void testKeys(){
        for(Integer key:test.keys()){
            StdOut.printf("{%s %s}\n",key,test.get(key));
        }
    }


}
