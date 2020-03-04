package br.com.caelum.cdc.model;

import br.com.caelum.cdc.model.RequiredCategoryFields;

public class CategoryUpdateDto implements RequiredCategoryFields {
	
	private String name;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
