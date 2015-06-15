package de.klickreform.dropkit.exception;

/**
 * Exception to be thrown if something goes wrong when sending email.
 *
 * @author Benjamin Bestmann
 */
public class EmailException extends ApiException {

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String errorCode, String message) {
        super(errorCode, message);
    }

    @Override
    public int getDefaultStatusCode() {
        return 500;
    }

}
