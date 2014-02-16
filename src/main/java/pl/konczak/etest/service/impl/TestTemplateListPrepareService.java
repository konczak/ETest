package pl.konczak.etest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.dto.teacher.testTemplate.TestTemplateListRow;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.ITestTemplateRepository;
import pl.konczak.etest.service.IDTORowListPrepareService;

@Service
public class TestTemplateListPrepareService
        implements IDTORowListPrepareService<TestTemplateListRow> {

    @Autowired
    private ITestTemplateRepository testTemplateRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TestTemplateListRow> findAll() {
        List<TestTemplateEntity> testTemplateEntities =
                testTemplateRepository.findAll();
        List<TestTemplateListRow> testTemplateListRows =
                new ArrayList<TestTemplateListRow>();

        for (TestTemplateEntity testTemplateEntity : testTemplateEntities) {
            TestTemplateListRow testTemplateListRow = new TestTemplateListRow();

            testTemplateListRow.setId(testTemplateEntity.getId());
            testTemplateListRow.setSubject(testTemplateEntity.getSubject());

            UserEntity autor = testTemplateEntity.getAuthor();
            UserPersonalDataEntity userPersonalDataEntity =
                    autor.getUserPersonalData();

            testTemplateListRow.setAuthorId(autor.getId());
            testTemplateListRow.setAuthorFirstname(
                    userPersonalDataEntity.getFirstname());
            testTemplateListRow.setAuthorLastname(
                    userPersonalDataEntity.getLastname());

            testTemplateListRows.add(testTemplateListRow);
        }


        return testTemplateListRows;
    }
}
