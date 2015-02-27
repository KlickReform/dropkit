package de.klickreform.dropkit.exception;

import de.klickreform.dropkit.models.ApiError;

/**
 * Exception class used to throw error messages by the Api. Any Exception of this type
 * can be Mapped to an JSON Error Response using the ApiExceptionMapper.
 *
 * @author Benjamin Bestmann
 */
public class ApiException extends RuntimeException {

    private ApiError error;

    public ApiException(String message) {
        super(message);
        this.error = new ApiError(getDefaultStatusCode(),message);
    }

    public ApiException(String errorCode, String message) {
        super(message);
        this.error = new ApiError(getDefaultStatusCode(),errorCode,message);
    }

    public ApiException(int statusCode, String errorCode, String message) {
        super(message);
        this.error = new ApiError(statusCode,message);
    }

    public ApiError getError() {
        return this.error;
    }

    public int getStatusCode() {
        return this.error.getStatusCode();
    }

    public String getErrorCode() {
        return this.error.getErrorCode();
    }

    public int getDefaultStatusCode() {
        return 500;
    }

}
