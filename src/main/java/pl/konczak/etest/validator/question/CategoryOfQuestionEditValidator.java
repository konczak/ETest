package pl.konczak.etest.validator.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import pl.konczak.etest.dto.question.category.CategoryOfQuestionEdit;
import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;

@Component
public class CategoryOfQuestionEditValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryOfQuestionEdit.class.isAssignableFrom(clazz);
    }

    @Transactional(readOnly = true)
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
        CategoryOfQuestionEdit categoryOfQuestionEdit = (CategoryOfQuestionEdit) target;

        Integer id = categoryOfQuestionEdit.getId();
        String title = categoryOfQuestionEdit.getTitle();

        CategoryOfQuestionEntity categoryOfQuestionEntity = categoryOfQuestionRepository.getById(id);

        if (categoryOfQuestionEntity.getTitle().equals(title)) {
            errors.rejectValue("title", "categoryOfQuestion.title.doesNotChanged");
        }

        CategoryOfQuestionEntity categoryOfQuestionEntityOther =
                categoryOfQuestionRepository.findByTitle(title);

        if (categoryOfQuestionEntityOther != null
                && !categoryOfQuestionEntityOther.getId().equals(id)) {
            errors.rejectValue("title", "categoryOfQuestion.title.alreadyTaken");
        }
    }
}
