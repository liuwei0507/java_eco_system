package linkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 双向链表（带哨兵）
 */
public class DoublyLinkedListSentinel implements Iterable<Integer> {
    /**
     * 节点类
     */
    private static class Node {

        Node prev;//前一个节点
        int value;//节点值
        Node next;//后一个节点

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private final Node head;//头哨兵
    private final Node tail;//尾哨兵

    public DoublyLinkedListSentinel() {
        head = new Node(null, 666, null);
        tail = new Node(null, 888, null);
        head.next = tail;
        tail.prev = head;
    }

    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public void addFirst(int value) {
        insert(0, value);
    }

    /**
     * 删除第一个元素
     */
    public void removeFirst() {
        remove(0);
    }

    public void addLast(int value) {
        Node last = tail.prev;
        Node added = new Node(last, value, tail);
        last.next = added;
        tail.prev = added;
    }

    /**
     * 删除最后第一个元素
     */
    public void removeLast() {
        Node removed = tail.prev;
        if (removed == head) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", 0));
        }
        Node prev = removed.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    /**
     * 指定索引位置的元素
     *
     * @param index
     * @param value
     */
    public void insert(int index, int value) {
        Node prev = findNode(index - 1);//待插入位置的前一个节点
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        Node next = prev.next;//待插入位置
        Node inserted = new Node(prev, value, next);
        prev.next = inserted;
        next.prev = inserted;
    }


    public void remove(int index) {
        Node prev = findNode(index - 1);//找到前一个节点
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        Node removed = prev.next;//待删除节点
        if (removed == tail) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        Node next = removed.next;// 待删除节点的下一个节点

        prev.next = next;
        next.prev = prev;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

}
