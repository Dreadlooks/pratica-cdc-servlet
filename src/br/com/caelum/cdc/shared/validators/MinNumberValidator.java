package br.com.caelum.cdc.shared.validators;

import java.lang.reflect.Field;
import java.util.Optional;

import br.com.caelum.cdc.shared.annotations.Min;

public class MinNumberValidator implements ConstraintValidator<Object> {

	@Override
	public Optional<String> valid(Field field, Object object) {
		int minValue= field.getAnnotation(Min.class).value();
		Optional<String> errorMessage = Optional.empty();
		
		try {
			int userInput = Integer.parseInt(field.get(object).toString());
			if (minValue > userInput) {
				errorMessage = Optional.of("O tamanho deve ser no minimo " + minValue + "!");
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
		return errorMessage;
	}

}
