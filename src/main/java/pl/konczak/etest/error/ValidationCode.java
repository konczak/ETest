package pl.konczak.etest.error;

public enum ValidationCode
        implements ErrorCode {

    REQUIRED(1),
    EMPTY(2),
    VALUE_MIN(3),
    VALUE_MAX(4),
    VALUE_TO_LONG(5),
    VALUE_TO_SHORT(6),
    PATTERN_NOT_MATCH(7),
    ALREADY_TAKEN(8),
    NOT_EXISTS(9);
    private int number;

    private ValidationCode(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
