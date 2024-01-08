public class LinearProbingHashST <Key,Value>{
    private int N;//符号表中键值对的总数
    private int M=16; // 线性探测表的大小
    private Key[] keys; // 键
    private Value[] values; //值
    public LinearProbingHashST(){
        keys=(Key[]) new Object[M];
    }
}
