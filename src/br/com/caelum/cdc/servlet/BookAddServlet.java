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
import br.com.caelum.cdc.dao.BookDao;
import br.com.caelum.cdc.dao.CategoryDao;
import br.com.caelum.cdc.model.BookDto;
import br.com.caelum.cdc.shared.RequestProcessor;
import br.com.caelum.cdc.shared.errors.BindingResult;
import br.com.caelum.cdc.shared.validators.ValidatorsUtil;

@WebServlet("/book")
public class BookAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");

		CategoryDao categoryDao = new CategoryDao(connection);
		request.setAttribute("categories", categoryDao.findAll());

		AuthorDao authorDao = new AuthorDao(connection);
		request.setAttribute("authors", authorDao.findAll());

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/book/new-book.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");
		BindingResult result = (BindingResult) request.getAttribute("bindingResult");
		ValidatorsUtil validatorsUtil = new ValidatorsUtil(result);
		BookDao bookDao = new BookDao(connection);
		CategoryDao categoryDao = new CategoryDao(connection);
		AuthorDao authorDao = new AuthorDao(connection);
		UniqueBookTitleValidator uniqueBookTitleValidator = new UniqueBookTitleValidator(bookDao);

		BookDto bookDto = RequestProcessor.process(request, BookDto.class);
		validatorsUtil.validate(bookDto);
		uniqueBookTitleValidator.validate(bookDto, result);

		if (result.hasErrors()) {
			request.setAttribute("bookDto", bookDto);
			request.setAttribute("errors", result.getErrors());
			doGet(request, response);
		} else {
			bookDao.save(bookDto.toModel(categoryDao, authorDao));
			response.sendRedirect("/pratica-cdc-servlet/books");
		}
	}
}
