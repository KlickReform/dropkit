package de.klickreform.dropkit.exception;

/**
 * Exception to be thrown if a user tries to perform an action he is not allowed
 * to do.
 *
 * @author Benjamin Bestmann
 */
public class NotAllowedException extends ApiException {

    public NotAllowedException(String message) {
        super(message);
    }

    public NotAllowedException(String errorCode, String message) {
        super(errorCode, message);
    }

    public int getDefaultStatusCode() {
        return 403;
    }

}
