public class Practice_3_2_13 {
    // TODO: Put()和Get()非递归版
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
            while (x != null) {
                int cmp = key.compareTo(x.key);
                if (cmp > 0) x = x.right;
                else if (cmp < 0) x = x.left;
                else return x.value;
            }
            return null;
        }

        public void put(Key key, Value value) {
            root = put(root, key, value);
        }

        private Node put(Node x, Key key, Value value) {
            if (x == null) return new Node(key, value, 1);

            Node parent = null;
            Node current = x;
            int cmp = 0;
            while (current != null) {
                parent = current;
                cmp = key.compareTo(current.key);
                if (cmp > 0) current = current.right;
                else if (cmp < 0) current = current.left;
                else {
                    current.value = value;
                    return x;
                }
            }
            Node newNode = new Node(key, value, 1);
            if (cmp < 0) parent.left = newNode;
            else parent.right = newNode;
            return x;
        }

    }

}
