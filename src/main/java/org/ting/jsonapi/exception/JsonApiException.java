package org.ting.jsonapi.exception;

import org.ting.jsonapi.ExceptionResponse;

public abstract class JsonApiException extends Exception  implements ExceptionResponse{

	private static final long serialVersionUID = -3756072345831647615L;
	
	public String getMessage() {
		return _getErrorDetail();
	}

}
