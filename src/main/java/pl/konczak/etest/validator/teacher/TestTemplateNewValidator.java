package pl.konczak.etest.validator.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.konczak.etest.dto.teacher.TestTemplateNew;
import pl.konczak.etest.repository.ITestTemplateRepository;

@Component
public class TestTemplateNewValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    @Autowired
    private ITestTemplateRepository testTemplateRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return TestTemplateNew.class.isAssignableFrom(clazz);
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
        TestTemplateNew testTemplateNew = (TestTemplateNew) target;

        String subject = testTemplateNew.getSubject();

        if (testTemplateRepository.findBySubject(subject) != null) {
            errors.rejectValue("subject", "testTemplate.subject.alreadyTaken");
        }
    }
}
