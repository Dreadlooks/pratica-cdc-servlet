package br.com.caelum.cdc.servlet;

import java.io.IOException;

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
	private AuthorDao authorDao;

	public AuthorAddServlet() {
		this.validatorsUtil = new ValidatorsUtil();
		this.authorDao = new AuthorDao();
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

		AuthorDto authorDto = RequestProcessor.process(request, AuthorDto.class);
		validatorsUtil.validate(authorDto);

		if (validatorsUtil.hasErrors()) {
			request.setAttribute("authorDto", authorDto);
			request.setAttribute("errors", validatorsUtil.getErrors());

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/author/new-form.jsp");
			rd.forward(request, response);
		} else {
			authorDao.save(authorDto.toModel());
			
			response.sendRedirect("/pratica-cdc-servlet/authors");
		}
	}
}
