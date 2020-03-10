package br.com.caelum.cdc.model;

import java.math.BigDecimal;

import br.com.caelum.cdc.shared.annotations.Min;
import br.com.caelum.cdc.shared.annotations.NotNull;
import br.com.caelum.cdc.shared.annotations.OnlyNumbers;
import br.com.caelum.cdc.shared.annotations.Size;

public class Book {
	
	private Long id;
	
	@Size(max = 255, min = 1)
	private String title;
	
	@NotNull
	private Category category;
	
	@NotNull
	private BigDecimal price;
	private String resume;
	
	@NotNull
	private Author author;
	
	@Min(value = 100)
	private int numberOfPages;
	
	@OnlyNumbers
	private String isbn;

	public Book(String title, Category category, BigDecimal price, String resume, Author author, int numberOfPages,
			String isbn) {
		this.title = title;
		this.category = category;
		this.price = price;
		this.resume = resume;
		this.author = author;
		this.numberOfPages = numberOfPages;
		this.isbn = isbn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
