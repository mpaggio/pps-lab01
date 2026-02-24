package tdd;

public class CircularQueueImpl implements CircularQueue {
    private final int[] queue = new int[QUEUE_SIZE];
    private int frontIndex = 0;
    private int size = 0;
    private static final int QUEUE_SIZE = 5;

    private void updateFrontIndex() {
        this.frontIndex = (this.frontIndex + 1) % QUEUE_SIZE;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void throwExceptionIfQueueEmpty(final String action) {
        if (this.size == 0) {
            throw new IllegalStateException("Trying to " + action + " on an empty queue!");
        }
    }

    @Override
    public void enqueue(int value) {
        final int rearIndex = (this.frontIndex + size) % QUEUE_SIZE;
        if (this.size < QUEUE_SIZE) {
            this.queue[rearIndex] = value;
            this.size++;
        } else {
            this.queue[this.frontIndex] = value;
            updateFrontIndex();
        }
    }

    @Override
    public int dequeue() {
        throwExceptionIfQueueEmpty("dequeue");
        final int dequeuedValue = this.queue[this.frontIndex];
        updateFrontIndex();
        this.size--;
        return dequeuedValue;
    }

    @Override
    public int peek() {
        throwExceptionIfQueueEmpty("peek");
        return this.queue[this.frontIndex];
    }

    @Override
    public int getFixedSize() {
        return QUEUE_SIZE;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
