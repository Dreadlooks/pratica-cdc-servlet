package br.com.caelum.cdc.servlet;

import java.util.Optional;

import br.com.caelum.cdc.model.CategoryDto;

public class UniqueCategoryNameValidator extends UniqueFieldValidator<CategoryDto> {

	@Override
	public Optional<CategoryDto> getFieldToSearch(CategoryDto object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInvalidField() {
		// TODO Auto-generated method stub
		return null;
	}

}
