package pl.konczak.etest.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import pl.konczak.etest.dto.question.closedAnswer.ClosedAnswerNew;

@Component
public class ClosedAnswerNewValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ClosedAnswerNew.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);
        if (errors.hasErrors()) {
            return;
        }
        //TODO validate optional file
        ClosedAnswerNew closedAnswerNew = (ClosedAnswerNew) target;

    }
}
