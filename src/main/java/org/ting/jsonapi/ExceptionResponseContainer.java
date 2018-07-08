package org.ting.jsonapi;

import java.util.Map;

public interface ExceptionResponseContainer {

	default String _getId() {
		return null;
	}
	
	default String _getAboutLink() {
		return null;
	}
	
	default String _getStatusCode() {
		return null;
	}
	
	default String _getDetailCode() {
		return null;
	}
	
	default String _getErrorTitle() {
		return null;
	}
	
	default String _getErrorDetail() {
		return null;
	}
	
	default String _getInvalidPayloadAttribute() {
		return null;
	}
	
	default String _getInvalidQueryParameter() {
		return null;
	}
	
	default Map<String, Object> _getMeta() {
		return null;
	}
}
