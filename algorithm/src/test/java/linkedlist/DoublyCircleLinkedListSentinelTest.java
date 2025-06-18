package linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoublyCircleLinkedListSentinelTest {

    @Test
    public void testAddFirst() {
        DoublyCircleLinkedListSentinel list = new DoublyCircleLinkedListSentinel();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);

        Assertions.assertIterableEquals(List.of(4, 3, 2, 1), list);
    }

    @Test
    public void testAddLast() {
        DoublyCircleLinkedListSentinel list = new DoublyCircleLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        Assertions.assertIterableEquals(List.of(1, 2, 3, 4), list);
    }

    @Test
    public void testRemoveFirst() {
        DoublyCircleLinkedListSentinel list = new DoublyCircleLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.removeFirst();
        list.removeFirst();

        Assertions.assertIterableEquals(List.of(3, 4), list);
    }

    @Test
    public void testRemoveLast() {
        DoublyCircleLinkedListSentinel list = new DoublyCircleLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.removeLast();
        list.removeLast();

        Assertions.assertIterableEquals(List.of(1, 2), list);
    }

    @Test
    public void testRemoveByValue() {
        DoublyCircleLinkedListSentinel list = new DoublyCircleLinkedListSentinel();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        list.removeByValue(3);

        Assertions.assertIterableEquals(List.of(1, 2, 4), list);
    }
}