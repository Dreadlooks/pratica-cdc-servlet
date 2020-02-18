package br.com.caelum.cdc.shared.errors;

import java.util.HashMap;
import java.util.Map;

public class ConcreteBindingResult implements BindingResult {
	
	Map<String, String> errors = new HashMap<String, String>();

	@Override
	public boolean hasErrors() {
		return !this.errors.isEmpty();
	}

	@Override
	public Map<String, String> getErrors() {
		// TODO Auto-generated method stub
		return this.errors;
	}

	@Override
	public int getErrorCount() {
		// TODO Auto-generated method stub
		return this.errors.size();
	}

	@Override
	public void addError(String fieldName, String errorMessage) {
		// TODO Auto-generated method stub
		this.errors.put(fieldName, errorMessage);
	}

	@Override
	public void removeError(String key) {
		this.errors.remove(key);
		
	}
}
