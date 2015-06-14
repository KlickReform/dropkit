package de.klickreform.dropkit.exception;

/**
 * Created by benjamin on 30.04.15.
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
