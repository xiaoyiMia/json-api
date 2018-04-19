package org.ting.jsonapi.exception;

public class ClassCannotConvertException extends JsonApiException{

	private static final long serialVersionUID = 5475405759690394041L;
	private static final String TITLE = "Cannot convert object to a formmatted JSON object";

	private String detail;
	
	public ClassCannotConvertException(String detail) {
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
