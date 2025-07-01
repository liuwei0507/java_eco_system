package queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 双锁实现阻塞队列
 *
 * @param <E>
 */
public class ArrayBlocking2<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private final AtomicInteger size = new AtomicInteger(); // 使用原子类，保证线程安全

    public ArrayBlocking2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();

    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();

    private boolean isEmpty() {
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }

    @Override
    public void offer(E value) throws InterruptedException { // poll，等待队列非空
        tailLock.lockInterruptibly();
        try {
            while (isFull()) { // 队列已满，使用while，防止虚假唤醒
                tailWaits.await();
            }
            array[tail] = value;
            if (++tail == array.length) {
                tail = 0;
            }
            size.getAndIncrement(); // size++
            /*
            1 读取成员变量size的值
            2 自增
            3 结果写回成员变量
             */
            // 唤醒poll的等待队列， headWaits 和 lock 需要配对使用， 造成死锁问题
//            headLock.lock();
//            try {
//                headWaits.signal();//唤醒poll的等待队列
//            } finally {
//                headLock.unlock();
//            }
        } finally {
            tailLock.unlock();
        }
        // 唤醒poll的等待队列， headWaits 和 lock 需要配对使用， 造成死锁问题---避免使用嵌套锁
        headLock.lock();
        try {
            headWaits.signal();//唤醒poll的等待队列
        } finally {
            headLock.unlock();
        }
    }

    @Override
    public boolean offer(E value, long timeout) throws InterruptedException { // timeout单位毫秒
        tailLock.lockInterruptibly();
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
            size.getAndIncrement();
            // 唤醒poll的等待队列， headWaits 和 lock 需要配对使用
//            headLock.lock();
//            try {
//                headWaits.signal();//唤醒poll的等待队列
//            } finally {
//                headLock.unlock();
//            }
//            return true;
        } finally {
            tailLock.unlock();
        }
        // 唤醒poll的等待队列， headWaits 和 lock 需要配对使用
        headLock.lock();
        try {
            headWaits.signal();//唤醒poll的等待队列
        } finally {
            headLock.unlock();
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        headLock.lockInterruptibly();
        E value;
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            value = array[head];
            array[head] = null;// help GC
            if (++head == array.length) {
                head = 0;
            }
            size.getAndDecrement();
            /*
                1 读取成员变量size的值
                2 自减
                3 结果写回成员变量
            */
//            tailLock.lock();
//            try {
//                tailWaits.signal();
//            } finally {
//                tailLock.unlock();
//            }
//            return value;
        } finally {
            headLock.unlock();
        }
        tailLock.lock();
        try {
            tailWaits.signal();
        } finally {
            tailLock.unlock();
        }
        return value;
    }

    public static void main(String[] args) {
        ArrayBlocking2<Integer> queue = new ArrayBlocking2<>(3);

        new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "poll_1").start();

        new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "poll_2").start();

        new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "offer_1").start();
    }
}
