package br.com.caelum.cdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.caelum.cdc.model.Author;
import br.com.caelum.cdc.shared.ConnectionFactory;

public class AuthorDao {
	
	private Connection conn;

	public AuthorDao(Connection conn) {
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

}
