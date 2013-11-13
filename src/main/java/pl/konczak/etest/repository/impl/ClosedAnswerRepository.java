package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.ClosedAnswer;
import pl.konczak.etest.repository.IClosedAnswerRepository;

@Transactional
@Repository
public class ClosedAnswerRepository
        implements IClosedAnswerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public ClosedAnswer getById(Integer id) {
        return entityManager.find(ClosedAnswer.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClosedAnswer> findAll() {
        Query query = entityManager.createQuery("SELECT ca FROM ClosedAnswer AS ca");
        return (List<ClosedAnswer>) query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClosedAnswer> findAllWithMatchingAnswer(String partOfAnswer) {
        Query query = entityManager.createQuery(
                "SELECT ca FROM ClosedAnswer AS ca WHERE ca.answer LIKE :partOfAnswer");
        query.setParameter("partOfAnswer", "%" + partOfAnswer + "%");
        return (List<ClosedAnswer>) query.getResultList();
    }

    @Override
    public void save(ClosedAnswer closedAnswer) {
        entityManager.persist(closedAnswer);
    }

    @Override
    public void delete(ClosedAnswer closedAnswer) {
        entityManager.remove(closedAnswer);
    }
}
