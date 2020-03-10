package br.com.caelum.cdc.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.cdc.dao.BookDao;

@WebServlet("/delete/book")
public class BookDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		Connection connection = (Connection) request.getAttribute("connection");
		BookDao bookDao = new BookDao(connection);
		
		bookDao.deleteById(id);
		response.sendRedirect("/pratica-cdc-servlet/books");
	}
}
