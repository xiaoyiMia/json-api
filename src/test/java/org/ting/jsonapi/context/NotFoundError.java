package org.ting.jsonapi.context;

import org.ting.jsonapi.ExceptionResponse;

public class NotFoundError implements ExceptionResponse{

	private final String STATUS = "404";
	
	@Override
	public String _getStatusCode() {
		return STATUS;
	}
	
	@Override
	public String _getDetailCode() {
		return "1F345";
	}
	
}
