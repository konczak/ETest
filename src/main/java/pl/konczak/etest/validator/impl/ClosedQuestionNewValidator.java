package pl.konczak.etest.validator.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartFile;

import pl.konczak.etest.dto.question.closedQuestion.ClosedQuestionNew;

@Component
public class ClosedQuestionNewValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    private static final Integer MAXIMAL_IMAGE_BYTES_SIZE = 100000;

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

        ClosedQuestionNew closedQuestionNew = (ClosedQuestionNew) target;
        MultipartFile multipartFile = closedQuestionNew.getMultipartFile();

        if (multipartFile != null) {
            if (isFileTooBig(multipartFile)) {
                errors.rejectValue("multipartFile", "closedQuestion.multipartFile.tooBig");
            }
        }

    }

    private boolean isFileTooBig(MultipartFile multipartFile) {
        boolean isFileTooBig = false;
        try {
            isFileTooBig = multipartFile.getBytes().length > MAXIMAL_IMAGE_BYTES_SIZE;
        } catch (IOException e) {
        }
        return isFileTooBig;
    }
}
