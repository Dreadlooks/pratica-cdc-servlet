package br.com.caelum.cdc.shared.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.caelum.cdc.shared.errors.BindingResult;
import br.com.caelum.cdc.shared.errors.ConcreteBindingResult;

@WebFilter("/*")
public class BindingResultFilter implements Filter {
	private static Logger logger = Logger.getLogger("bindingResultFilter");

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		BindingResult result = new ConcreteBindingResult();
		request.setAttribute("bindingResult", result);
		
		logger.info("Adding binding result!");
		
		filter.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
