package br.com.caelum.cdc.shared.validators;

import java.lang.reflect.Field;
import java.util.Optional;

public interface ConstraintValidator<T> {

	Optional<String> valid(Field field, T obj);
}
