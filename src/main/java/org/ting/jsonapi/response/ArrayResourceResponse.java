package org.ting.jsonapi.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ArrayResourceResponse extends JsonResponse{

	private Set<Resource> data;

	public ArrayResourceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<Resource> getData() {
		return data;
	}

	public void setData(Set<Resource> data) {
		this.data = data;
	}
	
	
}
