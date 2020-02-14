package br.com.caelum.cdc.shared.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.caelum.cdc.shared.ConnectionFactory;

@WebFilter("/*")
public class ConnectionFilter implements Filter {

	private static Logger logger = Logger.getLogger("connectionFilter");
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			logger.info("Opening connection");
			
			request.setAttribute("connection", conn);
			
			filter.doFilter(request, response);
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
