package br.com.caelum.cdc.shared.errors;

public interface BindingResult extends Errors {

	void addError(String fieldName, String errorMessage);
}
