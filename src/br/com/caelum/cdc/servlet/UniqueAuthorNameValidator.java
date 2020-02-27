package br.com.caelum.cdc.servlet;

import java.util.Optional;

import br.com.caelum.cdc.dao.AuthorDao;
import br.com.caelum.cdc.model.AuthorDto;
import br.com.caelum.cdc.shared.validators.UniqueFieldValidator;

public class UniqueAuthorNameValidator extends UniqueFieldValidator<AuthorDto> {

	private AuthorDao authorDao;
	
	public UniqueAuthorNameValidator(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	@Override
	public Optional getFieldToSearch(AuthorDto authorDto) {
		// TODO Auto-generated method stub
		return authorDao.findByName(authorDto.getName());
	}

	@Override
	public String getInvalidField() {
		// TODO Auto-generated method stub
		return "name";
	}
}
