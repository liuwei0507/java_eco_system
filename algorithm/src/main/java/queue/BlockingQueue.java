package queue;

/**
 * 阻塞队列
 * 普通队列存在问题：
 * 1，没有考虑多线程情况下，线程安全问题
 * 2，队列为空，那么之前的实现会返回null，如果就是要返回一个元素呢，只有不断循环尝试
 * 3，队列满了，之前会直接返回false，如果要返回一个元素呢，只有不断循环尝试
 * <p>
 * 解决办法
 * 1 用锁保证线程安全
 * 2 用条件变量，让poll或者offer线程进入等待状态，而不是不断循环，让CPU空转
 */
public interface BlockingQueue<E> {// 阻塞队列

    void offer(E value) throws InterruptedException;

    boolean offer(E value, long timeout) throws InterruptedException;

    E poll() throws InterruptedException;
}
