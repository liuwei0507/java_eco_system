package linkedlist;

import java.util.Iterator;

/**
 * 带哨兵的双向环形链表
 */
public class DoublyCircleLinkedListSentinel implements Iterable<Integer> {

    private static class Node {
        int value;
        Node prev;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    private Node sentinel = new Node(null, -1, null);

    public DoublyCircleLinkedListSentinel() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(int value) {
        Node a = sentinel;
        Node b = sentinel.next;
        Node added = new Node(a, value, b);
        a.next = added;
        a.prev = added;
    }

    public void removeFirst() {
        Node removed = sentinel.next;
        if (removed == sentinel) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", 0));
        }
        Node a = sentinel;
        Node b = removed.next;
        a.next = b;
        b.prev = a;
    }

    public void addLast(int value) {
        Node a = sentinel.prev;
        Node b = sentinel;

        Node added = new Node(a, value, b);
        a.next = added;
        b.prev = added;
    }

    public void removeLast() {
        Node removed = sentinel.prev;
        if (removed == sentinel) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", 0));
        }
        Node a = removed.prev;
        Node b = sentinel;
        a.next = b;
        b.prev = a;

    }

    /**
     * 根据指定值删除节点
     *
     * @param value
     */
    public void removeByValue(int value) {
        Node removed = findByValue(value);
        if (removed == null) {
            return;
        }
        Node a = removed.prev;
        Node b = removed.next;
        a.next = b;
        b.prev = a;

    }

    private Node findByValue(int value) {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;
    }
}

