package br.com.caelum.cdc.model;

import br.com.caelum.cdc.shared.annotations.Size;

public class AuthorDto {

	@Size(max = 100)
	private String name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Author toModel() {
		return new Author(this.name, this.description);
	}
}
