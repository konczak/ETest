package pl.konczak.etest.strategy.teacher.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.strategy.teacher.IExamGenerateStrategy;
import pl.konczak.etest.util.UnusedMapKeySearchUtil;

@Service
public class ExamGenerateStrategy
        implements IExamGenerateStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamGenerateStrategy.class);
    private Integer closedQuestionsCountPerUserTest = 10;
    private Integer closedAnswersCountPerClosedQuestion = 5;
    private static final Integer RANDOM_MAX = 10000;

    @Transactional
    @Override
    public ExamEntity generateUserExams(ExamEntity examEntity,
            Integer maxClosedQuestionsPerExam, Integer maxClosedAnswersPerClosedQuestion) {
        Validate.notNull(examEntity);
        Validate.notNull(examEntity.getTestTemplate());
        Validate.notNull(examEntity.getUserGroup());
        Validate.notNull(maxClosedQuestionsPerExam);
        Validate.notNull(maxClosedAnswersPerClosedQuestion);
        Validate.isTrue(maxClosedQuestionsPerExam > 0);
        Validate.isTrue(maxClosedAnswersPerClosedQuestion > 0);
        this.closedQuestionsCountPerUserTest = maxClosedQuestionsPerExam;
        this.closedAnswersCountPerClosedQuestion = maxClosedAnswersPerClosedQuestion;

        Set<UserEntity> examinedUsers = examEntity.getUserGroup().getMembers();

        Validate.isFalse(examinedUsers.isEmpty(), "Choosen UserGroup has no members");
        Validate.isTrue(examEntity.getGeneratedExams().isEmpty(), "UserExams have been already generated");

        TestTemplateEntity testTemplate = examEntity.getTestTemplate();

        for (UserEntity examined : examinedUsers) {
            LOGGER.debug("Preparing UserExam for <{}> id <{}>",
                    examined.getEmail(), examined.getId());

            //generate Exam per user
            Map<ClosedQuestionEntity, Set<ClosedAnswerEntity>> mapOfClosedQuestionsWithAnswers =
                    prepareIndividualUserExam(testTemplate);

            LOGGER.debug("Prepared <{}> closedAnswers for UserExam",
                    mapOfClosedQuestionsWithAnswers.size());

            examEntity.addUserExam(examined, mapOfClosedQuestionsWithAnswers);
        }

        return examEntity;
    }

    private Map<ClosedQuestionEntity, Set<ClosedAnswerEntity>> prepareIndividualUserExam(
            TestTemplateEntity testTemplate) {
        Map<ClosedQuestionEntity, Set<ClosedAnswerEntity>> mapOfClosedQuestionsWithAnswers =
                new HashMap<ClosedQuestionEntity, Set<ClosedAnswerEntity>>();

        Set<ClosedQuestionEntity> mandatoryClosedQuestions =
                testTemplate.getMandatoryClosedQuestions();
        randomizeOrderOfClosedQuestionsAndAnswers(mapOfClosedQuestionsWithAnswers, mandatoryClosedQuestions);

        Set<ClosedQuestionEntity> notMandatoryClosedQuestions =
                testTemplate.getNotMandatoryClosedQuestions();
        randomizeOrderOfClosedQuestionsAndAnswers(mapOfClosedQuestionsWithAnswers, notMandatoryClosedQuestions);

        return mapOfClosedQuestionsWithAnswers;
    }

    private void randomizeOrderOfClosedQuestionsAndAnswers(
            Map<ClosedQuestionEntity, Set<ClosedAnswerEntity>> mapOfClosedQuestionsWithAnswers,
            Set<ClosedQuestionEntity> closedQuestons) {
        Map<Integer, ClosedQuestionEntity> sortedNotMandatoryClosedQuestions =
                prepareRandomlySortedClosedQuestions(closedQuestons);
        for (Map.Entry<Integer, ClosedQuestionEntity> entry : sortedNotMandatoryClosedQuestions.entrySet()) {
            //prepare Set of ClosedAnswer per ClosedQuestion
            if (mapOfClosedQuestionsWithAnswers.size() >= closedQuestionsCountPerUserTest) {
                break;
            }
            ClosedQuestionEntity closedQuestionEntity = entry.getValue();
            Set<ClosedAnswerEntity> closedAnswerEntities = new HashSet<ClosedAnswerEntity>();

            Map<Integer, ClosedAnswerEntity> sortedClosedAnswers =
                    prepare(closedQuestionEntity);
            for (Map.Entry<Integer, ClosedAnswerEntity> ca : sortedClosedAnswers.entrySet()) {
                closedAnswerEntities.add(ca.getValue());
            }

            mapOfClosedQuestionsWithAnswers.put(closedQuestionEntity, closedAnswerEntities);
        }
    }

    private Map<Integer, ClosedQuestionEntity> prepareRandomlySortedClosedQuestions(
            Set<ClosedQuestionEntity> closedQuestions) {
        Map<Integer, ClosedQuestionEntity> map = new TreeMap<Integer, ClosedQuestionEntity>();
        Random generator = new Random(new Date().getTime());
        for (ClosedQuestionEntity closedQuestionEntity : closedQuestions) {
            int id = UnusedMapKeySearchUtil.findUnusedRandomKey(generator, RANDOM_MAX, map);
            map.put(id, closedQuestionEntity);
        }
        return map;
    }

    //handling ClosedAnswers per ClosedQuestion
    public Map<Integer, ClosedAnswerEntity> prepare(ClosedQuestionEntity closedQuestion) {
        Map<Integer, ClosedAnswerEntity> map = new TreeMap<Integer, ClosedAnswerEntity>();

        addAllCorrect(map, closedQuestion.getCorrectClosedAnswers());
        addAllIncorrect(map, closedQuestion.getIncorrectClosedAnswers());

        LOGGER.debug("Prepared <{}> closedAnswers set for closedQuestion <{}>",
                map.size(), closedQuestion.getId());

        return map;
    }

    private void addAllCorrect(Map<Integer, ClosedAnswerEntity> map,
            Set<ClosedAnswerEntity> correctClosedAnswers) {
        Validate.notEmpty(correctClosedAnswers, "Not found any correct ClosedAnswer!");
        fillUntilReachMax(map, correctClosedAnswers, closedAnswersCountPerClosedQuestion);
    }

    private void addAllIncorrect(Map<Integer, ClosedAnswerEntity> map,
            Set<ClosedAnswerEntity> incorrectClosedAnswers) {
        fillUntilReachMax(map, incorrectClosedAnswers, closedAnswersCountPerClosedQuestion);
    }

    private void fillUntilReachMax(Map<Integer, ClosedAnswerEntity> map, Set<ClosedAnswerEntity> closedAnswers,
            Integer maxOfClosedAnswers) {
        Random generator = new Random(new Date().getTime());
        for (ClosedAnswerEntity closedAnswerEntity : closedAnswers) {
            int id = UnusedMapKeySearchUtil.findUnusedRandomKey(generator, RANDOM_MAX, map);

            map.put(id, closedAnswerEntity);
            if (map.size() >= maxOfClosedAnswers) {
                break;
            }
        }
    }
}
