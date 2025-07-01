package queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlocking1<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    public ArrayBlocking1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition headWaits = lock.newCondition();
    private Condition tailWaits = lock.newCondition();

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length;
    }

    @Override
    public void offer(E value) throws InterruptedException { // poll，等待队列非空
        lock.lockInterruptibly();
        try {
            while (isFull()) { // 队列已满，使用while，防止虚假唤醒
                tailWaits.await();
            }
            array[tail] = value;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();//唤醒poll的等待队列
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E value, long timeout) throws InterruptedException { // timeout单位毫秒
        lock.lockInterruptibly();
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isFull()) { // 队列已满，使用while，防止虚假唤醒
                if (t <= 0) {
                    return false;
                }
                t = tailWaits.awaitNanos(t); // 最多等待多少纳秒， 返回值，代表剩余时间
            }
            array[tail] = value;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();//唤醒poll的等待队列
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            E value = array[head];
            array[head] = null;// help GC
            if (++head == array.length) {
                head = 0;
            }
            size--;
            tailWaits.signal();
            return value;
        } finally {
            lock.unlock();
        }
    }
}
