package com.flixtiqs.flixtiqsSite.http.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.flixtiqs.flixtiqsSite.service.exception.FlixtiqsException;

@Provider
@Component
public class FlixtiqsExceptionMapper implements ExceptionMapper<FlixtiqsException> {

	@Override
	public Response toResponse(FlixtiqsException ex) {
		return Response.status(Status.CONFLICT).entity(new HttpError(ex)).build();
	}

	
}
