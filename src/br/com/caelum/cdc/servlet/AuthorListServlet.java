package br.com.caelum.cdc.servlet;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.cdc.dao.AuthorDao;
import br.com.caelum.cdc.model.AuthorOutputDto;

@WebServlet("/authors")
public class AuthorListServlet extends HttpServlet {

	private AuthorDao authorDao;
	
	public AuthorListServlet() {
		this.authorDao = new AuthorDao();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setAttribute("authors", authorDao.findAll()
				.stream().map(AuthorOutputDto::new).collect(Collectors.toList()));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/author/list.jsp");
		rd.forward(request, response);
	}
}
