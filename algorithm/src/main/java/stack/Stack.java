package stack;

public interface Stack<E> {
    /**
     * 向栈顶压入元素
     *
     * @param value 待压入的值
     * @return 压入成功返回true，否则返回false
     */
    boolean push(E value);

    /**
     * 弹出栈顶元素
     *
     * @return 弹出的元素，如果栈为空则返回null
     */

    E pop();

    /**
     * 获取栈顶元素，不弹出
     *
     * @return 栈顶元素，如果栈为空则返回null
     */
    E peek();

    /**
     * 判断栈是否为空
     *
     * @return 栈为空返回true，否则返回false
     */
    boolean isEmpty();

    /**
     * 判断栈是否已满
     *
     * @return 栈已满返回true，否则返回false
     */
    boolean isFull();

}
