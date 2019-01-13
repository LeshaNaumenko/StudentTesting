package exceptions;

/**
 * This class represents a DAO exception. It should wrap any exception of DAO classes, such as SQLExceptions etc.
 *
 * @author Alex Naumenko
 */
public class DAOException extends Exception {
    public DAOException() {
        super();
    }


    /**
     * Constructs a DAOException with the given detail message.
     *
     * @param message The detail message of the DAOException.
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructs a DAOException with the given detail message and root cause.
     *
     * @param message The detail message of the DAOException.
     * @param cause The root cause of the DAOException.
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a DAOException with the given root cause.
     *
     * @param cause The root cause of the DAOException.
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}
