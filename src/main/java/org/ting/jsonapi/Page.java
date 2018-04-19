package org.ting.jsonapi;

import java.util.List;

public class Page<T> {

	private long totalElements;
	private List<Object> contents;

	public Page(long totalElements, List<Object> contents) {
		super();
		this.totalElements = totalElements;
		this.contents = contents;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public long getCurrentElements() {
		if(contents == null) {
			return 0;
		} else {
			return contents.size();
		}
	}

	public List<Object> getContents() {
		return contents;
	}

	public void setContents(List<Object> contents) {
		this.contents = contents;
	}

}
