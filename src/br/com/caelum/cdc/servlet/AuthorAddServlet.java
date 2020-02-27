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
import br.com.caelum.cdc.shared.errors.BindingResult;
import br.com.caelum.cdc.shared.errors.ConcreteBindingResult;
import br.com.caelum.cdc.shared.validators.ValidatorsUtil;

@WebServlet("/author")
public class AuthorAddServlet extends HttpServlet {

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
		BindingResult result = (BindingResult) request.getAttribute("bindingResult");
		AuthorDao authorDao = new AuthorDao(connection);
		ValidatorsUtil validatorsUtil = new ValidatorsUtil(result);
		UniqueAuthorNameValidator uniqueAuthorNameValidator = new UniqueAuthorNameValidator(authorDao, result);
		
		AuthorDto authorDto = RequestProcessor.process(request, AuthorDto.class);
		validatorsUtil.validate(authorDto);
		uniqueAuthorNameValidator.checkUniqueKey(authorDto.getName());		
		
		if (result.hasErrors()) {
			request.setAttribute("authorDto", authorDto);
			request.setAttribute("errors", result.getErrors());

			doGet(request, response);
		} else {
			authorDao.save(authorDto.toModel());
			
			response.sendRedirect("/pratica-cdc-servlet/authors");
		}
	}
}
