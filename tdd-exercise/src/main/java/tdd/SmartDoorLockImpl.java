package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean isLocked = false;
    private int pin = -1;
    private int failedAttempts = 0;

    @Override
    public void setPin(int pin) {
        if (pin < 0 || pin > 9999) {
            throw new IllegalArgumentException("PIN must be a 4-digit value! Illegal PIN entered.");
        }
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin == pin) {
            this.isLocked = false;
        } else {
            this.failedAttempts++;
        }
    }

    @Override
    public void lock() {
        if (this.pin == -1) {
            throw new IllegalStateException("Door locked succesfully, but no pin ");
        }
        this.isLocked = true;
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {

    }
}
