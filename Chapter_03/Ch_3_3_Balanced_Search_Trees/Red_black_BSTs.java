import com.sun.jdi.Value;

import javax.management.ValueExp;
import java.util.NoSuchElementException;

public class Red_black_BSTs<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    // BST helper node data type
    private class Node {
        private Key key;    // key
        private Value val;  //associated data
        private Node left, right; // subtree
        private int N;  // # nodes in this subtree
        private boolean color; // color of link from parent to this node

        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
    public int size(){
        return size(root);
    }
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }
    public boolean isEmpty()
    {
        return root==null;
    }
    // 标准BST Search
    public Value get(Key key)
    {
        if(key==null) throw new IllegalArgumentException("argument to get() is null");
        return get(root,key);
    }
    private Value get(Node x,Key key)
    {
        while(x!=null)
        {
            int cmp= key.compareTo(x.key);
            if(cmp<0) x=x.left;
            else if(cmp>0) x=x.right;
            else return x.val;
        }
        return null;
    }
    public boolean contains(Key key)
    {
        return get(key)!=null;
    }
    // RB_tree insertion
    public void put(Key key, Value val) {
        if(key==null) throw new IllegalArgumentException("first argument to put() is null");
        if(val==null) delete(key);
        // 查找key 找到则更新其值,否则为它新建一个节点
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        // 标准的插入操作 和父节点用红链接相连
        if (h == null) return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.left)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
    // RB_tree deletion
    public void deleteMin()
    {
        if(isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if(!isRed(root.left)&& !isRed(root.right)) root.color=RED;

        root=deleteMin(root);
        if(!isEmpty()) root.color=BLACK;
    }

    private Node deleteMin(Node h)
    {
        if(h.left==null) return null;
        if(!isRed(h.left)&&!isRed(h.left.left)) h=moveRedLefe(h);
        h.left=deleteMin(h.left);
        return balance(h);
    }
    // 左旋转h的右链接
    public Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    // 右旋转h的左链接
    public Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }



    public void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }


}
