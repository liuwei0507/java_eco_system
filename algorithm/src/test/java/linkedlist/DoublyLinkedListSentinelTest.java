package linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DoublyLinkedListSentinelTest {


    @Test
    public void testInsert() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();

        list.insert(0, 1);
        list.insert(0, 2);
        list.insert(0, 3);
        list.insert(0, 4);
        list.insert(0, 5);

        Assertions.assertIterableEquals(List.of(5, 4, 3, 2, 1), list);
    }

    @Test
    public void testRemove() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();

        list.insert(0, 1);
        list.insert(0, 2);
        list.insert(0, 3);
        list.insert(0, 4);
        list.insert(0, 5);

        list.remove(2);

        Assertions.assertIterableEquals(List.of(5, 4, 2, 1), list);
    }

    @Test
    public void testAddLast() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);


        Assertions.assertIterableEquals(List.of(1, 2, 3, 4), list);
    }

    @Test
    public void testRemoveLast() {
        DoublyLinkedListSentinel list = new DoublyLinkedListSentinel();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.removeLast();


        Assertions.assertIterableEquals(List.of(1, 2, 3), list);
    }

}