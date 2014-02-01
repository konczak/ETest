package pl.konczak.etest.core;

public class Validate
        extends org.apache.commons.lang.Validate {

    private static final String TESTED_VALUE_NULL = "Tested value cannot be null";
    private static final String MIN_VALUE_NULL = "Min value cannot be null";
    private static final String MAX_VALUE_NULL = "Max value cannot be null";
    private static final String BETWEEN_FAILED = "Tested <%d> is not between [<%d> ; <%d>]";

    public Validate() {
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Validate that the argument is between specified range (including min and max).
     *
     * @param tested
     * @param min
     * @param max
     */
    public static void isBetween(Integer tested, Integer min, Integer max) {
        isBetween(tested, min, max, String.format(BETWEEN_FAILED, tested, min, max));
    }

    public static void isBetween(Integer tested, Integer min, Integer max, String message) {
        Validate.notNull(tested, TESTED_VALUE_NULL);
        Validate.notNull(min, MIN_VALUE_NULL);
        Validate.notNull(max, MAX_VALUE_NULL);

        boolean isLessThenMin = tested < min;
        boolean isMoreThenMax = tested > max;

        if (isLessThenMin || isMoreThenMax) {
            throw new IllegalArgumentException(message);
        }
    }
}
