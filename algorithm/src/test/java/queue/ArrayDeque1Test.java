package queue;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDeque1Test {
    @Test
    void offerFirst() {
        ArrayDeque1<Object> deque = new ArrayDeque1<>(5);
        deque.offerFirst(1);
        deque.offerFirst(2);
        deque.offerFirst(3);
        deque.offerLast(4);
        deque.offerLast(5);

        assertIterableEquals(List.of(3, 2, 1, 4, 5), deque);
    }
}