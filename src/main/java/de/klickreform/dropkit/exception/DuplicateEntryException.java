package de.klickreform.dropkit.exception;

/**
 * Exception to be thrown if there is already an matching entity
 * for a unique field.
 *
 * @author Benjamin Bestmann
 */
public class DuplicateEntryException extends ApiException {

    public DuplicateEntryException(String message) {
        super(message);
    }

    public DuplicateEntryException(String errorCode, String message) {
        super(errorCode, message);
    }

    @Override
    public int getDefaultStatusCode() {
        return 409;
    }

}
