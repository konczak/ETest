package pl.konczak.etest.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import pl.konczak.etest.dto.question.category.CategoryOfQuestionNew;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;

@Component
public class CategoryOfQuestionNewValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryOfQuestionNew.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);
        if (errors.hasErrors()) {
            return;
        }
        myValidation(target, errors);
    }

    @Override
    public void validate(Object target, Errors errors, Object... validationHints) {
        super.validate(target, errors, validationHints);
        if (errors.hasErrors()) {
            return;
        }
        myValidation(target, errors);
    }

    private void myValidation(Object target, Errors errors) {
        CategoryOfQuestionNew categoryOfQuestionNew = (CategoryOfQuestionNew) target;

        String title = categoryOfQuestionNew.getTitle();

        if (categoryOfQuestionRepository.findByTitle(title) != null) {
            errors.rejectValue("title", "categoryOfQuestion.title.alreadyTaken");
        }
    }
}
