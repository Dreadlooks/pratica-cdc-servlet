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

import br.com.caelum.cdc.dao.CategoryDao;
import br.com.caelum.cdc.model.CategoryOutputDto;

@WebServlet("/categories")
public class CategoryListServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");
		
		CategoryDao categoryDao = new CategoryDao(connection);
		request.setAttribute("categories", categoryDao.findAll()
				.stream().map(CategoryOutputDto::new).collect(Collectors.toList()));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/category/list.jsp");
		rd.forward(request, response);
	}
}
