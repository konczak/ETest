package pl.konczak.etest.assembler.teacher.testTemplate;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.dto.teacher.TestTemplateClosedQuestions;
import pl.konczak.etest.dto.teacher.TestTemplatePreview;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.TestTemplateClosedQuestionEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IClosedQuestionRepository;
import pl.konczak.etest.repository.ITestTemplateRepository;

@Component
public class TestTemplateAssembler {

    @Autowired
    private ITestTemplateRepository testTemplateRepository;
    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;

    @Transactional(readOnly = true)
    public TestTemplatePreview toPreview(Integer id) {
        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(id);

        TestTemplatePreview testTemplatePreview = new TestTemplatePreview();
        testTemplatePreview.setId(testTemplateEntity.getId());
        testTemplatePreview.setSubject(testTemplateEntity.getSubject());

        UserEntity author = testTemplateEntity.getAuthor();
        UserPersonalDataEntity userPersonalDataEntity = author.getUserPersonalData();

        testTemplatePreview.setAuthorId(author.getId());
        testTemplatePreview.setAuthorFirstname(userPersonalDataEntity.getFirstname());
        testTemplatePreview.setAuthorLastname(userPersonalDataEntity.getLastname());

        Set<TestTemplateClosedQuestionEntity> testTemplateClosedQuestionEntities =
                testTemplateEntity.getClosedQuestions();

        for (TestTemplateClosedQuestionEntity testTemplateClosedQuestionEntity : testTemplateClosedQuestionEntities) {
            ClosedQuestionEntity closedQuestionEntity =
                    testTemplateClosedQuestionEntity.getClosedQuestionEntity();
            testTemplatePreview.addClosedQuestion(closedQuestionEntity.getId(),
                    closedQuestionEntity.getQuestion(),
                    testTemplateClosedQuestionEntity.isMandatory());
        }

        return testTemplatePreview;
    }

    @Transactional(readOnly = true)
    public TestTemplateClosedQuestions toClosedQuestions(Integer id) {
        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(id);

        TestTemplateClosedQuestions testTemplateClosedQuestions =
                new TestTemplateClosedQuestions(testTemplateEntity.getId(),
                testTemplateEntity.getSubject());

        for (ClosedQuestionEntity closedQuestionEntity : closedQuestionRepository.findAll()) {
            testTemplateClosedQuestions.addClosedQuestion(closedQuestionEntity.getId(),
                    closedQuestionEntity.getQuestion());
        }

        for (TestTemplateClosedQuestionEntity testTemplateClosedQuestionEntity : testTemplateEntity.getClosedQuestions()) {
            Integer closedQuestionAlreadyInId = testTemplateClosedQuestionEntity.getClosedQuestionEntity().getId();
            testTemplateClosedQuestions.markClosedQuestionAsAlreadyIn(closedQuestionAlreadyInId);
            if (testTemplateClosedQuestionEntity.isMandatory()) {
                testTemplateClosedQuestions.markClosedQuestionAsMandatory(closedQuestionAlreadyInId);
            }
        }

        return testTemplateClosedQuestions;
    }
}
