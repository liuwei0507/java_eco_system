package heap;

import java.util.Arrays;

/**
 * 大顶堆和小顶堆
 * <p>
 * 建堆
 */
public class Heap {
    int[] array;
    int size;
    boolean maxHeap;

    public Heap(int capacity, boolean maxHeap) {
        array = new int[capacity];
        this.maxHeap = maxHeap;
    }

    public Heap(int[] array) {
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
            //扩容
            growCapacity();
        }
        up(offered);
        size++;
        return true;
    }

    private void growCapacity() {
        // 进行扩容 1.5倍 1.618倍 2倍
        int capacity = size + (size >> 1);// 容量扩容1.5倍
        int[] newArray = new int[capacity];
        // 旧数组复制到新数组
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
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
            boolean cmp = maxHeap ? offered > array[parent] : offered < array[parent];
            if (cmp) {
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
    public void down(int parent) {
        int leftChild = parent * 2 + 1;
        int rightChild = leftChild + 1;
        int maxOrMin = parent;
        if (leftChild < size && (maxHeap ? array[leftChild] > array[maxOrMin] : array[leftChild] < array[maxOrMin])) {
            maxOrMin = leftChild;
        }
        if (rightChild < size && (maxHeap ? array[rightChild] > array[maxOrMin] : array[rightChild] < array[maxOrMin])) {
            maxOrMin = rightChild;
        }

        if (maxOrMin != parent) { // 找到了更大的孩子
            swap(parent, maxOrMin);
            down(maxOrMin);
        }
    }

    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        Heap maxHeap = new Heap(array);
        System.out.println(Arrays.toString(maxHeap.array));
    }
}
