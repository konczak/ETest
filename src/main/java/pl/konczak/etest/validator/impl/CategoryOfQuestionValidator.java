package pl.konczak.etest.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;

@Component
public class CategoryOfQuestionValidator
        implements Validator {

    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryOfQuestion.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CategoryOfQuestion categoryOfQuestion = (CategoryOfQuestion) target;

        Integer id = categoryOfQuestion.getId();
        String title = categoryOfQuestion.getTitle();

        CategoryOfQuestion categoryOfQuestionOther = categoryOfQuestionRepository.findByTitle(title);

        if (categoryOfQuestionOther != null) {
            if (categoryOfQuestionOther.getId().equals(id)) {
                errors.rejectValue("title", "categoryOfQuestion.title.doesNotChanged");
            } else {
                errors.rejectValue("title", "categoryOfQuestion.title.alreadyTaken");
            }
        }
    }
}
