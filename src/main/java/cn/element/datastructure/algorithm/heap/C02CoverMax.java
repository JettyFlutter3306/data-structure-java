package cn.element.datastructure.algorithm.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 使用堆计算重合在一起最多数量的线段数
 */
public class C02CoverMax {
    
    // 定义区间类
    public static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public static int maxCover(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }

        Arrays.sort(lines, Comparator.comparingInt(o -> o.end));

        // 定义一个小根堆,存放每一条线段结尾数值
        Queue<Integer> heap = new PriorityQueue<>();
        
        int ans = 0;
        for (Line line : lines) {
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }

            heap.add(line.end);
            ans = Math.max(ans, heap.size());
        }
        
        return ans;
    }

    public static void main(String[] args) {
        int[][] values = {
                {1, 10},
                {2, 6},
                {3, 6},
                {5, 7},
                {1, 3}
        };

        System.out.println(maxCover(values));
    }

}
