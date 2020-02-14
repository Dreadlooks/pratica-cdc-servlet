package br.com.caelum.cdc.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.cdc.dao.AuthorDao;
import br.com.caelum.cdc.model.AuthorDto;
import br.com.caelum.cdc.shared.RequestProcessor;
import br.com.caelum.cdc.shared.validators.ValidatorsUtil;

@WebServlet("/author")
public class AuthorAddServlet extends HttpServlet {

	private ValidatorsUtil validatorsUtil;

	public AuthorAddServlet() {
		this.validatorsUtil = new ValidatorsUtil();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/author/new-form.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");
		AuthorDao authorDao = new AuthorDao(connection);
		AuthorDto authorDto = RequestProcessor.process(request, AuthorDto.class);
		validatorsUtil.validate(authorDto);
		UniqueAuthorNameValidator uniqueAuthorNameValidator = new UniqueAuthorNameValidator(connection);
		uniqueAuthorNameValidator.checkUniqueKey(authorDto.getName());		
		
		if (validatorsUtil.hasErrors() || uniqueAuthorNameValidator.isInvalid()) {
			request.setAttribute("authorDto", authorDto);
			request.setAttribute("unique", uniqueAuthorNameValidator.getError());
			request.setAttribute("errors", validatorsUtil.getErrors());

			doGet(request, response);
		} else {
			authorDao.save(authorDto.toModel());
			
			response.sendRedirect("/pratica-cdc-servlet/authors");
		}
	}
}
