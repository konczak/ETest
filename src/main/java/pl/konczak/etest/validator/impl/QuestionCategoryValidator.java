package pl.konczak.etest.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.konczak.etest.dto.QuestionCategory;

import pl.konczak.etest.dto.UserRegistration;
import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.entity.User;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class QuestionCategoryValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return QuestionCategory.class.isAssignableFrom(clazz);
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
        QuestionCategory questionCategory = (QuestionCategory) target;

        String title = questionCategory.getTitle();

        CategoryOfQuestion categoryOfQuestion = categoryOfQuestionRepository.findByTitle(title);

        if (categoryOfQuestion != null) {
            errors.rejectValue("title", "questionCategory.title.alreadyTaken");
        }
    }
}
