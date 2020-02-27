package br.com.caelum.cdc.model;

import br.com.caelum.cdc.shared.annotations.Size;

public class Category {
	
	private Long id;
	
	@Size(max = 30, min = 1)
	private String name;

	public Category(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void update(RequiredCategoryFields update) {
		this.name = update.getName();
	}
}
