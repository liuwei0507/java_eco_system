package queue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListDequeTest {

    @Test
    void offerFirst() {
        LinkedListDeque<Object> deque = new LinkedListDeque<>(5);
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerLast(5);

        assertIterableEquals(List.of(3, 2, 1, 4, 5), deque);
    }

    @Test
    void offerLast() {
    }

    @Test
    void pollFirst() {
    }

    @Test
    void pollLast() {
    }

    @Test
    void peekFirst() {
    }

    @Test
    void peekLast() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void isFull() {
    }
}