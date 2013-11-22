package pl.konczak.etest.validator.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartFile;

import pl.konczak.etest.dto.question.closedAnswer.ClosedAnswerNew;

@Component
public class ClosedAnswerNewValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    private static final Integer MAXIMAL_IMAGE_BYTES_SIZE = 100000;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClosedAnswerNew.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);
        if (errors.hasErrors()) {
            return;
        }
        //TODO validate optional file
        ClosedAnswerNew closedAnswerNew = (ClosedAnswerNew) target;

        MultipartFile multipartFile = closedAnswerNew.getMultipartFile();

        if (multipartFile != null) {
            if (isFileTooBig(multipartFile)) {
                errors.rejectValue("multipartFile", "closedAnswerNew.multipartFile.tooBig");
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
