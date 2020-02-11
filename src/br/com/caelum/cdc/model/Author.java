package br.com.caelum.cdc.model;

public class Author {
	
	private Long id;
	private String name;
	private String description;

	public Author(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
