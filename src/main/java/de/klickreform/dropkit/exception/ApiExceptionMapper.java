package de.klickreform.dropkit.exception;

import de.klickreform.dropkit.models.ApiError;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * ExceptionMapper to map all instances of ApiException to a Response that contains a JSON
 * representation of the error, including status code and error message.
 *
 * @author Benjamin Bestmann
 */
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException e) {
        ApiError error = e.getError();
        return Response.status(error.getStatusCode()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }

}
