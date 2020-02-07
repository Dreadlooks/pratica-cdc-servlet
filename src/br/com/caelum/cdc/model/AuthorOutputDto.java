package br.com.caelum.cdc.model;

public class AuthorOutputDto {
	
	private Long id;
	private String name;
	private String description;
	
	public AuthorOutputDto(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
