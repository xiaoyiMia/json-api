package org.ting.jsonapi.response;

import java.util.Map;

public abstract class Relationship {

	private Link link;
	private Map<String, Object> meta;

	public Relationship() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public Map<String, Object> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, Object> meta) {
		this.meta = meta;
	}

}
