package br.com.caelum.cdc.shared.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.caelum.cdc.shared.validators.MinNumberValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ValidationAnnotation(getValidatorClass = MinNumberValidator.class)
public @interface Min {
	int value();
}
