package heap;

import java.util.Arrays;

/**
 * 堆排序
 */
public class A_HeapSort {
    public static void main(String[] args) {
        int[] array = {1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        /**
         * 1，heapify创建大顶堆
         * 2， 将堆顶元素与堆底元素交换（最大元素交换到堆底），缩小并调整堆
         * 3， 重复步骤2，直到堆中元素为1
         */
        MaxHeap heap = new MaxHeap(array);

        while (heap.size > 1) {
            heap.swap(0, heap.size - 1);
            heap.size--;
            heap.down(0);
        }
        System.out.println(Arrays.toString(heap.array));
    }

}
