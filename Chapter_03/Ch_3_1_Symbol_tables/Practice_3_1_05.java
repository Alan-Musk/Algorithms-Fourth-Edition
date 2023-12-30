import java.util.LinkedList;

public class Practice_3_1_05 {
    public class SequentialSearchST<Key extends Comparable<Key>, Value> {
        private Node first; // 链表首节点
        private int size;

        private class Node {
            // 链表节点的定义
            Key key;
            Value val;

            Node next;

            public Node(Key key, Value val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        public Value get(Key key) {
            // 查找给定的键,返回相关联的值
            for (Node x = first; x != null; x = x.next) {
                if (key.equals(x.key)) return x.val;
            }
            return null;
        }

        public void put(Key key, Value val) {
            // 查找给定的键,找到则更新其值,否则在表中新建节点
            if(key!=null&&val!=null)
            {
                for(Node x = first; x!=null; x=x.next)
                {
                    if(key.equals(x.key)){
                        x.val=val;
                        return;
                    }
                }
                first=new Node(key,val,first); //没有找到 新建节点
                size+=1;
            }

        }

        public int size()
        {
            return size;
        }
        // 删除其值
        public void delete(Key key)
        {
            if(first!=null && key.equals(first.key)){
                first=first.next;
                size-=1;
                return;
            }
            Node parent=null;
            for(Node x = first; x!=null; parent=x,x=x.next){
                if(key.equals(x.key)){
                    parent.next=x.next;
                    size-=1;
                    return;
                }
            }
        }
        public Iterable<Key> keys()
        {
            LinkedList<Key> list=new LinkedList<>();
            for(Node x = first; x!=null; x=x.next){
                list.add(x.key);
            }
            return list;
        }


    }

}
