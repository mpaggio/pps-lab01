package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoor;
    private static final int PIN_EXAMPLE = 0;
    private static final int WRONG_PIN_EXAMPLE = 10000;
    private static final int INITIAL_FAILED_ATTEMPTS = 0;

    @BeforeEach
    public void setUp() {
        this.smartDoor = new SmartDoorLockImpl();
    }

    @Test
    public void testSmartDoorInitialState() {
        assertFalse(this.smartDoor.isBlocked());
        assertFalse(this.smartDoor.isLocked());
        assertEquals(INITIAL_FAILED_ATTEMPTS, this.smartDoor.getFailedAttempts());
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

    @Test
    public void testSmartDoorDoesNotSetWrongPinWhenOpen() {
        assertThrows(IllegalArgumentException.class, () -> this.smartDoor.setPin(WRONG_PIN_EXAMPLE));
    }

    private void setPinAndLock() {
        this.smartDoor.setPin(PIN_EXAMPLE);
        this.smartDoor.lock();
    }

    @Test
    public void testSmartDoorUnlockWithCorrectPin() {
        setPinAndLock();
        this.smartDoor.unlock(PIN_EXAMPLE);
        assertFalse(this.smartDoor.isLocked());
    }

    @Test
    public void testSmartDoorDoesNotUnlockWithIncorrectPin() {
        setPinAndLock();
        this.smartDoor.unlock(WRONG_PIN_EXAMPLE);
        assertTrue(this.smartDoor.isLocked());
        assertEquals(INITIAL_FAILED_ATTEMPTS + 1, this.smartDoor.getFailedAttempts());
    }

    private void repeatUnlockWithWrongPinForMaxAttemptsTimes() {
        setPinAndLock();
        for (int i = 0; i < this.smartDoor.getMaxAttempts(); i++) {
            this.smartDoor.unlock(WRONG_PIN_EXAMPLE);
        }
    }

    @Test
    public void testSmartDoorBlockWithMaxNumberOfIncorrectPinUnlock() {
        repeatUnlockWithWrongPinForMaxAttemptsTimes();
        assertTrue(this.smartDoor.isBlocked());
    }

    @Test
    public void testSmartDoorResetWhenBlocked() {
        repeatUnlockWithWrongPinForMaxAttemptsTimes();
        this.smartDoor.reset();
        assertFalse(this.smartDoor.isLocked());
        assertFalse(this.smartDoor.isBlocked());
        assertEquals(INITIAL_FAILED_ATTEMPTS, this.smartDoor.getFailedAttempts());
    }

    @Test
    public void testSmartDoorUnlockWithCorrectPinAfterBeingBlocked() {
        repeatUnlockWithWrongPinForMaxAttemptsTimes();
        assertThrows(IllegalStateException.class, () -> this.smartDoor.unlock(PIN_EXAMPLE));
    }

    @Test
    public void testSmartDoorNotSetPinDuringLock() {
        setPinAndLock();
        assertThrows(IllegalStateException.class, () -> this.smartDoor.setPin(PIN_EXAMPLE));
    }

    @Test
    public void testSmartDoorNotSetPinDuringBlock() {
        repeatUnlockWithWrongPinForMaxAttemptsTimes();
        assertThrows(IllegalStateException.class, () -> this.smartDoor.setPin(PIN_EXAMPLE));
    }

    @Test
    public void testSmartDoorResetFailedAttemptsAfterCorrectPin() {
        setPinAndLock();
        for (int i = 0; i < this.smartDoor.getMaxAttempts() - 1; i++) {
            this.smartDoor.unlock(WRONG_PIN_EXAMPLE);
        }
        this.smartDoor.unlock(PIN_EXAMPLE);
        assertEquals(INITIAL_FAILED_ATTEMPTS, this.smartDoor.getFailedAttempts());
    }
}
