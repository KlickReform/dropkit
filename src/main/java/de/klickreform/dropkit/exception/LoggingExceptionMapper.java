package de.klickreform.dropkit.exception;

import de.klickreform.dropkit.models.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Random;

/**
 * Custom Version of the Dropwizard LoggingExceptionMapper
 *
 * @author Benjamin Bestmann
 * @since 25.02.14
 */
public class LoggingExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingExceptionMapper.class);
    private static final Random RANDOM = new Random();

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse();
        }
        // Get a random id and log exception
        final long id = RANDOM.nextLong();
        logException(id, exception);
        // Setup a new ApiError with Status Code Internal Server Error
        String message = "There was an unexpected error processing your request. The error has been logged (ID: " + id + ").";
        ApiError error = new ApiError(500, message + "Reason: " + exception.getMessage());
        // Return ApiError wrapped as JSON Response
        return Response.status(error.getStatusCode()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }

    protected void logException(long id, Throwable exception) {
        LOGGER.error(formatLogMessage(id, exception), exception);
    }

    @SuppressWarnings("UnusedParameters")
    protected String formatLogMessage(long id, Throwable exception) {
        return String.format("Error handling a request: %016x", id);
    }

}
