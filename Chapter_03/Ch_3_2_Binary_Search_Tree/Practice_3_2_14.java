import edu.princeton.cs.algs4.Queue;

public class Practice_3_2_14<Key extends Comparable<Key>, Value> {
    private Node root;  // 二叉查找树的根节点

    private class Node {
        private Key key;       // 键
        private Value value;    //值
        private Node left, right; // 指向子树的链接
        private int N;  // 以该节点为根的子树中的节点总数

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.value = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // 在以x为根节点的子树中查找并返回key所对应的值
        // 如果找不到 返回null
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        // 如果Key存在于以x为根节点的子树中则更新它的值
        // 否则将以key和value为键值对的新节点插入到该子树中
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    // Returns the largest key in the symbol table less than or equal to key.
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException();
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        Node temp = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            else if (cmp < 0) x = x.left;
            else {
                temp = x;
                x = x.right;
            }
        }
        return temp;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException();
        Node x = ceiling(root, key);
        if (x != null) return x.key;
        else return null;
    }

    private Node ceiling(Node x, Key key) {
        Node temp = null;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x;
            else if (cmp > 0) x = x.right;
            else {
                temp = x;
                x = x.left;
            }
        }
        return temp;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        while(x!=null){
            int t=size(x.left);
            if(t==k) return x;
            else if (t > k) x=x.left;
            else{
                x=x.right;
                k=k-t-1;
            }
        }
        return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        // 返回以x为根据点的子树中小于x.key的键的数量
        int count=0;
        while(x!=null){
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x=x.left;
            else if (cmp > 0){
                count+=1 + size(x.left);
                x=x.right;
            }
            else count+=size(x.left);
        }
        return count;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return x.left;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key max() {
        if (root == null) return null;
        return max(root).key;
    }

    private Node max(Node x) {
        while (x.right != null) x = x.right;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        sb.append(String.format("\t树规模:%d\n", size()));
        return sb.toString();
    }

    private void toString(Node x, StringBuilder sb) {
        if (x == null) return;
        toString(x.left, sb);
        sb.append(String.format("%4s    %4s    size=%d", x.key, x.value, size(x)));
        toString(x.right, sb);
    }
}
