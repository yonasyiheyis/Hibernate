package edu.miu.cs.cs544.examples;

import javax.persistence.Entity;

@Entity
public class DVD extends Product{

	private String genre;
	
	public DVD() {}
	
	public DVD(String genre, String name, String des) {
		super(name, des);
		this.setGenre(genre);
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
