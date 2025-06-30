package queue;

import java.util.Iterator;

/**
 * 双向环形链表实现双端队列
 *
 * @param <E>
 */
public class LinkedListDeque<E> implements Deque<E>, Iterable<E> {

    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E value, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private final int capacity;
    private int size;
    private final Node<E> sentinel = new Node<>(null, null, null);

    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public boolean offerFirst(E value) {
        if (isFull()) {
            return false;
        }
        // 上一个节点 a ， 下一个节点 b
        // 添加在头部，则  a = sentinel, b = sentinel.next
        Node<E> a = sentinel;
        Node<E> b = sentinel.next;
        Node<E> added = new Node<>(a, value, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    @Override
    public boolean offerLast(E value) {
        if (isFull()) {
            return false;
        }
        // 上一个节点 a ， 下一个节点 b
        // 添加在尾部，则  a = sentinel.prev, b = sentinel
        Node<E> a = sentinel.prev;
        Node<E> b = sentinel;
        Node<E> added = new Node<>(a, value, b);
        a.next = added;
        b.prev = added;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        // 被移除元素的上一个节点a, 下一个节点 b
        //从头部移除 a = sentinel, removed = sentinel.next, b = sentinel.next.next
        Node<E> a = sentinel;
        Node<E> removed = sentinel.next;
        Node<E> b = removed.next;
        a.next = b;
        b.prev = a;
        size--;
        return removed.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        // 被移除元素的上一个节点a, 下一个节点 b
        //从头部移除 a = sentinel.prev.prev, removed = sentinel.prev, b = sentinel
        Node<E> b = sentinel;
        Node<E> removed = sentinel.prev;
        Node<E> a = removed.prev;
        a.next = b;
        b.prev = a;
        return removed.value;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.prev.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}
