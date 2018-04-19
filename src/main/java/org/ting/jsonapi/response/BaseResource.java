package org.ting.jsonapi.response;

public abstract class BaseResource {

	private String id;
	private String type;

	public BaseResource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof BaseResource) {
			BaseResource that = (BaseResource) o;
			return (this.id.equals(that.getId())) && (this.type.equals(that.getType()));
		}
		return false;
	}

	@Override
	public int hashCode() {
		int h$ = 1;
		h$ *= 1000003;
		h$ ^= id.hashCode();
		h$ *= 1000003;
		h$ ^= type.hashCode();
		return h$;
	}

}
