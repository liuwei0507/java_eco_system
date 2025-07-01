package heap;

import java.util.Arrays;

/**
 * 大顶堆
 * <p>
 * 建堆
 */
public class MaxHeap {
    int[] array;
    int size;

    public MaxHeap(int capacity) {
        array = new int[capacity];
    }

    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    /**
     * 获取堆顶元素
     */
    public int peek() {
        if (size == 0) {
            throw new IllegalArgumentException("堆为空");
        }
        return array[0];
    }

    /**
     * 删除堆顶元素
     *
     * @return
     */
    public int poll() {
        if (size == 0) {
            throw new IllegalArgumentException("堆为空");
        }
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    /**
     * 删除指定索引的元素
     *
     * @param index
     * @return
     */
    public int poll(int index) {
        int deleted = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return deleted;
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced
     */
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 向堆尾部添加元素
     *
     * @param offered
     */
    public boolean offer(int offered) {
        if (size == array.length) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    /**
     * 将offer元素上浮，直至offered小于父元素或到堆顶
     *
     * @param offered
     */
    private void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    //建堆
    private void heapify() {
        //1，如何找到最合这个非叶子节点  size/2-1
        for (int i = size / 2 - 1; i >= 0; i--) {
            //2，从后向前，堆每个节点执行下潜
            down(i);
        }
    }

    // 将parent索引出的元素下潜：与两个孩子较大者交换，直至没孩子或孩子没他大
    private void down(int parent) {
        int leftChild = parent * 2 + 1;
        int rightChild = leftChild + 1;
        int max = parent;
        if (leftChild < size && array[leftChild] > array[max]) {
            max = leftChild;
        }
        if (rightChild < size && array[rightChild] > array[max]) {
            max = rightChild;
        }
        if (max != parent) { // 找到了更大的孩子
            swap(parent, max);
            down(max);
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        MaxHeap maxHeap = new MaxHeap(array);
        System.out.println(Arrays.toString(maxHeap.array));
    }
}
