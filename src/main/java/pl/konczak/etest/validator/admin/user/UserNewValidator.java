package pl.konczak.etest.validator.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import pl.konczak.etest.dto.user.UserNew;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class UserNewValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserNew.class.isAssignableFrom(clazz);
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
        UserNew userNew = (UserNew) target;

        String password = userNew.getPassword();
        String passwordConfirm = userNew.getPasswordConfirm();

        if (!password.equals(passwordConfirm)) {
            errors.rejectValue("password", "user.password.mismatch");
            errors.rejectValue("passwordConfirm", "user.password.mismatch");
        }
        UserEntity user = userRepository.findByEmail(userNew.getEmail());
        if (user != null) {
            errors.rejectValue("email", "user.email.alreadyTaken");
        }
    }
}
