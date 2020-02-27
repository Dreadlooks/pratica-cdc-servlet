package br.com.caelum.cdc.model;

import br.com.caelum.cdc.shared.annotations.Size;

public class CategoryDto {
	
	@Size(max = 30, min = 1)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Category toModel() {
		return new Category(this.name);
	}
}
