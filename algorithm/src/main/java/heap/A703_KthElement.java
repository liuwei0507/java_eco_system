package heap;

/**
 * 数组中第k个最大元素
 * <p>
 * 1 向小顶堆放入前k个元素
 * 2 剩余元素
 * 若 <= 堆顶元素，则略过
 * 若 > 堆顶元素，则将堆顶元素弹出，将当前元素放入堆中
 * 3 这样小顶堆始终保留的是目前位置，前k个最大的元素
 * 4 返回堆顶元素
 */
public class A703_KthElement {
    private MinHeap heap;

    public A703_KthElement(int k, int[] nums) {
        heap = new MinHeap(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (!heap.isFull()) {
            heap.offer(val);
        } else if (val > heap.peek()) {
            heap.replace(val);
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 8, 2};
        A703_KthElement test = new A703_KthElement(2, array);

        test.add(3);
        test.add(3);
        test.add(3);
        test.add(3);
        test.add(3);

    }
}
