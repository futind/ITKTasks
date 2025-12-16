package ru.itk.objectmapper.util.validation;

import jakarta.validation.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BeanValidator {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator;

    public BeanValidator() {
        this.validator = factory.getValidator();
    }

    public void validate(Object bean) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(bean);

        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
