package org.ting.jsonapi.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class JsonResponse {

	private Map<String, Object> meta;
	private Link links;
	private Set<Resource> includes;
	private List<JsonError> errors;

	public JsonResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map<String, Object> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, Object> meta) {
		this.meta = meta;
	}

	public Link getLinks() {
		return links;
	}

	public void setLinks(Link links) {
		this.links = links;
	}

	public Set<Resource> getIncludes() {
		return includes;
	}

	public void setIncludes(Set<Resource> includes) {
		this.includes = includes;
	}

	public List<JsonError> getErrors() {
		return errors;
	}

	public void setErrors(List<JsonError> errors) {
		this.errors = errors;
	}
	
	public void addError(JsonError error) {
		if(this.getErrors() == null) {
			this.setErrors(new ArrayList<JsonError>());
		}
		this.getErrors().add(error);
	}

}
