package linkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 单向链表
 */
public class SinglyLinkedList implements Iterable<Integer> {
    Node head = null;// 头指针

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head;

            @Override
            public boolean hasNext() { // 有没有下一个元素
                return p != null;
            }

            @Override
            public Integer next() {// 返回当前元素，并移动到下一个元素
                int v = p.value;
                p = p.next;
                return v;
            }
        };
    }

    /**
     * 节点类
     */
    private static class Node {
        int value; //节点值
        Node next; //下一个节点

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int value) {
        //1.链表为空
//        head = new Node(value, null);
        //2. 链表非空
        head = new Node(value, head);
    }

    public void loop1(Consumer<Integer> consumer) {
        Node p = head;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    private Node findLast() {
        if (head == null) {//链表为空
            return null;
        }
        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    public void addLast(int value) {
        Node last = findLast();
        if (last == null) {
            addFirst(value);
            return;
        }
        last.next = new Node(value, null);
    }

    private Node findNode(int index) {
        Node p = head;
        for (int i = 0; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;//没有找到
    }

    /**
     * 获取指定索引位置的元素
     *
     * @param index
     * @return
     */
    public int get(int index) {
        Node p = findNode(index);
        if (p == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        return p.value;
    }

    /**
     * 指定索引位置的元素
     *
     * @param index
     * @param value
     */
    public void insert(int index, int value) {
        if (index == 0) {
            addFirst(value);
            return;
        }
        Node prev = findNode(index - 1);//待插入位置的前一个节点
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        prev.next = new Node(value, prev.next);
    }

    /**
     * 删除第一个元素
     */
    public void removeFirst() {
        if (head == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", 0));
        }
        head = head.next;
    }

    public void remove(int index) {
        if (index == 0) {
            removeFirst();
            return;
        }
        Node prev = findNode(index - 1);//找到前一个节点
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        Node removed = prev.next;//被删除的节点
        if (removed == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
        }
        prev.next = removed.next;
    }

}
