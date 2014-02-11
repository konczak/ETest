package pl.konczak.etest.bo.impl;

import java.util.Set;


import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IExamBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.repository.IExamRepository;
import pl.konczak.etest.repository.ITestTemplateRepository;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.repository.IUserRepository;
import pl.konczak.etest.strategy.teacher.IExamGenerateStrategy;
import pl.konczak.etest.strategy.teacher.IUserExamCheckStrategy;

@Service
public class ExamBO
        implements IExamBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExamBO.class);
    @Autowired
    private ITestTemplateRepository testTemplateRepository;
    @Autowired
    private IUserGroupRepository userGroupRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IExamRepository examRepository;
    @Autowired
    private IExamGenerateStrategy examGenerateStrategy;
    @Autowired
    private IUserExamCheckStrategy userExamCheckStrategy;

    @Transactional
    @Override
    public ExamEntity add(Integer testTemplateId, Integer userGroupId, String titleSufix, Integer examinerId,
            LocalDateTime activeFrom, LocalDateTime activeTo,
            Integer maxClosedQuestionsPerExam, Integer maxClosedAnswersPerClosedQuestion) {
        Validate.notNull(testTemplateId);
        Validate.notNull(userGroupId);
        Validate.notNull(examinerId);
        Validate.notNull(activeFrom);
        Validate.notNull(activeTo);
        Validate.isTrue(activeFrom.isBefore(activeTo));

        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(testTemplateId);
        UserGroupEntity userGroupEntity = userGroupRepository.getById(userGroupId);
        UserEntity userEntity = userRepository.getById(examinerId);

        ExamEntity examEntity = new ExamEntity(testTemplateEntity, userGroupEntity, titleSufix,
                userEntity, activeFrom, activeTo);

        examGenerateStrategy.generateUserExams(examEntity, maxClosedQuestionsPerExam,
                maxClosedAnswersPerClosedQuestion);

        examRepository.save(examEntity);

        LOGGER.info("Add Exam <{}>", examEntity.getId());

        return examEntity;
    }

    @Transactional
    @Override
    public void check(Integer examId) {
        Validate.notNull(examId);
        ExamEntity examEntity = examRepository.getById(examId);

        Set<UserExamEntity> userExamEntityies = examEntity.getGeneratedExams();
        for (UserExamEntity userExamEntity : userExamEntityies) {
            userExamCheckStrategy.check(userExamEntity);
        }
        examEntity.markAsChecked();

        examRepository.save(examEntity);

        LOGGER.info("Exam <{}> checked", examEntity.getId());
    }

    @Transactional
    @Override
    public void prolong(Integer examId, Seconds seconds) {
        Validate.notNull(examId);
        LocalDateTime now = LocalDateTime.now();
        ExamEntity examEntity = examRepository.getById(examId);
        Validate.isTrue(examEntity.getActiveTo().isAfter(now),
                String.format("Exam <%s> is no longer active and cannot be prolonged", examId));

        examEntity.prolongExam(seconds);

        examRepository.save(examEntity);

        LOGGER.info(
                "Exam <{}> has been prolonged about <{}> seconds and will be active up to <{}>",
                examEntity.getId(), seconds.getSeconds(), examEntity.getActiveTo());
    }

    @Transactional
    @Override
    public void terminate(Integer examId) {
        Validate.notNull(examId);
        ExamEntity examEntity = examRepository.getById(examId);

        examEntity.terminateExam();

        examRepository.save(examEntity);

        LOGGER.info("Exam <{}> has been terminated", examEntity.getId());
    }
}
