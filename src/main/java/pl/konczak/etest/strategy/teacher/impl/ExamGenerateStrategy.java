package pl.konczak.etest.strategy.teacher.impl;

import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.strategy.teacher.IExamGenerateStrategy;

public class ExamGenerateStrategy
        implements IExamGenerateStrategy {

    private Integer closedQuestionsCountPerUserTest;
    private Integer closedAnswersCountPerClosedQuestion;

    public ExamGenerateStrategy(Integer closedQuestionsCountPerUserTest,
            Integer closedAnswersCountPerClosedQuestion) {
        Validate.notNull(closedQuestionsCountPerUserTest);
        Validate.notNull(closedAnswersCountPerClosedQuestion);
        this.closedQuestionsCountPerUserTest = closedQuestionsCountPerUserTest;
        this.closedAnswersCountPerClosedQuestion = closedAnswersCountPerClosedQuestion;
    }

    @Transactional(readOnly = true)
    @Override
    public ExamEntity generateUserExams(ExamEntity examEntity) {
        Validate.notNull(examEntity);
        Validate.notNull(examEntity.getTestTemplate());
        Validate.notNull(examEntity.getUserGroup());

        Set<UserEntity> examinedUsers = examEntity.getUserGroup().getMembers();

        Validate.isFalse(examinedUsers.isEmpty(), "Choosen UserGroup has no members");
        Validate.isTrue(examEntity.getGeneratedExams().isEmpty(), "UserExams have been already generated");

        Set<ClosedQuestionEntity> mandatoryClosedQuestions =
                examEntity.getTestTemplate().getMandatoryClosedQuestions();
        Set<ClosedQuestionEntity> notMandatoryClosedQuestions =
                examEntity.getTestTemplate().getNotMandatoryClosedQuestions();

        Validate.isTrue(closedQuestionsCountPerUserTest <= mandatoryClosedQuestions.size(),
                "Count of mandatory ClosedQuestions is bigger then count of ClosedQuestions per UserTest");

        for (UserEntity userEntity : examinedUsers) {
            Integer inputedClosedQuestions = 0;
        }

        return examEntity;
    }
}
