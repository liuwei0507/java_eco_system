package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBlocking1Test {

    @Test
    public void offer() throws InterruptedException {
        ArrayBlocking1<Integer> queue = new ArrayBlocking1<>(5);

        Thread t1 = new Thread(() -> {
            try {
                queue.offer(1);
                queue.offer(2);
                queue.offer(3);
                queue.offer(4);
                queue.offer(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        t1.start();
        Thread.sleep(2000);
        assertFalse(queue.offer(6, 2000));
    }

}