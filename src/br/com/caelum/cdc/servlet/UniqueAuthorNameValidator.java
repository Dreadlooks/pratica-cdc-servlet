package br.com.caelum.cdc.servlet;

import br.com.caelum.cdc.dao.AuthorDao;
import br.com.caelum.cdc.shared.errors.BindingResult;

public class UniqueAuthorNameValidator {

	private AuthorDao authorDao;
	private BindingResult result;
	
	public UniqueAuthorNameValidator(AuthorDao authorDao, BindingResult result) {
		this.authorDao = authorDao;
		this.result = result;
	}

	public void checkUniqueKey(String name) {
		if (authorDao.findByName(name).isPresent()) {
			result.addError("name", "Já existe um autor com este nome -> " + name);
		}
	}
}
