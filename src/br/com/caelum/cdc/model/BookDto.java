package br.com.caelum.cdc.model;

import java.math.BigDecimal;

import br.com.caelum.cdc.dao.AuthorDao;
import br.com.caelum.cdc.dao.CategoryDao;
import br.com.caelum.cdc.shared.annotations.Min;
import br.com.caelum.cdc.shared.annotations.NotNull;
import br.com.caelum.cdc.shared.annotations.OnlyNumbers;
import br.com.caelum.cdc.shared.annotations.Size;
import br.com.caelum.cdc.shared.exceptions.AuthorNotFoundException;
import br.com.caelum.cdc.shared.exceptions.CategoryNotFoundException;

public class BookDto implements RequiredBookFields {

	@Size(max = 255, min = 1)
	private String title;
	
	@NotNull
	private Long categoryId;
	
	@NotNull
	private BigDecimal price;
	private String resume;
	
	@NotNull
	private Long authorId;
	
	@Min(value = 100)
	private int numberOfPages;
	
	@OnlyNumbers
	private String isbn;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Book toModel(CategoryDao categoryDao, AuthorDao authorDao) {
		Category category = categoryDao
				.findById(this.categoryId).orElseThrow(() -> new CategoryNotFoundException());
		
		Author author = authorDao.findById(this.authorId).orElseThrow(() -> new AuthorNotFoundException());
		
		return new Book(this.title, category, this.price, this.resume, author, 
				this.numberOfPages, this.isbn);		
	}
}
