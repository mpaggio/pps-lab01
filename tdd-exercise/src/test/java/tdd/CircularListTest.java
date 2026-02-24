package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private CircularQueue queue;
    private static final int VALUE_EXAMPLE = 5;

    @BeforeEach
    public void setUp() {
        this.queue = new CircularQueueImpl();
    }

    @Test
    public void testInitiallyEmpty() {
        assertTrue(this.queue.isEmpty());
    }

    @Test
    public void testFirstEnqueue() {
        this.queue.enqueue(VALUE_EXAMPLE);
        assertEquals(VALUE_EXAMPLE, this.queue.peek());
        assertFalse(this.queue.isEmpty());
    }

    @Test
    public void testFirstDequeue() {
        this.queue.enqueue(VALUE_EXAMPLE);
        assertEquals(VALUE_EXAMPLE, this.queue.dequeue());
        assertTrue(this.queue.isEmpty());
    }

    private void fillQueueWithValues() {
        for (int i = 0; i < this.queue.getFixedSize(); i++) {
            this.queue.enqueue(i);
        }
    }

    @Test
    public void testSingleOverwrite() {
        fillQueueWithValues();
        this.queue.enqueue(VALUE_EXAMPLE);
        assertEquals(1, this.queue.dequeue());
    }

    @Test
    public void testDequeueWithEmptyQueue() {
        assertThrows(IllegalStateException.class, this.queue::dequeue);
    }

    @Test
    public void testPeekWithEmptyQueue() {
        assertThrows(IllegalStateException.class, this.queue::peek);
    }

    @Test
    public void testMultipleOverwrites() {
        fillQueueWithValues();
        this.queue.enqueue(VALUE_EXAMPLE);
        this.queue.enqueue(VALUE_EXAMPLE + 1);
        final List<Integer> expectedDequeuedValues = List.of(2,3,4,5,6);
        for (Integer value : expectedDequeuedValues) {
            assertEquals(value, queue.dequeue());
        }
    }

    @Test
    public void testQueueSize() {
        fillQueueWithValues();
        assertEquals(this.queue.getFixedSize(), this.queue.getSize());
    }
}
