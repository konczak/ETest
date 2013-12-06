package pl.konczak.etest.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.bo.ITestTemplateBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.TestTemplateClosedQuestionEntity;

import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.IClosedQuestionRepository;
import pl.konczak.etest.repository.ITestTemplateRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class TestTemplateBO
        implements ITestTemplateBO {

    private static final Logger LOGGER = Logger.getLogger(TestTemplateBO.class);
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

        LOGGER.info(String.format("Add TestTemplate <%s>", testTemplateEntity.getId()));
        return testTemplateEntity;
    }

    @Transactional
    @Override
    public TestTemplateEntity addClosedQuestion(Integer id, Integer closedQuestionId) {
        Validate.notNull(id);
        Validate.notNull(closedQuestionId);
        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(id);
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository.getById(closedQuestionId);

        testTemplateEntity.addClosedQuestion(closedQuestionEntity);

        testTemplateRepository.save(testTemplateEntity);

        LOGGER.info(String.format("Add ClosedQuestion <%s> to TestTemplate <%s>",
                closedQuestionEntity.getId(), testTemplateEntity.getId()));

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
        System.out.println("size before <" + testTemplateEntity.getClosedQuestions().size() + ">");
        testTemplateEntity.getClosedQuestions().remove(testTemplateClosedQuestionEntity);
        System.out.println("size after <" + testTemplateEntity.getClosedQuestions().size() + ">");
        testTemplateRepository.save(testTemplateEntity);

        LOGGER.info(String.format("Removed ClosedQuestion <%s> from TestTemplate <%s>",
                closedQuestionId, testTemplateEntity.getId()));

        return testTemplateEntity;
    }

    @Transactional
    @Override
    public TestTemplateEntity changeClosedQuestionStatusOfMandatory(Integer id, Integer closedQuestionId, boolean mandatory) {
        Validate.notNull(id);
        Validate.notNull(closedQuestionId);
        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(id);

        if (mandatory) {
            testTemplateEntity.markClosedQuestionAsMandatory(closedQuestionId);
        } else {
            testTemplateEntity.markClosedQuestionAsNotMandatory(closedQuestionId);
        }
        testTemplateRepository.save(testTemplateEntity);

        LOGGER.info(String.format("Change mandatory status for ClosedQuestion <%s> in TestTemplate <%s> to <%b>",
                closedQuestionId, testTemplateEntity.getId(), mandatory));

        return testTemplateEntity;
    }

    @Transactional
    @Override
    public void remove(Integer id) {
        Validate.notNull(id);
        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(id);

        testTemplateRepository.delete(testTemplateEntity);

        LOGGER.info(String.format("Removed TestTemplate <%s>", testTemplateEntity.getId()));
    }
}
