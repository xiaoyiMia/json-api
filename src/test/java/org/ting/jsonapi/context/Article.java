package org.ting.jsonapi.context;

import java.util.List;

import org.ting.jsonapi.annotations.JsonApiId;
import org.ting.jsonapi.annotations.JsonApiRelation;
import org.ting.jsonapi.annotations.JsonApiRelationId;
import org.ting.jsonapi.annotations.JsonApiResource;

@JsonApiResource(type = "articles")
public class Article {

	@JsonApiId
	private Integer id;
	private String title;
	
	@JsonApiRelationId(attributeName = "author", type = "people")
	private Integer authorId;
	@JsonApiRelation
	private Person author;
	@JsonApiRelation
	private List<Comment> comments;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
