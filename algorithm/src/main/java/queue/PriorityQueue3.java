package queue;

/**
 * 优先级队列：基于大顶堆实现
 *
 * @param <E>
 */
public class PriorityQueue3<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size;

    public PriorityQueue3(int capacity) {
        array = new Priority[capacity];
    }


    /**
     * 入堆新元素，加入到数组末尾
     * 不断比较新元素和父节点的优先级，如果新元素优先级高，则交换位置
     * 直到新元素优先级小于等于父节点的优先级
     *
     * @param value 插入的值
     * @return
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && value.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = value;
        return true;
    }

    /**
     * 1， 交换堆顶和尾部元素，让尾部元素出队
     * 2， 堆顶元素和子节点中优先级最高的元素交换位置
     * 3， 直到堆顶元素小于等于子节点的优先级
     * 4， 循环结束后，堆顶元素就是优先级最高的元素
     *
     * @return
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        swap(0, size - 1);
        size--;
        Priority e = array[size];
        array[size] = null;
        //下潜
        down(0);
        return (E) e;
    }

    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;// 假设父节点元素优先级最高
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    private void swap(int i, int j) {
        Priority temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
