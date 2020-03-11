package br.com.caelum.cdc.shared.validators;

import java.lang.reflect.Field;
import java.util.Optional;

public class OnlyNumbersVerificator implements ConstraintValidator<Object> {

	@Override
	public Optional<String> valid(Field field, Object objectString) {
		String errorMessage = null;

		try {
			String word = field.get(objectString).toString();

			if (!word.matches("^[0-9]*$")) {
				errorMessage = "O campo deve conter apenas numeros!";
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
		return Optional.ofNullable(errorMessage);
	}

}
