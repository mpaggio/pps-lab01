package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoor;
    private static final int PIN_EXAMPLE = 0000;

    @BeforeEach
    public void setUp() {
        this.smartDoor = new SmartDoorLockImpl();
    }

    @Test
    public void testSmartDoorInitiallyUnlocked() {
        assertFalse(this.smartDoor.isLocked());
    }

    @Test
    public void testSmartDoorInitiallyNotBlocked() {
        assertFalse(this.smartDoor.isBlocked());
    }

    @Test
    public void testSmartDoorNotLockWithoutPin() {
        assertThrows(IllegalStateException.class, this.smartDoor::lock);
        assertFalse(this.smartDoor.isLocked());
    }

    @Test
    public void testSmartDoorSetPinWhenOpen() {
        assertDoesNotThrow(() -> this.smartDoor.setPin(PIN_EXAMPLE));
    }

    @Test
    public void testSmartDoorLockWhenPinSet() {
        this.smartDoor.setPin(PIN_EXAMPLE);
        assertDoesNotThrow(this.smartDoor::lock);
        assertTrue(this.smartDoor.isLocked());
    }
}
