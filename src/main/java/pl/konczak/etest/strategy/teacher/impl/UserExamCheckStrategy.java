package pl.konczak.etest.strategy.teacher.impl;

import java.util.Set;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.UserExamClosedAnswerEntity;
import pl.konczak.etest.entity.UserExamClosedQuestionEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.strategy.teacher.IUserExamCheckStrategy;

/**
 * This strategy will grant 1 point per question only when all correct answers are checked and none of incorrect answer is checked.
 *
 * @author konczak
 */
@Component
public class UserExamCheckStrategy
        implements IUserExamCheckStrategy {

    @Transactional
    @Override
    public void check(UserExamEntity userExam) {
        Set<UserExamClosedQuestionEntity> userExamClosedQuestionEntities = userExam.getClosedQuestions();
        for (UserExamClosedQuestionEntity userExamClosedQuestionEntity : userExamClosedQuestionEntities) {
            boolean isAnsweredCorrectly = isAnsweredCorrectly(userExamClosedQuestionEntity);
            if (isAnsweredCorrectly) {
                userExamClosedQuestionEntity.grantPoints(1);
            }
        }
        userExam.markAsChecked();
    }

    private boolean isAnsweredCorrectly(UserExamClosedQuestionEntity userExamClosedQuestionEntity) {
        boolean isAnsweredCorrectly = true;
        Set<UserExamClosedAnswerEntity> userExamClosedAnswerEntities = userExamClosedQuestionEntity.getClosedAnswers();
        for (UserExamClosedAnswerEntity userExamClosedAnswerEntity : userExamClosedAnswerEntities) {
            //has user subbmited question
            if (!userExamClosedQuestionEntity.isSubbmited()) {
                isAnsweredCorrectly = false;
                break;
            }
            ClosedAnswerEntity closedAnswerEntity = userExamClosedAnswerEntity.getClosedAnswer();
            boolean userAnswered = userExamClosedAnswerEntity.isMarkedByUser();
            boolean isCorrect = closedAnswerEntity.isCorrect();
            //user marked true while it is false, or user marked false while it is true
            if ((userAnswered && !isCorrect)
                    || (isCorrect && !userAnswered)) {
                isAnsweredCorrectly = false;
                break;
            }
        }
        return isAnsweredCorrectly;
    }
}
