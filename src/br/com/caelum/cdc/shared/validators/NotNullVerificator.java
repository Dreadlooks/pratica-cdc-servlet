package br.com.caelum.cdc.shared.validators;

import java.lang.reflect.Field;
import java.util.Optional;

public class NotNullVerificator implements ConstraintValidator<Object> {

	@Override
	public Optional<String> valid(Field field, Object object) {
		Optional<String> errorMessage = Optional.empty();

		try {
			Object objectValue = field.get(object);

			if (objectValue == null) {
				errorMessage = Optional.ofNullable("O campo não pode ser nulo!");
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		return errorMessage;
	}

}
