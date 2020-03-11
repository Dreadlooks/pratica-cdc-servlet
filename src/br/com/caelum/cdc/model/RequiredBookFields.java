package br.com.caelum.cdc.model;

import java.math.BigDecimal;

public interface RequiredBookFields {

	String getTitle();

	Long getCategoryId();

	BigDecimal getPrice();

	String getResume();

	Long getAuthorId();

	int getNumberOfPages();

	String getIsbn();

}
