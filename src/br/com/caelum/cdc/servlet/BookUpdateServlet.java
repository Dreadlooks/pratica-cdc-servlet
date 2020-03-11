package br.com.caelum.cdc.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.cdc.dao.AuthorDao;
import br.com.caelum.cdc.dao.BookDao;
import br.com.caelum.cdc.dao.CategoryDao;
import br.com.caelum.cdc.model.Book;
import br.com.caelum.cdc.model.BookOutputDto;
import br.com.caelum.cdc.model.BookUpdateDto;
import br.com.caelum.cdc.shared.RequestProcessor;
import br.com.caelum.cdc.shared.errors.BindingResult;
import br.com.caelum.cdc.shared.exceptions.BookNotFoundException;
import br.com.caelum.cdc.shared.validators.ValidatorsUtil;

@WebServlet("/update/book")
public class BookUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Connection connection = (Connection) request.getAttribute("connection");

		BookDao bookDao = new BookDao(connection);
		AuthorDao authorDao = new AuthorDao(connection);
		CategoryDao categoryDao = new CategoryDao(connection);

		Optional<Book> possibleBook = bookDao.findById(id);

		if (possibleBook.isPresent()) {
			request.setAttribute("bookDto", new BookOutputDto(possibleBook.get()));
			request.setAttribute("categories", categoryDao.findAll());
			request.setAttribute("authors", authorDao.findAll());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/book/update-book.jsp");
			rd.forward(request, response);
		} else {
			 response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");
		BindingResult result = (BindingResult) request.getAttribute("bindingResult");

		BookDao bookDao = new BookDao(connection);
		AuthorDao authorDao = new AuthorDao(connection);
		CategoryDao categoryDao = new CategoryDao(connection);
		
		ValidatorsUtil validatorsUtil = new ValidatorsUtil(result);
		UniqueBookTitleValidator uniqueBookTitleValidator = new UniqueBookTitleValidator(bookDao);

		BookUpdateDto bookDto = RequestProcessor.process(request, BookUpdateDto.class);
		validatorsUtil.validate(bookDto);
		uniqueBookTitleValidator.validate(bookDto, result);

		if (result.hasErrors()) {
			request.setAttribute("bookDto", bookDto);
			request.setAttribute("errors", result.getErrors());
			doGet(request, response);
		} else {
			Book book = bookDao.findById(bookDto.getId()).orElseThrow(() -> new BookNotFoundException());
			book.update(bookDto, authorDao.findById(bookDto.getAuthorId()).get(),
					categoryDao.findById(bookDto.getCategoryId()).get());
			bookDao.update(book);
			response.sendRedirect("/pratica-cdc-servlet/books");
		}
	}
}
