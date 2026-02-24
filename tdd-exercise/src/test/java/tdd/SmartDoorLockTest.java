package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoor;

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
    public void testSmartDoorLockWithoutPin() {
        assertThrows(IllegalStateException.class, this.smartDoor::lock);
        assertTrue(this.smartDoor.isLocked());
    }
}
