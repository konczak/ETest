package pl.konczak.etest.validator.student.userExam;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.konczak.etest.dto.student.userExam.UserExamClosedQuestion;

import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserExamClosedAnswerEntity;
import pl.konczak.etest.entity.UserExamClosedQuestionEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.repository.IUserExamClosedQuestionRepository;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class UserExamClosedQuestionValidator
        extends LocalValidatorFactoryBean
        implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserExamClosedQuestionValidator.class);
    @Autowired
    private IUserExamClosedQuestionRepository userExamClosedQuestionRepository;
    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserExamClosedQuestion.class.isAssignableFrom(clazz);
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
        UserExamClosedQuestion userExamClosedQuestion = (UserExamClosedQuestion) target;

        UserExamClosedQuestionEntity userExamClosedQuestionEntity =
                userExamClosedQuestionRepository.getById(userExamClosedQuestion.getId());

        UserExamEntity userExamEntity = userExamClosedQuestionEntity.getUserExam();

        if (!isUserExamIdBelongsToLoggedUser(userExamEntity)) {
            errors.reject("userExamClosedQuestion.examinerIdAndSubbmitedIdDoesNotMatch.error");
            LOGGER.warn(
                    "Subbmited UserExamClosedQuestion is incorrect because userExaminedId <{}> while userLoggedId <{}>",
                    userExamEntity.getId(), getIdOfAuthenticatedUser());
        }
        hasAllClosedAnswersIncluded(userExamClosedQuestionEntity, userExamClosedQuestion, errors);
    }

    private boolean isUserExamIdBelongsToLoggedUser(UserExamEntity userExam) {
        Integer userId = getIdOfAuthenticatedUser();
        return userExam.getExamined().getId().equals(userId);
    }

    private Integer getIdOfAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.getByEmail(email);

        return user.getId();
    }

    private void hasAllClosedAnswersIncluded(UserExamClosedQuestionEntity userExamClosedQuestionEntity, UserExamClosedQuestion userExamClosedQuestion, Errors errors) {
        Set<UserExamClosedAnswerEntity> userExamClosedAnswerEntities =
                userExamClosedQuestionEntity.getClosedAnswers();
        List<UserExamClosedQuestion.UserExamClosedAnswer> userExamClosedAnswers =
                userExamClosedQuestion.getUserExamClosedAnswers();

        if (userExamClosedAnswerEntities.size()
                != userExamClosedAnswers.size()) {
            errors.rejectValue("userExamClosedAnswers",
                    "userExamClosedQuestion.userExamClosedAnswers.countOfAnswersDoesNotMatch");
            LOGGER.warn("Subbmited UserExamClosedQuestion is incorrect because countOfAnswersDoesNotMatch");
        }

        for (UserExamClosedAnswerEntity userExamClosedAnswerEntity : userExamClosedAnswerEntities) {
            if (!isUserExamClosedAnswerSubbmited(userExamClosedAnswerEntity.getId(), userExamClosedAnswers)) {
                errors.rejectValue("userExamClosedAnswers", "userExamClosedQuestion.userExamClosedAnswers.missingClosedAnswer");
                LOGGER.warn(
                        "Subbmited UserExamClosedQuestion is incorrect because UserExamClosedAnswer <{}> is missing",
                        userExamClosedAnswerEntity.getId());
                break;
            }
        }
    }

    private boolean isUserExamClosedAnswerSubbmited(Integer id,
            List<UserExamClosedQuestion.UserExamClosedAnswer> userExamClosedAnswers) {
        boolean isUserExamClosedAnswerSubbmited = false;

        for (UserExamClosedQuestion.UserExamClosedAnswer userExamClosedAnswer : userExamClosedAnswers) {
            if (userExamClosedAnswer.getId().equals(id)) {
                isUserExamClosedAnswerSubbmited = true;
            }
        }

        return isUserExamClosedAnswerSubbmited;
    }
}
