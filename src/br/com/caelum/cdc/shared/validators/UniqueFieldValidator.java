package br.com.caelum.cdc.shared.validators;

import java.util.Optional;

import br.com.caelum.cdc.shared.errors.BindingResult;

public abstract class UniqueFieldValidator<T> {
	
	public void validate(Object object, BindingResult result) {
		
		Optional<?> possibleField = getFieldToSearch((T) object);
		
        if (possibleField.isPresent()) {
            String invalidField = getInvalidField();
            result.addError(invalidField, " Já existe um " + invalidField + " com esse nome!");
        }
	}
	
	public abstract Optional<T> getFieldToSearch(T object);

    public abstract String getInvalidField();
}
