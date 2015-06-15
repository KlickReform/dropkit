package de.klickreform.dropkit.exception;

/**
 * Exception to be thrown if a user tries to enter invalid data.
 *
 * @author Benjamin Bestmann
 */
public class InvalidDataException extends ApiException {

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String errorCode, String message) {
        super(errorCode, message);
    }

    @Override
    public int getDefaultStatusCode() {
        return 400;
    }

}
