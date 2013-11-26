package pl.konczak.etest.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import pl.konczak.etest.dto.user.group.UserGroupNew;
import pl.konczak.etest.repository.IUserGroupRepository;

@Component
public class UserGroupNewValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    @Autowired
    private IUserGroupRepository userGroupRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserGroupNew.class.isAssignableFrom(clazz);
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
        UserGroupNew userGroupNew = (UserGroupNew) target;

        String title = userGroupNew.getTitle();

        if (userGroupRepository.findByTitle(title) != null) {
            errors.rejectValue("title", "userGroup.title.alreadyTaken");
        }
    }
}
