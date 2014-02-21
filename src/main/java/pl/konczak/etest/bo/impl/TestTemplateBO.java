package pl.konczak.etest.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.ITestTemplateBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.TestTemplateClosedQuestionEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.error.ClosedQuestionCode;
import pl.konczak.etest.error.SystemException;
import pl.konczak.etest.repository.IClosedQuestionRepository;
import pl.konczak.etest.repository.ITestTemplateRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class TestTemplateBO
        implements ITestTemplateBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTemplateBO.class);
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITestTemplateRepository testTemplateRepository;
    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;

    @Transactional
    @Override
    public TestTemplateEntity add(String subject, Integer authorId) {
        Validate.notEmpty(subject);
        Validate.notNull(authorId);
        UserEntity author = userRepository.getById(authorId);
        TestTemplateEntity testTemplateEntity =
                new TestTemplateEntity(subject, author);

        testTemplateRepository.save(testTemplateEntity);

        LOGGER.info("Add TestTemplate <{}>", testTemplateEntity.getId());
        return testTemplateEntity;
    }

    @Transactional
    @Override
    public TestTemplateEntity addClosedQuestion(Integer id, Integer closedQuestionId) {
        Validate.notNull(id);
        Validate.notNull(closedQuestionId);
        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(id);
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository.getById(closedQuestionId);

        validateClosedQuestionHasAnyAnswer(closedQuestionEntity);
        validateClosedQuestionHasAnyAtLeastOneCorrectAnswer(closedQuestionEntity);

        testTemplateEntity.addClosedQuestion(closedQuestionEntity);

        testTemplateRepository.save(testTemplateEntity);

        LOGGER.info("Add ClosedQuestion <{}> to TestTemplate <{}>",
                closedQuestionEntity.getId(), testTemplateEntity.getId());

        return testTemplateEntity;
    }

    @Transactional
    @Override
    public TestTemplateEntity removeClosedQuestion(Integer id, Integer closedQuestionId) {
        Validate.notNull(id);
        Validate.notNull(closedQuestionId);

        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(id);

        TestTemplateClosedQuestionEntity testTemplateClosedQuestionEntity =
                testTemplateEntity.getClosedQuestion(closedQuestionId);
        testTemplateEntity.getClosedQuestions().remove(testTemplateClosedQuestionEntity);
        testTemplateRepository.save(testTemplateEntity);

        LOGGER.info("Removed ClosedQuestion <{}> from TestTemplate <{}>",
                closedQuestionId, testTemplateEntity.getId());

        return testTemplateEntity;
    }

    @Transactional
    @Override
    public TestTemplateEntity changeClosedQuestionStatusOfMandatory(Integer id, Integer closedQuestionId,
            boolean mandatory) {
        Validate.notNull(id);
        Validate.notNull(closedQuestionId);
        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(id);

        if (mandatory) {
            testTemplateEntity.markClosedQuestionAsMandatory(closedQuestionId);
        } else {
            testTemplateEntity.markClosedQuestionAsNotMandatory(closedQuestionId);
        }
        testTemplateRepository.save(testTemplateEntity);

        LOGGER.info(
                "Change mandatory status for ClosedQuestion <{}> in TestTemplate <{}> to <{}>",
                closedQuestionId, testTemplateEntity.getId(), mandatory);

        return testTemplateEntity;
    }

    @Transactional
    @Override
    public void remove(Integer id) {
        Validate.notNull(id);
        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(id);

        testTemplateRepository.delete(testTemplateEntity);

        LOGGER.info("Removed TestTemplate <{}>", testTemplateEntity.getId());
    }

    private void validateClosedQuestionHasAnyAnswer(ClosedQuestionEntity closedQuestionEntity) {
        if (closedQuestionEntity.getClosedAnswers().isEmpty()) {
            throw new SystemException(ClosedQuestionCode.DOES_NOT_HAVE_ANSWERS)
                    .add("id", closedQuestionEntity.getId());
        }
    }

    private void validateClosedQuestionHasAnyAtLeastOneCorrectAnswer(ClosedQuestionEntity closedQuestionEntity) {
        if (closedQuestionEntity.getCorrectClosedAnswers().isEmpty()) {
            throw new SystemException(ClosedQuestionCode.DOES_NOT_HAVE_ANY_CORRECT_ANSWERS)
                    .add("id", closedQuestionEntity.getId());
        }
    }
}
