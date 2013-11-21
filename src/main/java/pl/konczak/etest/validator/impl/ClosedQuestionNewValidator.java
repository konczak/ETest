package pl.konczak.etest.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import pl.konczak.etest.dto.question.closedQuestion.ClosedQuestionNew;

@Component
public class ClosedQuestionNewValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return ClosedQuestionNew.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);
        if (errors.hasErrors()) {
            return;
        }

        //TODO validate optional file
    }
}
