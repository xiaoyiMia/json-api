package org.ting.jsonapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Link {

	private String self;
	private String related;
	private String about;

	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public String getRelated() {
		return related;
	}

	public void setRelated(String related) {
		this.related = related;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	public static LinkBuilder builder() {
		return new LinkBuilder();
	}
	
	public static class LinkBuilder {
		
		private Link links;
		
		public LinkBuilder() {
			links = new Link();
		}
		
		public LinkBuilder self(String self) {
			links.setSelf(self);
			return this;
		}
		
		public LinkBuilder related(String related) {
			links.setRelated(related);
			return this;
		}
		
		public LinkBuilder about(String about) {
			links.setAbout(about);
			return this;
		}
		
		public Link build() {
			return links;
		}
	}

}
