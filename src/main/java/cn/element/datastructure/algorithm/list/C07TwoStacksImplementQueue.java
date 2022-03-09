package cn.element.datastructure.algorithm.list;

import java.util.Stack;

/**
 * 使用双栈实现队列
 */
public class C07TwoStacksImplementQueue {
    
    public static class TwoStacksQueue {
        public Stack<Integer> pushStack;
        public Stack<Integer> popStack;

        public TwoStacksQueue() {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        /**
         * push栈向pop栈倒入数据
         */
        private void pushToPop() {
            if (popStack.empty()) {
                while (!pushStack.empty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }
        
        public void add(int i) {
            pushStack.push(i);
            pushToPop();
        }
        
        public int poll() {
            if (popStack.empty() && pushStack.empty()) {
                throw new RuntimeException("Queue is empty");
            }
            
            pushToPop();
            return popStack.pop();
        }
        
        public int peek() {
            if (popStack.empty() && pushStack.empty()) {
                throw new RuntimeException("Queue is empty");
            }
            
            pushToPop();
            return popStack.peek();
        }
    }
    
    public static class TowQueueStack {
        
    }
    
    public static void main(String[] args) {
        TwoStacksQueue queue = new TwoStacksQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}
