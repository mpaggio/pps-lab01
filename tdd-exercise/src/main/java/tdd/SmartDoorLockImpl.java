package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private State lockStatus = State.OPEN;
    private int pin = INITIAL_PIN_VALUE;
    private int failedAttempts = INITIAL_FAILED_ATTEMPTS;
    private static final int INITIAL_PIN_VALUE = -1;
    private static final int INITIAL_FAILED_ATTEMPTS = 0;
    private static final int MAX_PIN_VALUE = 9999;
    private static final int MIN_PIN_VALUE = 0;
    private static final int MAX_ATTEMPTS = 5;

    private enum State {
        OPEN,
        LOCKED,
        BLOCKED
    }

    @Override
    public void setPin(int pin) {
        if (pin < MIN_PIN_VALUE || pin > MAX_PIN_VALUE) {
            throw new IllegalArgumentException("PIN must be a 4-digit value! Illegal PIN entered.");
        }
        if (lockStatus.equals(State.LOCKED) || lockStatus.equals(State.BLOCKED)) {
            throw new IllegalStateException("PIN can't be set while door is locked or blocked.");
        }
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (lockStatus.equals(State.BLOCKED)) {
            throw new IllegalStateException("Tried entering PIN, but door lock is blocked! Please reset the door lock and insert a new PIN");
        }
        if (this.pin == pin) {
            this.lockStatus = State.OPEN;
            this.failedAttempts = 0;
        } else {
            this.failedAttempts++;
            if (this.failedAttempts >= MAX_ATTEMPTS) {
                this.lockStatus = State.BLOCKED;
            }
        }
    }

    @Override
    public void lock() {
        if (this.pin == INITIAL_PIN_VALUE) {
            throw new IllegalStateException("Trying to lock the door with no PIN set! Please, set a PIN and retry.");
        }
        this.lockStatus = State.LOCKED;
    }

    @Override
    public boolean isLocked() {
        return this.lockStatus.equals(State.LOCKED);
    }

    @Override
    public boolean isBlocked() {
        return this.lockStatus.equals(State.BLOCKED);
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
        this.lockStatus = State.OPEN;
        this.failedAttempts = INITIAL_FAILED_ATTEMPTS;
        this.pin = INITIAL_PIN_VALUE;
    }
}
