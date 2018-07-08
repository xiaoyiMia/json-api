package org.ting.jsonapi.exception;

import org.ting.jsonapi.ExceptionResponseContainer;

public abstract class JsonApiException extends RuntimeException  implements ExceptionResponseContainer{

	private static final long serialVersionUID = -3756072345831647615L;
	
	public String getMessage() {
		return _getErrorDetail();
	}

}
