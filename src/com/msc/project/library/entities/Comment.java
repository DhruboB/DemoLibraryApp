package com.msc.project.library.entities;

public class Comment {
	
	public String comment;
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comment;
	}

	public void setComments(String comments) {
		this.comment = comments;
	}

	public Comment(String name, String comments) {
		super();
		this.name = name;
		this.comment = comments;
	}

	public Comment() {
		super();
	}

}
