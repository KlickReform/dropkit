package de.klickreform.dropkit.models;

import java.io.Serializable;

/**
 * Represent errors that occur during the usage of the API.
 *
 * @author Benjamin Bestmann
 */
public class ApiError implements Serializable {

    private int statusCode;
    private String errorCode;
    private String message;

    /**
     * Constructor for setting up with just a statusCode and a message.
     *
     * @param statusCode The http statusCode errorCode to set for this error
     * @param message The error message for this error
     */
    public ApiError(int statusCode, String message) {
        this.setStatusCode(statusCode);
        // Apply HTTP status code as error code as well
        this.setErrorCode(Integer.toString(statusCode));
        this.setMessage(message);
    }

    /**
     * Constructor for setting up with all properties.
     *
     * @param statusCode The http statusCode errorCode to set for this error
     * @param errorCode The API specific error errorCode
     * @param message The error message for this error
     */
    public ApiError(int statusCode, String errorCode, String message) {
        this.setStatusCode(statusCode);
        this.setErrorCode(errorCode);
        this.setMessage(message);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
