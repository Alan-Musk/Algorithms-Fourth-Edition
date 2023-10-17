public class FixedCapacityStackOfStrings<Item>{
    private Item[] a;
    private int N;
    public FixedCapacityStackOfStrings(int cap)
    {
        a=(Item[]) new Object[cap];
    }
    public boolean isEmpty()
    {
        return N==0;
    }
    public int size()
    {
        return N;
    }
    public void push(Item item)
    {
        if(N==a.length) resize(2*a.length);
        a[N++]=item;
    }
    public Item pop()
    {
        Item item=a[--N];
        a[N]=null;
        if(N>0 && N==a.length/4)
        {
            resize(a.length/2);
        }
        return item;

    }
    private void  resize(int max)
    {
        //将大小为N<=max的栈移动到一个新的大小为max的数组中
        Item[] temp=(Item[]) new Object[max];
        if (N >= 0) System.arraycopy(a, 0, temp, 0, N);
        a=temp;
    }


}
