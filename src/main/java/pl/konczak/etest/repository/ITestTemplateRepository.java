package pl.konczak.etest.repository;

import java.util.List;

import pl.konczak.etest.entity.TestTemplateEntity;

public interface ITestTemplateRepository {

    TestTemplateEntity getById(Integer id);

    List<TestTemplateEntity> findAll();

    TestTemplateEntity findBySubject(String subject);

    void save(TestTemplateEntity testTemplate);

    void delete(TestTemplateEntity testTemplate);
}
