package br.com.caelum.cdc.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.cdc.dao.CategoryDao;
import br.com.caelum.cdc.model.CategoryDto;
import br.com.caelum.cdc.shared.RequestProcessor;
import br.com.caelum.cdc.shared.errors.BindingResult;
import br.com.caelum.cdc.shared.validators.ValidatorsUtil;

@WebServlet("/category")
public class CategoryAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/category/new-category.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");
		BindingResult result = (BindingResult) request.getAttribute("bindingResult");

		CategoryDao categoryDao = new CategoryDao(connection);
		ValidatorsUtil validatorsUtil = new ValidatorsUtil(result);
		UniqueCategoryNameValidator uniqueCategoryNameValidator = 
				new UniqueCategoryNameValidator(categoryDao);
		
		CategoryDto categoryDto = RequestProcessor.process(request, CategoryDto.class);
		validatorsUtil.validate(categoryDto);
		uniqueCategoryNameValidator.validate(categoryDto, result);
		
		if (result.hasErrors()) {
			request.setAttribute("categoryDto", categoryDto);
			request.setAttribute("errors", result.getErrors());

			doGet(request, response);
		} else {
			categoryDao.save(categoryDto.toModel());
			response.sendRedirect("/pratica-cdc-servlet/categories");
		}
	}
}
