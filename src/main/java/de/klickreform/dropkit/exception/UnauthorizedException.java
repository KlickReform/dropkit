package de.klickreform.dropkit.exception;

/**
 * Exception to be thrown if a user tries to perform an action he is not authenticated
 * to perform.
 *
 * @author Benjamin Bestmann
 */
public class UnauthorizedException extends ApiException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String errorCode, String message) {
        super(errorCode, message);
    }

    public int getDefaultStatusCode() {
        return 401;
    }

}
