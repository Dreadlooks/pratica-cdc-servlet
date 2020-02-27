package br.com.caelum.cdc.servlet;

import java.util.Optional;

import br.com.caelum.cdc.dao.CategoryDao;
import br.com.caelum.cdc.model.CategoryDto;

public class UniqueCategoryNameValidator extends UniqueFieldValidator<CategoryDto> {

	private CategoryDao categoryDao;
	
	public UniqueCategoryNameValidator(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Override
	public Optional getFieldToSearch(CategoryDto object) {
		// TODO Auto-generated method stub
		return categoryDao.findByName(object.getName());
	}

	@Override
	public String getInvalidField() {
		// TODO Auto-generated method stub
		return "name";
	}

}
