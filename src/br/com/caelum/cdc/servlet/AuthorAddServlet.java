package br.com.caelum.cdc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.cdc.model.Author;
import br.com.caelum.cdc.model.AuthorDto;
import br.com.caelum.cdc.shared.RequestProcessor;

@WebServlet("/author")
public class AuthorAddServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/author/new-form.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AuthorDto authorDto = RequestProcessor.process(request, AuthorDto.class);
		
		System.out.println(authorDto.getName());
		System.out.println(authorDto.getDescription());
		
	}
}


