package array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer> {
    private int size = 0; // 逻辑大小
    private int capacity = 8;// 容量
    private int[] array = {}; // 初始化容器

    /**
     * 向最后添加元素
     *
     * @param element 待添加元素
     */
    public void addLast(int element) {
//        array[size] = element;
//        size++;
        add(size, element);
    }

    /**
     * 【0..size】添加元素
     *
     * @param index   索引位置
     * @param element 待添加元素
     */
    public void add(int index, int element) {
        //容量检查
        checkAndGrow();
        //添加逻辑
        if (index >= 0 && index < size) {
            //往后移动，空出待插入位置
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[size] = element;
        size++;
    }

    private void checkAndGrow() {
        if (size == 0) {
            array = new int[capacity];
        } else if (size == capacity) {
            // 进行扩容 1.5倍 1.618倍 2倍
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            // 旧数组复制到新数组
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }


    public int remove(int index) {
        int removed = array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return removed;
    }

    public int get(int index) {
        return array[index];
    }

    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept(array[i]);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() { // 有没有下一个元素
                return i < size;
            }

            @Override
            public Integer next() { // 返回当前元素，并移动到下一个元素
                return array[i++];
            }
        };
    }

    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }
}
