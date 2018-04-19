package org.ting.jsonapi.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Resource extends BaseResource {

	private Map<String, Object> attributes;
	private Map<String, Relationship> relationships;
	private Link links;
	private Map<String, Object> meta;

	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(Map<String, Relationship> relationships) {
		this.relationships = relationships;
	}

	public Link getLinks() {
		return links;
	}

	public void setLinks(Link links) {
		this.links = links;
	}

	public Map<String, Object> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, Object> meta) {
		this.meta = meta;
	}

}
