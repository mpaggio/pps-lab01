package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private MinMaxStack stack;
    private static final int EXAMPLE_VALUE = 5;
    private final List<List<Integer>> listsOfValues = List.of(
        List.of(1,2,3,4,5),
        List.of(5,4,3,2,1),
        List.of(1,2,2,4,5),
        List.of(5,3,7,2,6),
        List.of(5,5,2,1,1),
        List.of(0,1,-1,-5,2)
    );

    @BeforeEach
    public void setUp() {
        this.stack = new MinMaxStackImpl();
    }

    @Test
    public void testInitiallyEmpty() {
        assertTrue(this.stack.isEmpty());
    }

    @Test
    public void testFirstPush() {
        this.stack.push(EXAMPLE_VALUE);
        assertEquals(EXAMPLE_VALUE, this.stack.peek());
        assertEquals(1, this.stack.size());
    }

    @Test
    public void testPopWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> this.stack.pop());
    }

    @Test
    public void testPopWithNonEmptyStack() {
        this.stack.push(EXAMPLE_VALUE);
        assertEquals(EXAMPLE_VALUE, this.stack.pop());
        assertEquals(0, this.stack.size());
        assertTrue(this.stack.isEmpty());
    }

    @Test
    public void testGetMinWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> this.stack.getMin());
    }

    @Test
    public void testGetMaxWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> this.stack.getMax());
    }

    @Test
    public void testPeekWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> this.stack.peek());
    }

    private void pushValues(final List<Integer> values) {
        for (Integer value : values) {
            this.stack.push(value);
        }
    }

    @Test
    public void testGetMin() {
        for (List<Integer> values : listsOfValues) {
            pushValues(values);
            assertEquals(Collections.min(values), this.stack.getMin());
            for (int i = 0; i < values.size(); i++) {
                this.stack.pop();
            }
        }
    }

    @Test
    public void testGetMax() {
        for (List<Integer> values : listsOfValues) {
            pushValues(values);
            assertEquals(Collections.max(values), this.stack.getMax());
            for (int i = 0; i < values.size(); i++) {
                this.stack.pop();
            }
        }
    }
}