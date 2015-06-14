package de.klickreform.dropkit.exception;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.klickreform.dropkit.models.ApiError;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Map all JsonProcessingExceptions
 *
 * @author Benjamin Bestmann
 */
@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {

    public Response toResponse(JsonProcessingException e) {

        ApiError error = new ApiError(400, "JSON Processing Error");

        // If the error is in the JSON generation, it's a server error.
        if (e instanceof JsonGenerationException) {
            return Response.status(500).entity(error).type(MediaType.APPLICATION_JSON).build();
        }

        // Otherwise it's the user
        return Response.status(Response.Status.BAD_REQUEST).build();

    }

}
