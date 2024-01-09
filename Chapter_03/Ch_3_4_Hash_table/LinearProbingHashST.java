public class LinearProbingHashST <Key,Value>{
    private int N;//符号表中键值对的总数
    private int M=16; // 线性探测表的大小
    private Key[] keys; // 键
    private Value[] values; //值
    public LinearProbingHashST(){
        keys=(Key[]) new Object[M];
        values=(Value[]) new Object[M];
    }
    private int hash(Key key){
        return (key.hashCode()&0x7ffffff) %M;
    }
    private void resize(){}

    public void put(Key key,Value value)
    {
        if(N>=M/2) resize(2*M);
        int i;
        for(i=hash(key);keys[i]!=null;i=(i+1)%M){
            if(keys[i].equals(key)){
                values[i]=value;
                return;
            }
        }
        keys[i]=key;
        values[i]=value;
        N++;
    }
    public Value get(Key key){
        for (int i = hash(key); keys[i]!=null ;i=(i+1)%M) {
            if(keys[i].equals(key)){
                return values[i];
            }
            return null;
        }
    }
}
