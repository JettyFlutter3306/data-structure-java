package cn.element.datastructure.algorithm.list;

public class C03LinkedStackAndQueue {
    
    private static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * 使用链表实现队列
     */
    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        /**
         * 头插入
         */
        public void addFromHead(T value) {
            Node<T> cur = new Node<>(value);
            
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        /**
         * 尾插入
         */
        public void addFromBottom(T value) {
            Node<T> cur = new Node<>(value);
            
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.last = cur;
                tail = cur;
            }
        }
    }
    
    

}
