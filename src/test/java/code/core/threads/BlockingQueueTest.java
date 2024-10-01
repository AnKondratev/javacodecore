package code.core.threads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class BlockingQueueTest {
    private BlockingQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new BlockingQueue<>(5);
    }

    @Test
    public void testEnqueueAndDequeue() throws InterruptedException {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(3, queue.size());

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());

        assertEquals(0, queue.size());
    }

    @Test
    public void testDequeueOnEmptyQueue() throws InterruptedException {
        Thread thread = new Thread(() -> assertThrows(InterruptedException.class, queue::dequeue));

        thread.start();
        Thread.sleep(100);
        thread.interrupt();
        thread.join();
    }

    @Test
    public void testEnqueueWhenFull() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }

        CountDownLatch latch = new CountDownLatch(1);

        Thread producer = new Thread(() -> {
            try {
                queue.enqueue(6);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        });

        producer.start();

        assertEquals(5, queue.size());

        queue.dequeue();

        latch.await();
        assertEquals(5, queue.size());
    }


    @Test
    public void testSize() throws InterruptedException {
        assertEquals(0, queue.size());
        queue.enqueue(1);
        assertEquals(1, queue.size());
        queue.enqueue(2);
        assertEquals(2, queue.size());
        queue.enqueue(3);
        assertEquals(3, queue.size());
        queue.dequeue();
        assertEquals(2, queue.size());
        queue.dequeue();
        assertEquals(1, queue.size());
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    public void testConcurrentEnqueueAndDequeue() throws InterruptedException {
        final int numberOfProducers = 5;
        final int numberOfConsumers = 5;
        final int itemsPerProducer = 10;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(numberOfProducers + numberOfConsumers);

        for (int i = 0; i < numberOfProducers; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();
                    for (int j = 0; j < itemsPerProducer; j++) {
                        queue.enqueue(1);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }

        for (int i = 0; i < numberOfConsumers; i++) {
            new Thread(() -> {
                try {
                    startLatch.await();
                    for (int j = 0; j < itemsPerProducer; j++) {
                        queue.dequeue();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }

        startLatch.countDown();
        endLatch.await();

        assertEquals(0, queue.size());
    }


}