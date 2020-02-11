package br.com.caelum.cdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.caelum.cdc.model.Author;
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
			throw new RuntimeException(e);
		}
	}

	public List<Author> findAll() {
		String sql = "select * from author";
		List<Author> authors = new ArrayList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Author author = new Author(rs.getString("name"), rs.getString("description"));
				author.setId(rs.getLong("id"));
				
				authors.add(author);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return authors;
	}
	
	
	public Optional<Author> findByName(String name) {
		Optional<Author> possibleAuthor = Optional.empty();
		String sql = "select * from author where name = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Author author = new Author(rs.getString("name"), rs.getString("description"));
				author.setId(rs.getLong("id"));
				possibleAuthor = Optional.of(author);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return possibleAuthor;
	} 

}
