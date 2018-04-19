package org.ting.jsonapi.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Relationships extends Relationship{

	private Set<ResourceLinkage> data;

	public Relationships() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<ResourceLinkage> getData() {
		return data;
	}

	public void setData(Set<ResourceLinkage> data) {
		this.data = data;
	}
	
}
