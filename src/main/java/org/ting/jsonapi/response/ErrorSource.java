package org.ting.jsonapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorSource {

	private String pointer;
	private String parameter;

	public ErrorSource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPointer() {
		return pointer;
	}

	public void setPointer(String pointer) {
		this.pointer = pointer;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	public static ErrorSourceBuilder builder() {
		return new ErrorSourceBuilder();
	}
	
	public static class ErrorSourceBuilder{
		private ErrorSource source;
		
		public ErrorSourceBuilder() {
			source = new ErrorSource();
		}

		public ErrorSourceBuilder pointer(String pointer) {
			source.setPointer(pointer);
			return this;
		}
		
		public ErrorSourceBuilder parameter(String parameter) {
			source.setParameter(parameter);
			return this;
		}
		
		public ErrorSource build() {
			return source;
		}
	}

}
