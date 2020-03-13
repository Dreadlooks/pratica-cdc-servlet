package br.com.caelum.cdc.model;

public class AuthorDetailDto {

	private String name;
	private String description;
	
	public AuthorDetailDto(Author author) {
		this.name = author.getName();
		this.description = author.getDescription();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
