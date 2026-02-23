package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean locked = false;
    private Integer pin;

    @Override
    public void setPin(int pin) {
        
    }

    @Override
    public void unlock(int pin) {

    }

    @Override
    public void lock() {
        this.locked = true;
        if (this.pin == null) {
            throw new IllegalStateException("Door locked succesfully, but no pin ");
        }
    }

    @Override
    public boolean isLocked() {
        return this.locked;
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
