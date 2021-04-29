package edu.miu.cs.cs544.examples;

import javax.persistence.Entity;

@Entity
public class Book extends Product{
	
	private String title;
	
	public Book() {}
	
	public Book(String title, String name, String des) {
		super(name, des);
		this.setTitle(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
