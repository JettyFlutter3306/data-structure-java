package cn.element.datastructure.algorithm.list;

public class C04RingArray {

    /**
     * 使用数组实现队列
     */
    private static class Queue {
        private int[] arr;
        private int pushI;
        private int pollI;
        private int size;
        private final int limit;

        public Queue(int limit) {
            this.arr = new int[limit];
            this.pushI = 0;
            this.pollI = 0;
            this.size = 0;
            this.limit = limit;
        }
        
        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了");
            }
            
            size++;
            arr[pushI] = value;
            pushI = nextIndex(pushI);
        }
        
        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列空了");
            }
            
            size--;
            int ans = arr[pollI];
            pollI = nextIndex(pollI);
            return ans;
        }
        
        public boolean isEmpty() {
            return size == 0;
        }

        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }

}
