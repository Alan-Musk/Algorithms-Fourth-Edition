import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {
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
        if (x.left == null) return x;
        return min(x.left);
    }

    // Returns the largest key in the symbol table less than or equal to key.
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null; // Base case: if the node is null, return null.
        int cmp = key.compareTo(x.key); // Compare the given key with the key of the current node.

        if (cmp == 0) return x; // If the keys are equal, we have found the floor value, return current node.

        if (cmp < 0) {
            // If the given key is less than the node's key, then the floor of the key
            // must be in the left subtree.
            return floor(x.left, key);
        }

        // If the given key is greater than the node's key, then the floor might be in the right subtree;
        // however, if the right subtree call returns null, it means the largest key less than or equal to
        // the given key is the key of the current node.
        Node t = floor(x.right, key);
        if (t != null) {
            // If we found a node in the right subtree, return it.
            return t;
        } else {
            // Otherwise, the current node is the floor.
            return x;
        }
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x != null) return x.key;
        else return null;
    }

    private Node ceiling(Node x, Key key) {
        if(x==null)  return null;
        int cmp=key.compareTo(x.key);
        if(cmp==0) return x;
        else if (cmp > 0) return ceiling(x.right,key);
        Node f=ceiling(x.left,key);
        if (f!=null) return f;
        return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        // 返回排名为K的节点
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        // 返回以x为根据点的子树中小于x.key的键的数量
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
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
        if (x.right != null) return max(x.right);
        else return x;
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

    public String toString(){
        StringBuilder sb=new StringBuilder();
        toString(root,sb);
        sb.append(String.format("\t树规模:%d\n",size()));
        return sb.toString();
    }
    private void toString(Node x,StringBuilder sb){
        if(x==null) return;
        toString(x.left,sb);
        sb.append(String.format("%4s    %4s    size=%d",x.key,x.value,size(x)));
        toString(x.right,sb);
    }
}
