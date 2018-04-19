package org.ting.jsonapi.context;

import org.ting.jsonapi.annotations.JsonApiId;
import org.ting.jsonapi.annotations.JsonApiRelation;
import org.ting.jsonapi.annotations.JsonApiRelationId;
import org.ting.jsonapi.annotations.JsonApiResource;

@JsonApiResource(type = "comments")
public class Comment {

	@JsonApiId
	private int id;
	private String body;
	
	@JsonApiRelationId(attributeName = "author", type = "people")
	private int authorId;
	@JsonApiRelation
	private Person author;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

}
