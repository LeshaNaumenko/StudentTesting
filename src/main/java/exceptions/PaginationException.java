package exceptions;

/**
 * This class represents a pagination exception.
 *
 * @author Alex Naumenko
 */
public class PaginationException extends Exception {

    /**
     * Default constructor.
     */
    public PaginationException() {
        super();
    }

    /**
     * Constructs a PaginationException with the given detail message.
     *
     * @param message The detail message of the PaginationException.
     */
    public PaginationException(String message) {
        super(message);
    }

    /**
     * Constructs a PaginationException with the given detail message and root cause.
     *
     * @param message The detail message of the PaginationException.
     * @param cause The root cause of the PaginationException.
     */
    public PaginationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a PaginationException with the given root cause.
     *
     * @param cause The root cause of the PaginationException.
     */
    public PaginationException(Throwable cause) {
        super(cause);
    }
}
