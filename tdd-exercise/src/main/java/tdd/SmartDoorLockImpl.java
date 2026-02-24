package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private boolean isLocked = false;
    private boolean isBlocked = false;
    private int pin = INITIAL_PIN_VALUE;
    private int failedAttempts = INITIAL_FAILED_ATTEMPTS;
    private static final int INITIAL_PIN_VALUE = -1;
    private static final int INITIAL_FAILED_ATTEMPTS = 0;
    private static final int MAX_PIN_VALUE = 9999;
    private static final int MIN_PIN_VALUE = 0;
    private static final int MAX_ATTEMPTS = 5;

    @Override
    public void setPin(int pin) {
        if (pin < MIN_PIN_VALUE || pin > MAX_PIN_VALUE) {
            throw new IllegalArgumentException("PIN must be a 4-digit value! Illegal PIN entered.");
        }
        if (this.isLocked || this.isBlocked) {
            throw new IllegalStateException("PIN can't be set while door is locked or blocked.");
        }
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.isBlocked) {
            throw new IllegalStateException("Tried entering PIN, but door lock is blocked! Please reset the door lock and insert a new PIN");
        }
        if (this.pin == pin) {
            this.isLocked = false;
            this.failedAttempts = 0;
        } else {
            this.failedAttempts++;
            if (this.failedAttempts >= MAX_ATTEMPTS) {
                this.isBlocked = true;
            }
        }
    }

    @Override
    public void lock() {
        if (this.pin == INITIAL_PIN_VALUE) {
            throw new IllegalStateException("Trying to lock the door with no PIN set! Please, set a PIN and retry.");
        }
        this.isLocked = true;
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isBlocked() {
        return this.isBlocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {
        this.isLocked = false;
        this.isBlocked = false;
        this.failedAttempts = INITIAL_FAILED_ATTEMPTS;
        this.pin = INITIAL_PIN_VALUE;
    }
}
