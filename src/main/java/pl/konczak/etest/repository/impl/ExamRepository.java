package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.repository.IExamRepository;

@Transactional
@Repository
public class ExamRepository
        implements IExamRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public ExamEntity getById(Integer id) {
        return entityManager.find(ExamEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExamEntity> findAll() {
        Query query = entityManager.createQuery("SELECT e FROM ExamEntity e");
        return (List<ExamEntity>) query.getResultList();
    }

    @Override
    public void save(ExamEntity testTemplate) {
        entityManager.persist(testTemplate);
    }

    @Override
    public void delete(ExamEntity testTemplate) {
        entityManager.remove(testTemplate);
    }
}
