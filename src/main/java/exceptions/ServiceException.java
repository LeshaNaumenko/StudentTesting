package exceptions;

/**
 * This class represents a service exception.
 *
 * @author Alex Naumenko
 */
public class ServiceException extends Exception {

    /**
     * Default constructor.
     */
    public ServiceException() {
        super();
    }

    /**
     * Constructs a ServiceException with the given detail message.
     * @param message The detail message of the ServiceException.
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a ServiceException with the given detail message and root cause.
     * @param message The detail message of the ServiceException.
     * @param cause The root cause of the ServiceException.
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a ServiceException with the given root cause.
     * @param cause The root cause of the ServiceException.
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
