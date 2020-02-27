package br.com.caelum.cdc.shared.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.caelum.cdc.shared.validators.SizeVerificator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ValidationAnnotation(getValidatorClass = SizeVerificator.class)
public @interface Size {
	int min() default 0;
	int max();
}
