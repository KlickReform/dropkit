package de.klickreform.dropkit.exception;

import de.klickreform.dropkit.models.ApiError;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.security.NoSuchAlgorithmException;

/**
 * Map all of hibernates ConstraintViolationException to a HTTP status code 400 and return a JSON error entity.
 *
 * @author Benjamin Bestmann
 */
public class NoSuchAlgorithmExceptionMapper implements ExceptionMapper<NoSuchAlgorithmException> {

    public Response toResponse(NoSuchAlgorithmException e) {
        ApiError error = new ApiError(500, "Encoding Algorithm Error");
        return Response.status(error.getStatusCode()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }

}
