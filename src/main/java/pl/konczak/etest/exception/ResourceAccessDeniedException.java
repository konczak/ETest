package pl.konczak.etest.exception;

public class ResourceAccessDeniedException
        extends RuntimeException {

    /**
     * Constructs an instance of
     * <code>ResourceNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ResourceAccessDeniedException(String msg) {
        super(msg);
    }
}
