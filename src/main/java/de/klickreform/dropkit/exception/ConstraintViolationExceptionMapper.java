package de.klickreform.dropkit.exception;

import de.klickreform.dropkit.models.ApiError;
import org.hibernate.exception.ConstraintViolationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Map all of hibernates ConstraintViolationException to a HTTP status code 400 and return a JSON error entity.
 *
 * @author Benjamin Bestmann
 */
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        ApiError error = new ApiError(400,e.getMessage());
        return Response.status(error.getStatusCode()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }

}
