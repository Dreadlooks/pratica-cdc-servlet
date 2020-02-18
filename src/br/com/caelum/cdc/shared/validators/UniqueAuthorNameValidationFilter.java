package br.com.caelum.cdc.shared.validators;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.caelum.cdc.dao.AuthorDao;
import br.com.caelum.cdc.servlet.UniqueAuthorNameValidator;
import br.com.caelum.cdc.shared.errors.BindingResult;
import br.com.caelum.cdc.shared.errors.ConcreteBindingResult;

@WebFilter("/author")
public class UniqueAuthorNameValidationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		Connection connection = (Connection) request.getAttribute("connection");
		BindingResult result = (ConcreteBindingResult) request.getAttribute("bindingResult");
		System.out.println(connection);
		System.out.println(result);
		System.out.println("oie");
		if (result != null) {
			UniqueAuthorNameValidator uniqueAuthorNameValidator = new UniqueAuthorNameValidator(
					new AuthorDao(connection), result);
			uniqueAuthorNameValidator.checkUniqueKey(request.getParameter("name"));
			System.out.println(connection);
			System.out.println(result);
			
			if (result.hasErrors()) {
				System.out.println("oi");
			}
		}



		filter.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
