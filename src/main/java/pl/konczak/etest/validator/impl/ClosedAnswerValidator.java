package pl.konczak.etest.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.entity.ClosedAnswer;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;
import pl.konczak.etest.repository.IClosedAnswerRepository;

@Component
public class ClosedAnswerValidator
        implements Validator {

    @Autowired
    private IClosedAnswerRepository closedAnswerRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClosedAnswer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClosedAnswer closedAnswer = (ClosedAnswer) target;

        Integer id = closedAnswer.getId();
        String answer = closedAnswer.getAnswer();

//        ClosedAnswer closedAnswerOther = closedAnswerRepository.findAllWithMatchingAnswer(answer);
//
//        if (categoryOfQuestionOther != null) {
//            if (id != null
//                    && categoryOfQuestionOther.getId().equals(id)) {
//                errors.rejectValue("title", "categoryOfQuestion.title.doesNotChanged");
//            } else {
//                errors.rejectValue("title", "categoryOfQuestion.title.alreadyTaken");
//            }
//        }
    }
}
