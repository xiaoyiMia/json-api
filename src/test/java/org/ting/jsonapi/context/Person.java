package org.ting.jsonapi.context;

import org.ting.jsonapi.annotations.JsonApiId;
import org.ting.jsonapi.annotations.JsonApiResource;

@JsonApiResource(type = "people")
public class Person {

	@JsonApiId
	private Integer id;
	private String firstName;
	private String lastName;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
