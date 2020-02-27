package br.com.caelum.cdc.model;

public class AuthorOutputDto {
	
	private Long id;
	private String name;
	private String description;
	
	public AuthorOutputDto(Author author) {
		this.id = author.getId();
		this.name = author.getName();
		this.description = author.getDescription() ;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}	
}
