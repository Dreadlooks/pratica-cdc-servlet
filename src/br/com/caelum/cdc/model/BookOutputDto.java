package br.com.caelum.cdc.model;

import java.math.BigDecimal;

public class BookOutputDto {
	
	private Long id;
	private String title;
	private Category category;
	private BigDecimal price;
	private String resume;
	private Author author;
	private int numberOfPages;
	private String isbn;
	
	public BookOutputDto(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.category = book.getCategory();
		this.price = book.getPrice();
		this.resume = book.getResume();
		this.author = book.getAuthor();
		this.numberOfPages = book.getNumberOfPages();
		this.isbn = book.getIsbn();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Category getCategory() {
		return category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getResume() {
		return resume;
	}

	public Author getAuthor() {
		return author;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public String getIsbn() {
		return isbn;
	}
}
