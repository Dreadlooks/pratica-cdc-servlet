package br.com.caelum.cdc.shared.validators;

import java.lang.reflect.Field;
import java.util.Map;

import br.com.caelum.cdc.shared.annotations.Size;

public class SizeVerificator {

	public static boolean verify(Field field, Object object, Map<String, String> errors) {
		String word = "";
		try {
			word = field.get(object).toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		int maxSize = field.getAnnotation(Size.class).max();
		int minSize = field.getAnnotation(Size.class).min();

		if (word.length() < minSize || word.length() > maxSize) {
			errors.put(field.getName(), "O campo deve conter algo entre "
											+ minSize + " e " + maxSize + " caracteres!");
			return true;
		} 
		
		return false;
	}

}
