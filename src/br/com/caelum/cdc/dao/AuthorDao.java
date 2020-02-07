package br.com.caelum.cdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cdc.model.Author;
import br.com.caelum.cdc.model.AuthorOutputDto;
import br.com.caelum.cdc.shared.ConnectionFactory;

public class AuthorDao {

	private Connection conn;

	public AuthorDao() {
		this.conn = new ConnectionFactory().getConnection();
	}

	public void save(Author author) {
		String sql = "insert into author(name, description) values (?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, author.getName());
			stmt.setString(2, author.getDescription());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<AuthorOutputDto> findAll() {
		String sql = "select * from author";
		List<AuthorOutputDto> authors = new ArrayList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				AuthorOutputDto author = new AuthorOutputDto(rs.getLong("id"), 
						rs.getString("name"), rs.getString("description"));
				
				authors.add(author);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return authors;
	}

}
