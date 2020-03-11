package br.com.caelum.cdc.servlet;

import java.util.Optional;

import br.com.caelum.cdc.dao.BookDao;
import br.com.caelum.cdc.model.RequiredBookFields;
import br.com.caelum.cdc.shared.validators.UniqueFieldValidator;

public class UniqueBookTitleValidator extends UniqueFieldValidator<RequiredBookFields> {

	private BookDao bookDao;

	public UniqueBookTitleValidator(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public Optional getFieldToSearch(RequiredBookFields bookDto) {
		// TODO Auto-generated method stub
		return bookDao.findByTitle(bookDto.getTitle());
	}

	@Override
	public String getInvalidField() {
		// TODO Auto-generated method stub
		return "title";
	}

}
