package br.com.caelum.cdc.shared.validators;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.caelum.cdc.shared.annotations.ValidationAnnotation;
import br.com.caelum.cdc.shared.errors.BindingResult;

public class ValidatorsUtil {

	private static Map<String, String> errors = new HashMap<>();
	private static boolean hasErrors;
	private BindingResult result;

	public ValidatorsUtil(BindingResult result) {
		this.result = result;
	}

	public <T> void validate(T object) {

		Class<?> clazz = object.getClass();

		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			Annotation[] annotations = field.getAnnotations();

			Set<Annotation> validationAnnotations = Stream.of(annotations)
					.filter(annotation -> annotation.annotationType().isAnnotationPresent(ValidationAnnotation.class))
					.collect(Collectors.toSet());

			validationAnnotations.forEach(annotation -> {
				Class<? extends ConstraintValidator> validationClass = (Class<? extends ConstraintValidator>) annotation
						.annotationType().getAnnotation(ValidationAnnotation.class).getValidatorClass();
				try {
					ConstraintValidator validator = validationClass.getConstructor().newInstance();
					Optional<String> errorMessage = validator.valid(field, object);
					if (errorMessage.isPresent()) {
						result.addError(field.getName(), errorMessage.get());
					} else {
						result.removeError(field.getName());
					}
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
			});
		}
	}
}
