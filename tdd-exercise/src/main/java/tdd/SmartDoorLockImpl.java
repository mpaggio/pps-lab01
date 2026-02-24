package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean isLocked = false;
    private int pin = -1;

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {

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
        return 0;
    }

    @Override
    public void reset() {

    }
}
