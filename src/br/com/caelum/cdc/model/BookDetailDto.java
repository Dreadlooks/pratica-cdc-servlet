package br.com.caelum.cdc.model;

import java.math.BigDecimal;

public class BookDetailDto {
	
	private String title;
	private AuthorDetailDto author;
	private BigDecimal price;
	private String resume;
	private int numberOfPages;
	private String isbn;

	public BookDetailDto(Book book) {
		this.title = book.getTitle();
		this.author = new AuthorDetailDto(book.getAuthor());
		this.price = book.getPrice();
		this.resume = book.getResume();
		this.numberOfPages = book.getNumberOfPages();
		this.isbn = book.getIsbn();
	}

	public String getTitle() {
		return title;
	}

	public AuthorDetailDto getAuthor() {
		return author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getResume() {
		return resume;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public String getIsbn() {
		return isbn;
	}
}
