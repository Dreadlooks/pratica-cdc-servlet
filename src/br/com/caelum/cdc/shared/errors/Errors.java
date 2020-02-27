package br.com.caelum.cdc.shared.errors;

import java.util.Map;

public interface Errors {
	
	boolean hasErrors();
	
	void removeError(String key);
	
	Map<String, String> getErrors();
	
	int getErrorCount();
}
