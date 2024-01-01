import edu.princeton.cs.algs4.Queue;

public class Practice_3_1_16_17 {
    public class BinarySearchST<Key extends Comparable<Key>, Value> {
        private Key[] keys;
        private Value[] values;
        private int N;

        @SuppressWarnings("unchecked")
        public BinarySearchST(int capacity) {
            keys = (Key[]) new Comparable[capacity];
            values = (Value[]) new Object[capacity];
        }

        public int size() {
            return N;
        }

        public Value get(Key key) {
            if (isEmpty()) return null;
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) return values[i];
            else return null;
        }

        public void put(Key key, Value value) {
            // 查找键 找到时更新值 否则创建新的元素
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) {
                values[i] = value;
                return;
            }
            for (int j = N; j > i; j--) {
                keys[j] = keys[j - 1];
                values[j] = values[j - 1];
            }
            keys[i] = key;
            values[i] = value;
            N++;
        }

        public int rank(Key key) {
            int lo = 0, hi = N - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = key.compareTo(keys[mid]);
                if (cmp < 0) hi = mid - 1;
                else if (cmp > 0) lo = mid + 1;
                else return mid;
            }
            // 设在某一点时lo=N-2 则mid=N-2 -> lo=N-1 -> mid=N-1 lo=N 退出循环
            return lo;
        }

        public Key min() {
            if (N == 0) return null;
            return keys[0];
        }

        public Key max() {
            if (N == 0) return null;
            return keys[N - 1];
        }

        public Key select(int k) {
            return keys[k];
        }

        public Key ceiling(Key key) {
            int i = rank(key);
            return keys[i];
        }

        public Key floor(Key key) {
            int i = rank(key);
            if (contains(key)) {
                return keys[i];
            }
            return keys[i - 1];
        }

        public void delete(Key key) {
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) {
                N -= 1;
                for (int j = i; j < N - 1; j++) {
                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];
                }
                keys[N] = null;
                values[N] = null;
            }
        }

        public boolean contains(Key key) {
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) {
                return true;
            }
            return false;
        }

        public Iterable<Key> keys(Key lo, Key hi) {
            Queue<Key> q = new Queue<>();
            for (int i = rank(lo); i < rank(hi); i++) {
                q.enqueue(keys[i]);
            }
            if (contains(hi)) {
                q.enqueue(keys[rank(hi)]);
            }
            return q;
        }

        private boolean isEmpty() {
            return N == 0;
        }
    }

}
