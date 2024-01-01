public class BST<Key extends Comparable<Key>, Value> {
    private Node root;  // 二叉查找树的根节点

    private class Node {
        private Key key;       // 键
        private Value value;    //值
        private Node left, right; // 指向子树的链接
        private int N;  // 以该节点为根的子树中的节点总数

        public Node(Key key, Value val, int N) {
            this.key=key;this.value=val;this.N=N;
        }
    }
    public int size() {
        return size(root);
    }
    private int size(Node x){
        if(x==null) return 0;
        else return x.N;
    }
    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x,Key key){
        // 在以x为根节点的子树中查找并返回key所对应的值
        // 如果找不到 返回null
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp<0) return get(x.left,key);
        else if(cmp>0) return get(x.right,key);
        else return x.value;
    }
    public void put(Key key,Value value){
        root=put(root,key,value);
    }
    private Node put(Node x,Key key,Value value){
        // 如果Key存在于以x为根节点的子树中则更新它的值
        // 否则将以key和value为键值对的新节点插入到该子树中
        if(x==null) return new Node(key,value,1);
        int cmp= key.compareTo(x.key);
        if(cmp<0) x.left=put(x.left,key,value);
        else if(cmp>0) x.right=put(x.right,key,value);
        else x.value=value;
        x.N=size(x.left)+size(x.right)+1;
        return x;


    }

}
