package br.com.caelum.cdc.model;

public class CategoryOutputDto {
	
	private Long id;
	private String name;
	
	public CategoryOutputDto(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
