package br.com.caelum.cdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.caelum.cdc.model.Author;
import br.com.caelum.cdc.model.Book;
import br.com.caelum.cdc.model.Category;

public class BookDao {

	private Connection conn;

	public BookDao(Connection connection) {
		this.conn = connection;
	}

	public void save(Book book) {
		String sql = "insert into book(title, category_id, price, resume, author_id, numberOfPages, "
				+ "isbn) values (?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getTitle());
			stmt.setLong(2, book.getCategory().getId());
			stmt.setBigDecimal(3, book.getPrice());
			stmt.setString(4, book.getResume());
			stmt.setLong(5, book.getAuthor().getId());
			stmt.setInt(6, book.getNumberOfPages());
			stmt.setString(7, book.getIsbn());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Book> findAll() {
		String sql = "select b.id, b.category_id, b.author_id, b.title, b.price, b.resume, b.numberOfPages, b.isbn, c.name as category_name, a.name as author_name, a.description"
				+ " from book b inner join category c inner join author a where b.category_id = c.id and "
				+ "b.author_id = a.id";
		List<Book> books = new ArrayList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Category category = new Category(rs.getString("category_name"));
				category.setId(rs.getLong("category_id"));

				Author author = new Author(rs.getString("author_name"), rs.getString("description"));
				author.setId(rs.getLong("author_id"));

				Book book = new Book(rs.getString("title"), category, rs.getBigDecimal("price"), rs.getString("resume"),
						author, rs.getInt("numberOfPages"), rs.getString("isbn"));
				book.setId(rs.getLong("id"));

				books.add(book);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return books;
	}
	
	public Optional<Book> findById(Long id) {
		Optional<Book> possibleBook = Optional.empty();
		String sql = "select b.id, b.category_id, b.author_id, b.title, b.price, b.resume, b.numberOfPages, b.isbn, c.name as category_name, a.name as author_name, a.description"
				+ " from book b inner join category c inner join author a where b.category_id = c.id and "
				+ "b.author_id = a.id and b.id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Category category = new Category(rs.getString("category_name"));
				category.setId(rs.getLong("category_id"));

				Author author = new Author(rs.getString("author_name"), rs.getString("description"));
				author.setId(rs.getLong("author_id"));

				Book book = new Book(rs.getString("title"), category, rs.getBigDecimal("price"), rs.getString("resume"),
						author, rs.getInt("numberOfPages"), rs.getString("isbn"));
				book.setId(rs.getLong("id"));
				possibleBook = Optional.of(book);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return possibleBook;
	}

	public Optional<Book> findByTitle(String title) {
		Optional<Book> possibleBook = Optional.empty();
		String sql = "select b.id, b.category_id, b.author_id, b.title, b.price, b.resume, b.numberOfPages, b.isbn, c.name as category_name, a.name as author_name, a.description"
				+ " from book b inner join category c inner join author a where b.category_id = c.id and "
				+ "b.author_id = a.id and b.title = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Category category = new Category(rs.getString("category_name"));
				category.setId(rs.getLong("category_id"));

				Author author = new Author(rs.getString("author_name"), rs.getString("description"));
				author.setId(rs.getLong("author_id"));

				Book book = new Book(rs.getString("title"), category, rs.getBigDecimal("price"), rs.getString("resume"),
						author, rs.getInt("numberOfPages"), rs.getString("isbn"));
				book.setId(rs.getLong("id"));
				possibleBook = Optional.of(book);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return possibleBook;
	}

	public void deleteById(Long id) {
		String sql = "delete from book where id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Book book) {
		String sql = "update book set title = ?, category_id = ?, price = ?,"
				+ " resume = ?, author_id = ?, numberOfPages = ?, isbn = ? where id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getTitle());
			stmt.setLong(2, book.getCategory().getId());
			stmt.setBigDecimal(3, book.getPrice());
			stmt.setString(4, book.getResume());
			stmt.setLong(5, book.getAuthor().getId());
			stmt.setInt(6, book.getNumberOfPages());
			stmt.setString(7, book.getIsbn());
			stmt.setLong(8, book.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
