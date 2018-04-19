package org.ting.jsonapi.response;

public final class ResourceLinkage extends BaseResource{

	public ResourceLinkage() {
		super();
	}
	
	public ResourceLinkage(String id, String type) {
		super();
		this.setId(id);
		this.setType(type);
	}
}
