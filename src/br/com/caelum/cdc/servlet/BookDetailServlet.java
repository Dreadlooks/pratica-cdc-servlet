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

import br.com.caelum.cdc.dao.BookDao;
import br.com.caelum.cdc.model.Book;
import br.com.caelum.cdc.model.BookDetailDto;

@WebServlet("/book/detail")
public class BookDetailServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");
		Long id = Long.parseLong(request.getParameter("id"));
		
		Optional<Book> possibleBook = new BookDao(connection).findById(id);
		
		if (possibleBook.isPresent()) {
			request.setAttribute("bookDetail", new BookDetailDto(possibleBook.get()));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/book/book-detail.jsp");
			rd.forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}		
	}

}
