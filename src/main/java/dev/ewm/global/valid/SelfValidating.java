package dev.ewm.global.valid;

import javax.validation.ValidatorFactory;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

public abstract class SelfValidating<T> {
	private Validator validator;
	
	public SelfValidating() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	protected void validateSelf() {
		Set<ConstraintViolation<T>> violations = validator.validate((T) this);
		
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}
}
