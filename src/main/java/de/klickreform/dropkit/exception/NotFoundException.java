package de.klickreform.dropkit.exception;

/**
 * Created by benjamin on 01.01.15.
 */
public class NotFoundException extends ApiException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }

    @Override
    public int getDefaultStatusCode() {
        return 404;
    }
}
