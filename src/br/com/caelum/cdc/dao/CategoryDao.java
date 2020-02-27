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

	public Optional<Category> findByName(String name) {
		Optional<Category> possibleCategory = Optional.empty();
		String sql = "select * from category where name = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Category category = new Category(rs.getString("name"));
				category.setId(rs.getLong("id"));
				possibleCategory = Optional.of(category);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return possibleCategory;
	}

	public void deleteById(Long id) {
		String sql = "delete from category where id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
