import edu.princeton.cs.algs4.Queue;
public class Practice_3_1_12 {

    public class BinarySearchST<Key extends Comparable<Key>, Value> {
        private Item items[];
        private int N;

        private class Item
        {
            private Key key;
            private Value value;
            private Item(Key key,Value value)
            {
                this.key=key;
                this.value=value;
            }
        }

        @SuppressWarnings("unchecked")
        public BinarySearchST(int capacity) {
            items=(Item[]) new Object[capacity];
        }

        public int size() {
            return N;
        }

        public Value get(Key key) {
            if (isEmpty()) return null;
            int i = rank(key);
            if (i < N && items[i].key.compareTo(key)== 0) return items[i].value;
            else return null;
        }

        public void put(Key key, Value value) {
            // 查找键 找到时更新值 否则创建新的元素
            int i = rank(key);
            if (i < N && items[i].key.compareTo(key) == 0) {
                items[i].value = value;
                return;
            }
            for (int j = N; j > i; j--) {
                items[j] = items[j-1];
            }
            items[i]=new Item(key, value);
            N++;
        }

        public int rank(Key key) {
            int lo = 0, hi = N - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = key.compareTo(items[mid].key);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            // 设在某一点时lo=N-2 则mid=N-2 -> lo=N-1 -> mid=N-1 lo=N 退出循环
            return lo;
        }

        public Key min() {
            if (N == 0) return null;
            return items[0].key;
        }

        public Key max() {
            if (N == 0) return null;
            return items[N-1].key;
        }

        public Key select(int k) {
            return items[k].key;
        }

        public Key ceiling(Key key) {
            int i = rank(key);
            return items[i].key;
        }

        public Key floor(Key key) {
            int i = rank(key);
            if (contains(key)) {
                return items[i].key;
            }
            return items[i-1].key;
        }

        public void delete(Key key) {
            int i = rank(key);
            if (i < N && items[i].key.compareTo(key) == 0) {
                N -= 1;
                for (int j = i; j < N - 1; j++) {
                    items[j]=items[j+1];
                }
                items[N]=null;
            }
        }

        public boolean contains(Key key) {
            int i = rank(key);
            if (i < N && items[i].key.compareTo(key) == 0) {
                return true;
            }
            return false;
        }

        public Iterable<Key> keys(Key lo, Key hi) {
            Queue<Key> q = new Queue<>();
            for (int i = rank(lo); i < rank(hi); i++) {
                q.enqueue(items[i].key);
            }
            if (contains(hi)) {
                q.enqueue(items[rank(hi)].key);
            }
            return q;
        }

        private boolean isEmpty() {
            return N == 0;
        }
    }
}
