package pl.konczak.etest.bo.impl;

import java.util.Set;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IUserExamBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.UserExamClosedAnswerEntity;
import pl.konczak.etest.entity.UserExamClosedQuestionEntity;
import pl.konczak.etest.error.SystemException;
import pl.konczak.etest.error.UserExamCode;
import pl.konczak.etest.repository.IUserExamClosedQuestionRepository;
import pl.konczak.etest.vo.UserExamClosedAnswerVO;
import pl.konczak.etest.vo.UserExamClosedQuestionWithAnswersVO;

@Service
public class UserExamBO
        implements IUserExamBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserExamBO.class);
    @Autowired
    private IUserExamClosedQuestionRepository userExamClosedQuestionRepository;

    @Transactional
    @Override
    public void solve(UserExamClosedQuestionWithAnswersVO userExamClosedQuestionWithAnswersVO) {
        Validate.notNull(userExamClosedQuestionWithAnswersVO);
        LocalDateTime now = LocalDateTime.now().plusSeconds(20);

        UserExamClosedQuestionEntity userExamClosedQuestionEntity =
                userExamClosedQuestionRepository.getById(userExamClosedQuestionWithAnswersVO.getId());

        ExamEntity examEntity =
                userExamClosedQuestionEntity.getUserExam().getExam();

        validateExamIsActive(examEntity, now);
        validateClosedQuestionIsNotSubbmittedAlready(userExamClosedQuestionEntity);

        Set<UserExamClosedAnswerEntity> closedAnswers = userExamClosedQuestionEntity.getClosedAnswers();
        Set<UserExamClosedAnswerVO> closedAnswerVOs = userExamClosedQuestionWithAnswersVO.getClosedAnswers();
        Validate.isTrue(closedAnswers.size() == closedAnswerVOs.size());

        for (UserExamClosedAnswerVO userExamClosedAnswerVO : closedAnswerVOs) {
            userExamClosedQuestionEntity.markClosedAnswerByUser(
                    userExamClosedAnswerVO.getId(), userExamClosedAnswerVO.isCorrect());
        }

        userExamClosedQuestionEntity.markAsSubmitted();

        userExamClosedQuestionRepository.save(userExamClosedQuestionEntity);

        LOGGER.info("Solved UserExamClosedQuestion <{}>", userExamClosedQuestionWithAnswersVO);
    }

    private void validateExamIsActive(ExamEntity examEntity, LocalDateTime now) {
        if (!examEntity.getActiveTo().isAfter(now)) {
            throw new SystemException(UserExamCode.EXAM_ALREADY_FINISHED)
                    .add("id", examEntity.getId());
        }
    }

    private void validateClosedQuestionIsNotSubbmittedAlready(
            UserExamClosedQuestionEntity userExamClosedQuestionEntity) {
        if (userExamClosedQuestionEntity.isSubbmited()) {
            throw new SystemException(UserExamCode.QUESTION_ALREADY_ANSWERED)
                    .add("id", userExamClosedQuestionEntity.getId());
        }
    }
}
