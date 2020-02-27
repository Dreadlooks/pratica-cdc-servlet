package br.com.caelum.cdc.shared.validators;

import java.lang.reflect.Field;
import java.util.Optional;

import br.com.caelum.cdc.shared.annotations.Size;

public class SizeVerificator implements ConstraintValidator<Object> {

	@Override
	public Optional<String> valid(Field field, Object objectString) {
		String word = "";
		String errorMessage = null;
		
		try {
			word = field.get(objectString).toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		int maxSize = field.getAnnotation(Size.class).max();
		int minSize = field.getAnnotation(Size.class).min();
		
		if (word.length() < minSize || word.length() > maxSize) {
			errorMessage = "O campo deve conter algo entre "
											+ minSize + " e " + maxSize + " caracteres!";
		}
		
		return Optional.ofNullable(errorMessage); 
	}

}
