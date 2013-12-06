package pl.konczak.etest.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.repository.ITestTemplateRepository;

@Transactional
@Repository
public class TestTemplateRepository
        implements ITestTemplateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public TestTemplateEntity getById(Integer id) {
        return entityManager.find(TestTemplateEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TestTemplateEntity> findAll() {
        Query query = entityManager.createQuery("SELECT tt FROM TestTemplateEntity tt");
        return (List<TestTemplateEntity>) query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public TestTemplateEntity findBySubject(String subject) {
        Query query = entityManager.createQuery(
                "SELECT tt FROM TestTemplateEntity tt WHERE tt.subject = :subject");
        query.setParameter("subject", subject);
        List<TestTemplateEntity> list = (List<TestTemplateEntity>) query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void save(TestTemplateEntity testTemplate) {
        entityManager.persist(testTemplate);
    }

    @Override
    public void delete(TestTemplateEntity testTemplate) {
        entityManager.remove(testTemplate);
    }
}
