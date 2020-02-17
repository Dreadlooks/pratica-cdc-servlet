package br.com.caelum.cdc.shared.validators;

import java.lang.reflect.Field;
import java.util.Optional;

import com.mysql.cj.util.StringUtils;

public class NotBlankVerificator implements ConstraintValidator<Object> {

	@Override
	public Optional<String> valid(Field field, Object objectString) {
		String errorMessage = null;

		try {
			String word = field.get(objectString).toString();

			if (StringUtils.isNullOrEmpty(word)) {
				errorMessage = "O campo não pode ser nulo!";
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		return Optional.ofNullable(errorMessage);
	}
}
