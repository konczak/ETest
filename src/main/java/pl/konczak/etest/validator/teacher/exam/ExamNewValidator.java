package pl.konczak.etest.validator.teacher.exam;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import pl.konczak.etest.dto.teacher.exam.ExamNew;

@Component
public class ExamNewValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    private static final String DATE_PATTERN = "YYYY-MM-dd HH:mm";
    private static final String DURATION_SEPARATOR = " - ";

    @Override
    public boolean supports(Class<?> clazz) {
        return ExamNew.class.isAssignableFrom(clazz);
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
        ExamNew testTemplateNew = (ExamNew) target;

        if (testTemplateNew.getTestTemplateId() == 0) {
            errors.rejectValue("testTemplateId", "exam.testTemplateId.notSelected");
        }

        if (testTemplateNew.getUserGroupId() == 0) {
            errors.rejectValue("userGroupId", "exam.userGroupId.notSelected");
        }

        System.out.println("testTemplateNew.getDuration() <" + testTemplateNew.getDuration() + ">");
        String[] durationArray = testTemplateNew.getDuration().split(DURATION_SEPARATOR);

        if (durationArray.length != 2) {
            errors.rejectValue("duration", "exam.duration.incorrect");
        }

        LocalDateTime activeFrom = null;
        LocalDateTime activeTo = null;
        try {
            activeFrom = LocalDateTime.parse(durationArray[0], DateTimeFormat.forPattern(DATE_PATTERN));
        } catch (RuntimeException e) {
            errors.rejectValue("duration", "exam.duration.incorrectActiveFrom");
        }

        try {
            activeTo = LocalDateTime.parse(durationArray[1], DateTimeFormat.forPattern(DATE_PATTERN));
        } catch (RuntimeException e) {
            errors.rejectValue("duration", "exam.duration.incorrectActiveTo");
        }

        if (activeFrom != null
                && activeTo != null) {
            rejectDurationWhenActiveFromIsAfterActiveTo(activeFrom, activeTo, errors);
            rejectDurationWhenBothDatesAreFromPast(activeFrom, activeTo, errors);
        }
    }

    private void rejectDurationWhenActiveFromIsAfterActiveTo(LocalDateTime activeFrom, LocalDateTime activeTo,
            Errors errors) {
        if (activeFrom.isAfter(activeTo)) {
            errors.rejectValue("duration", "exam.duration.incorrectActiveFromIsAfterActiveTo");
        }
    }

    private void rejectDurationWhenBothDatesAreFromPast(LocalDateTime activeFrom, LocalDateTime activeTo,
            Errors errors) {
        LocalDateTime now = LocalDateTime.now().minusMinutes(5);
        if (activeFrom.isBefore(now)
                && activeTo.isBefore(now)) {
            System.out.println("now <" + now + ">");
            System.out.println("activeFrom <" + activeFrom + ">");
            System.out.println("activeTo <" + activeTo + ">");
            errors.rejectValue("duration", "exam.duration.incorrectActiveFromIAndActiveToAreFromPast");
        }
    }
}
