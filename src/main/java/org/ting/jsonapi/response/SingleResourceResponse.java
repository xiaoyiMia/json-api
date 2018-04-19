package org.ting.jsonapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;;

@JsonInclude(Include.NON_NULL)
public class SingleResourceResponse extends JsonResponse{

	private Resource data;

	public SingleResourceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resource getData() {
		return data;
	}

	public void setData(Resource data) {
		this.data = data;
	}
	
}
