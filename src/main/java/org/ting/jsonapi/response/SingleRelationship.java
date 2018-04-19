package org.ting.jsonapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SingleRelationship extends Relationship{

	private ResourceLinkage data;

	public SingleRelationship() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceLinkage getData() {
		return data;
	}

	public void setData(ResourceLinkage data) {
		this.data = data;
	}
	
}
