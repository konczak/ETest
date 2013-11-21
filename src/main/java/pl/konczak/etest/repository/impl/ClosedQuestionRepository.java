package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.repository.IClosedQuestionRepository;

@Transactional
@Repository
public class ClosedQuestionRepository
        implements IClosedQuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public ClosedQuestionEntity getById(Integer id) {
        return entityManager.find(ClosedQuestionEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClosedQuestionEntity> findAll() {
        Query query = entityManager.createQuery("SELECT cq FROM ClosedQuestionEntity AS cq");
        return (List<ClosedQuestionEntity>) query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClosedQuestionEntity> findAllWithMatchingQuestion(String partOfQuestion) {
        Query query = entityManager.createQuery(
                "SELECT cq FROM ClosedQuestionEntity AS cq WHERE cq.question LIKE :partOfQuestion");
        query.setParameter("partOfQuestion", "%" + partOfQuestion + "%");
        return (List<ClosedQuestionEntity>) query.getResultList();
    }

    @Override
    public void save(ClosedQuestionEntity closedQuestion) {
        entityManager.persist(closedQuestion);
    }

    @Override
    public void delete(ClosedQuestionEntity closedQuestion) {
        entityManager.remove(closedQuestion);
    }
}
