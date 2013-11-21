package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.repository.IClosedAnswerRepository;

@Transactional
@Repository
public class ClosedAnswerRepository
        implements IClosedAnswerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public ClosedAnswerEntity getById(Integer id) {
        return entityManager.find(ClosedAnswerEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClosedAnswerEntity> findAll() {
        Query query = entityManager.createQuery("SELECT ca FROM ClosedAnswerEntity AS ca");
        return (List<ClosedAnswerEntity>) query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClosedAnswerEntity> findAllWithMatchingAnswer(String partOfAnswer) {
        Query query = entityManager.createQuery(
                "SELECT ca FROM ClosedAnswerEntity AS ca WHERE ca.answer LIKE :partOfAnswer");
        query.setParameter("partOfAnswer", "%" + partOfAnswer + "%");
        return (List<ClosedAnswerEntity>) query.getResultList();
    }

    @Override
    public void save(ClosedAnswerEntity closedAnswer) {
        entityManager.persist(closedAnswer);
    }

    @Override
    public void delete(ClosedAnswerEntity closedAnswer) {
        entityManager.remove(closedAnswer);
    }
}
