package tdd;

import java.util.EmptyStackException;
import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {
    private final Stack<Integer> mainStack = new Stack<>();
    private final Stack<Integer> minStack = new Stack<>();
    private final Stack<Integer> maxStack = new Stack<>();

    @Override
    public void push(int value) {
        if (this.minStack.empty() || this.minStack.peek() >= value) {
            this.minStack.push(value);
        }
        if (this.maxStack.empty() || this.maxStack.peek() <= value) {
            this.maxStack.push(value);
        }
        this.mainStack.push(value);
    }

    @Override
    public int pop() {
        try {
            if (this.mainStack.peek() == this.minStack.peek()) {
                this.minStack.pop();
            }
            if (this.mainStack.peek() == this.maxStack.peek()) {
                this.maxStack.pop();
            }
            return this.mainStack.pop();
        } catch (EmptyStackException e) {
            throw new IllegalStateException("Trying to pop on an empty stack!");
        }
    }

    @Override
    public int peek() {
        throwExceptionIfStackIsEmpty("peek");
        return this.mainStack.peek();
    }

    private void throwExceptionIfStackIsEmpty(final String action) {
        if (this.mainStack.isEmpty()) {
            throw new IllegalStateException("Trying to " + action + ", but stack is empty!");
        }
    }

    @Override
    public int getMin() {
        throwExceptionIfStackIsEmpty("get min");
        return this.minStack.peek();
    }

    @Override
    public int getMax() {
        throwExceptionIfStackIsEmpty("get max");
        return this.maxStack.peek();
    }

    @Override
    public boolean isEmpty() {
        return this.mainStack.isEmpty();
    }

    @Override
    public int size() {
        return this.mainStack.size();
    }
}
