package br.com.caelum.cdc.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.cdc.dao.CategoryDao;

@WebServlet("/delete/category")
public class CategoryDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		Connection connection = (Connection) request.getAttribute("connection");
		CategoryDao categoryDao = new CategoryDao(connection);
		
		categoryDao.deleteById(id);
		response.sendRedirect("/pratica-cdc-servlet/categories");
	}
	
}
