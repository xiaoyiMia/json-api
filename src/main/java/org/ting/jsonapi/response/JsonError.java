package org.ting.jsonapi.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JsonError {

	private String id;
	private Link links;
	private String status;
	private String code;
	private String title;
	private String detail;
	private ErrorSource source;
	private Map<String, Object> meta;

	public JsonError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Link getLinks() {
		return links;
	}

	public void setLinks(Link links) {
		this.links = links;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public ErrorSource getSource() {
		return source;
	}

	public void setSource(ErrorSource source) {
		this.source = source;
	}

	public Map<String, Object> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, Object> meta) {
		this.meta = meta;
	}
	
	public static JsonErrorBuilder builder() {
		return new JsonErrorBuilder();
	}
	
	public static class JsonErrorBuilder{
		private JsonError error;
		
		public JsonErrorBuilder() {
			error = new JsonError();
		}
		
		public JsonErrorBuilder id(String id) {
			error.setId(id);
			return this;
		}
		
		public JsonErrorBuilder links(Link links) {
			error.setLinks(links);
			return this;
		}
		
		public JsonErrorBuilder status(String status) {
			error.setStatus(status);
			return this;
		}
		
		public JsonErrorBuilder code(String code) {
			error.setCode(code);
			return this;
		}
		
		public JsonErrorBuilder title(String title) {
			error.setTitle(title);
			return this;
		}
		
		public JsonErrorBuilder detail(String detail) {
			error.setDetail(detail);
			return this;
		}
		
		public JsonErrorBuilder source(ErrorSource source) {
			error.setSource(source);
			return this;
		}
		
		public JsonErrorBuilder meta(Map<String, Object> meta) {
			error.setMeta(meta);
			return this;
		}
		
		public JsonError build() {
			return error;
		}
	}

}
