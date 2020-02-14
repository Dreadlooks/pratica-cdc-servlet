package br.com.caelum.cdc.shared.validators;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.com.caelum.cdc.shared.annotations.Size;

public class ValidatorsUtil {
	
	private static Map<String, String> errors = new HashMap<>(); 
	private static boolean hasErrors;

	public <T> void validate(T object) {
		
		Class<?> clazz = object.getClass();
		
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Size.class)) {
				hasErrors = SizeVerificator.verify(field, object, errors);
			}
		}
	}

	public boolean hasErrors() {
		return hasErrors;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
}
