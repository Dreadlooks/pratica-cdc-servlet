package br.com.caelum.cdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.caelum.cdc.model.Category;

public class CategoryDao {
	
	private Connection conn;

	public CategoryDao(Connection connection) {
		this.conn = connection;
	}
	
	public void save(Category category) {
		String sql = "insert into category(name) values (?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, category.getName());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Category> findAll() {
		String sql = "select * from category";
		List<Category> categories = new ArrayList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Category category = new Category(rs.getString("name"));
				category.setId(rs.getLong("id"));
				
				categories.add(category);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return categories;
	}
}
