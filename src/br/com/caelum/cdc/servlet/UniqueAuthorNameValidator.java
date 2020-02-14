package br.com.caelum.cdc.servlet;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.cdc.dao.AuthorDao;

public class UniqueAuthorNameValidator {

	private AuthorDao authorDao;
	private boolean isInvalid;
	private static Map<String, String> error = new HashMap<>();
	
	public UniqueAuthorNameValidator(Connection connection) {
		this.authorDao = new AuthorDao(connection);
	}

	public void checkUniqueKey(String name) {
		if (authorDao.findByName(name).isPresent()) {
			this.isInvalid = true;
			error.put("name:", name + " já está em uso!");
		}
	}

	public boolean isInvalid() {
		return isInvalid;
	}
	
	public Map<String, String> getError() {
		return error;
	}
}
