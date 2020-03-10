package br.com.caelum.cdc.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.cdc.dao.BookDao;
import br.com.caelum.cdc.model.BookOutputDto;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");
		
		BookDao bookDao = new BookDao(connection);
		
		request.setAttribute("books", bookDao.findAll()
		.stream().map(BookOutputDto::new).collect(Collectors.toList()));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/book/list.jsp");
		rd.forward(request, response);
	}
}
