package queue;

public interface Queue<E> {
    /**
     * 向队尾插入值
     *
     * @param value 插入的值
     * @return 插入成功返回true，否则返回false
     */
    boolean offer(E value);

    /**
     * 从队列头获取值，并移除
     *
     * @return 队列头值，如果队列为空则返回null
     */
    E poll();

    /**
     * 获取队列头值，但不移除
     *
     * @return 队列头值，如果队列为空则返回null
     */
    E peek();

    /**
     * 判断队列是否为空
     *
     * @return 队列为空返回true，否则返回false
     */
    boolean isEmpty();

    /**
     * 判断队列是否已满
     * @return 队列已满返回true，否则返回false
     */
    boolean isFull();
}
