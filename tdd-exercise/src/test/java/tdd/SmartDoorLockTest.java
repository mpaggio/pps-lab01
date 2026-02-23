package tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    @Test
    public void isSmartDoorInitiallyUnlocked() {
        SmartDoorLock smartDoorLock = new SmartDoorLockImpl();
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    public void smartDoorLockWithoutPin() {
        SmartDoorLock smartDoorLock = new SmartDoorLockImpl();
        assertThrows(IllegalStateException.class, () -> smartDoorLock.lock());
        assertTrue(smartDoorLock.isLocked());
    }
}
