package heap;

import java.util.PriorityQueue;

/**
 * 求数据流中的中位数
 */
public class A295_MedianFinder_2 {
    /**
     * 使用一个大顶堆和一个小顶堆来实现
     * 1. 大顶堆存放较小的一半数据
     * 2. 小顶堆存放较大的一半数据
     * <p>
     * 为了保证两边数据量的平衡
     * 两边的个数一样时，左边个数加一
     * 两边的个数不一样时，右边个数加一
     * <p>
     * 但是随便一个数能直接加入吗？
     * 左边个数加一时，把新元素加在右边，弹出右边最小的加入左边
     * 右边个数加一时，把新元素加在左边，弹出左边最大的加入右边
     */

//    private final PriorityQueue<Integer> queue = new PriorityQueue<>();// 默认PriorityQueue是一个小顶堆
    // 大顶堆
    private final PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    //小顶堆
    private final PriorityQueue<Integer> right = new PriorityQueue<>();

    public void addNum(int num) {
        if (left.size() == right.size()) {
            right.offer(num);
            int poll = right.poll();
            left.offer(poll);
        } else {
            left.offer(num);
            int poll = left.poll();
            right.offer(poll);
        }
    }


    /**
     * 获取中位数
     * 1. 两边个数一样时，左右各取堆顶元素求平均
     * 2. 左边个数多一个，取左边堆顶元素
     *
     * @return
     */
    public double findMedian() {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }

    public static void main(String[] args) {
        A295_MedianFinder_2 test = new A295_MedianFinder_2();
        test.addNum(1);
        test.addNum(2);
        test.addNum(3);
        test.addNum(7);
        test.addNum(8);
        test.addNum(9);
        test.addNum(9);
        test.addNum(9);
        test.addNum(9);
        System.out.println(test.findMedian());
        test.addNum(4);
        System.out.println(test.findMedian());
        test.addNum(10);
        System.out.println(test.findMedian());
    }
}
