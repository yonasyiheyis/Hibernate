package edu.miu.cs.cs544.examples;

import javax.persistence.Entity;

@Entity
public class CD extends Product{
	
	private String artist;
	
	public CD() {}
	
	public CD(String artist, String name, String des) {
		super(name, des);
		this.setArtist(artist);
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}
