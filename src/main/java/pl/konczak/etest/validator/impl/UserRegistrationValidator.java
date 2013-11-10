package pl.konczak.etest.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import pl.konczak.etest.dto.UserRegistration;
import pl.konczak.etest.entity.User;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class UserRegistrationValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistration.class.isAssignableFrom(clazz);
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
        UserRegistration userRegistration = (UserRegistration) target;

        String password = userRegistration.getPassword();
        String passwordConfirm = userRegistration.getPasswordConfirm();

        if (!password.equals(passwordConfirm)) {
            errors.rejectValue("password", "userRegistration.password.mismatch");
            errors.rejectValue("passwordConfirm", "userRegistration.password.mismatch");
        }
        User user = userRepository.findByEmail(userRegistration.getEmail());
        if (user != null) {
            errors.rejectValue("email", "userRegistration.email.alreadyTaken");
        }
    }
}
