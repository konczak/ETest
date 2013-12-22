package pl.konczak.etest.bo.impl;

import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.bo.IUserExamBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.UserExamClosedAnswerEntity;
import pl.konczak.etest.entity.UserExamClosedQuestionEntity;
import pl.konczak.etest.repository.IUserExamClosedQuestionRepository;
import pl.konczak.etest.vo.UserExamClosedAnswerVO;
import pl.konczak.etest.vo.UserExamClosedQuestionWithAnswersVO;

@Service
public class UserExamBO
        implements IUserExamBO {

    private static final Logger LOGGER = Logger.getLogger(UserExamBO.class);
    @Autowired
    private IUserExamClosedQuestionRepository userExamClosedQuestionRepository;

    @Transactional
    @Override
    public void solve(UserExamClosedQuestionWithAnswersVO userExamClosedQuestionWithAnswersVO) {
        Validate.notNull(userExamClosedQuestionWithAnswersVO);

        UserExamClosedQuestionEntity userExamClosedQuestionEntity =
                userExamClosedQuestionRepository.getById(userExamClosedQuestionWithAnswersVO.getId());

        Validate.isFalse(userExamClosedQuestionEntity.isSubbmited(), "UserExamClosedQuestion is already subbmited");
        Set<UserExamClosedAnswerEntity> closedAnswers = userExamClosedQuestionEntity.getClosedAnswers();
        Set<UserExamClosedAnswerVO> closedAnswerVOs = userExamClosedQuestionWithAnswersVO.getClosedAnswers();
        Validate.isTrue(closedAnswers.size() == closedAnswerVOs.size());

        for (UserExamClosedAnswerVO userExamClosedAnswerVO : closedAnswerVOs) {
            userExamClosedQuestionEntity.markClosedAnswerByUser(
                    userExamClosedAnswerVO.getId(), userExamClosedAnswerVO.isCorrect());
        }

        userExamClosedQuestionEntity.markAsSubmitted();

        userExamClosedQuestionRepository.save(userExamClosedQuestionEntity);

        LOGGER.info(String.format("Solved %s", userExamClosedQuestionWithAnswersVO));
    }
}
