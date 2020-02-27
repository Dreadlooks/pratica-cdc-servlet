package br.com.caelum.cdc.servlet;

import java.util.Optional;

import br.com.caelum.cdc.dao.CategoryDao;
import br.com.caelum.cdc.model.CategoryDto;
import br.com.caelum.cdc.model.RequiredCategoryFields;
import br.com.caelum.cdc.shared.validators.UniqueFieldValidator;

public class UniqueCategoryNameValidator extends UniqueFieldValidator<RequiredCategoryFields> {

	private CategoryDao categoryDao;
	
	public UniqueCategoryNameValidator(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Override
	public Optional getFieldToSearch(RequiredCategoryFields object) {
		// TODO Auto-generated method stub
		return categoryDao.findByName(object.getName());
	}

	@Override
	public String getInvalidField() {
		// TODO Auto-generated method stub
		return "name";
	}

}
