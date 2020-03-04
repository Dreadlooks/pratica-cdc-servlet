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

import br.com.caelum.cdc.dao.CategoryDao;
import br.com.caelum.cdc.model.Category;
import br.com.caelum.cdc.model.CategoryOutputDto;
import br.com.caelum.cdc.model.CategoryUpdateDto;
import br.com.caelum.cdc.shared.RequestProcessor;
import br.com.caelum.cdc.shared.errors.BindingResult;
import br.com.caelum.cdc.shared.exceptions.CategoryNotFoundException;
import br.com.caelum.cdc.shared.validators.ValidatorsUtil;

@WebServlet("/update/category")
public class CategoryUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Connection connection = (Connection) request.getAttribute("connection");
		
		CategoryDao categoryDao = new CategoryDao(connection);
		Optional<Category> possibleCategory = categoryDao.findById(id);
		
		if (possibleCategory.isPresent()) {
			request.setAttribute("categoryDto", new CategoryOutputDto(possibleCategory.get()));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/category/update-category.jsp");
			rd.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		Connection connection = (Connection) request.getAttribute("connection");
		BindingResult result = (BindingResult) request.getAttribute("bindingResult");

		CategoryDao categoryDao = new CategoryDao(connection);
		ValidatorsUtil validatorsUtil = new ValidatorsUtil(result);
		UniqueCategoryNameValidator uniqueCategoryNameValidator = 
				new UniqueCategoryNameValidator(categoryDao);
		
		CategoryUpdateDto categoryUpdateDto = RequestProcessor.process(request, CategoryUpdateDto.class);
		validatorsUtil.validate(categoryUpdateDto);
		uniqueCategoryNameValidator.validate(categoryUpdateDto, result);
		
		if (result.hasErrors()) {
			request.setAttribute("categoryDto", categoryUpdateDto);
			request.setAttribute("errors", result.getErrors());

			doGet(request, response);
		} else {
			Category category = categoryDao.findById(id).orElseThrow(() -> new CategoryNotFoundException());
			category.update(categoryUpdateDto);
			categoryDao.update(category);
			response.sendRedirect("/pratica-cdc-servlet/categories");
		}
	}

}
