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
public class A215_KthElement {
    public int findKthLargest(int[] nums, int k) {
        MinHeap heap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap.peek()) {
                heap.replace(nums[i]);
            }
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        int[] array = {3, 2, 1, 5, 6, 4};
        System.out.println(new A215_KthElement().findKthLargest(array, 2));
        System.out.println(new A215_KthElement().findKthLargest(array, 4));
    }
}
