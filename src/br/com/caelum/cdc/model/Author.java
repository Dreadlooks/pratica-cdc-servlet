package br.com.caelum.cdc.model;

import br.com.caelum.cdc.shared.annotations.NotBlank;
import br.com.caelum.cdc.shared.annotations.Size;

public class Author {

	private Long id;
	
	@Size(max = 100, min = 1)
	private String name;
	
	@NotBlank
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
