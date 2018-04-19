package org.ting.jsonapi.exception;

public class IllegalAnnotationException extends JsonApiException{

	private static final long serialVersionUID = 5226200320992445404L;

	private static final String TITLE = "Illegal usage of annotation";

	private String detail;
	
	public IllegalAnnotationException(String detail) {
		super();
		this.detail = detail;
	}

	@Override
	public String _getErrorTitle() {
		return TITLE;
	}
	
	@Override
	public String _getErrorDetail() {
		return detail;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
