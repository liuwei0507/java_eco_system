package linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SinglyLinkedListTest {

    @Test
    public void testAddFirst() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        list.loop1(value -> System.out.println(value));
        System.out.println("----------------");
        list.loop2(value -> System.out.println(value));
        System.out.println("----------------");
        for (Integer value : list) {
            System.out.println(value);
        }
    }

    @Test
    public void testAddLast() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        Assertions.assertIterableEquals(List.of(1, 2, 3, 4, 5), list);
    }

    @Test
    public void testGet() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        Assertions.assertEquals(4, list.get(3));
    }

    @Test
    public void testIndex() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.insert(3, 6);
        list.insert(0, 7);

        Assertions.assertIterableEquals(List.of(7, 1, 2, 3, 6, 4, 5), list);
    }

    @Test
    public void testRemoveFirst() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        list.removeFirst();

        Assertions.assertIterableEquals(List.of(2, 3, 4, 5), list);
    }

    @Test
    public void testRemove() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        list.remove(0);
//        list.remove(1);
//        list.remove(5);
//        list.remove(4);

        Assertions.assertIterableEquals(List.of(2, 3, 4, 5), list);
    }
}