package org.codeart.datastructure.algorithm.list;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class C07TwoStacksImplementQueue {

    /**
     * 使用双栈实现队列
     */
    public static class TwoStacksQueue {
        public final Stack<Integer> pushStack;
        public final Stack<Integer> popStack;

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

    /**
     * 双队列实现栈
     */
    public static class TwoQueueStack {
        private Queue<Integer> queue;
        private Queue<Integer> helper;

        public TwoQueueStack() {
            queue = new LinkedList<>();
            helper = new LinkedList<>();
        }
        
        public void push(int i) {
            queue.offer(i);
        }
        
        public int pop() {
            while (queue.size() > 1) {
                helper.offer(queue.poll());
            }

            int ans = queue.poll();
            Queue<Integer> tmp = queue;
            queue = helper;
            helper = tmp;
            return ans;
        }
        
        public int peek() {
            while (queue.size() > 1) {
                helper.offer(queue.poll());
            }
            
            int ans = queue.poll();
            helper.offer(ans);
            Queue<Integer> tmp = queue;
            queue = helper;
            helper = tmp;
            return ans;
        }
        
        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
    
    public static void main(String[] args) {
        TwoStacksQueue queue = new TwoStacksQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        System.out.println("==========================");
        
        TwoQueueStack stack = new TwoQueueStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
