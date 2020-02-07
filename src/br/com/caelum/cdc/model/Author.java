package br.com.caelum.cdc.model;

public class Author {
	
	private String name;
	private String description;
	
	public Author() {
		super();
	}

	public Author(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
