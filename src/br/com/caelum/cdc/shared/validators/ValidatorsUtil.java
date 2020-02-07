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
				verifySize(field, object);
			}
		}
	}

	private void verifySize(Field field, Object object) {
		String word = "";
		try {
			word = field.get(object).toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		int maxSize = field.getAnnotation(Size.class).max();
		int minSize = field.getAnnotation(Size.class).min();

		if (word.length() < minSize || word.length() > maxSize) {
			hasErrors = true;
			errors.put(field.getName(), "O campo deve conter algo entre "
						+ minSize + " e " + maxSize + " caracteres!");
		} else {
			hasErrors = false;
		}
	}

	public boolean hasErrors() {
		return hasErrors;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
}
