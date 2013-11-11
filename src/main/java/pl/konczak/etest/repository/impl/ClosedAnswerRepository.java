package pl.konczak.etest.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.entity.ClosedAnswer;
import pl.konczak.etest.entity.ClosedQuestion;
import pl.konczak.etest.repository.IClosedAnswerRepository;

@Transactional
@Repository
public class ClosedAnswerRepository implements IClosedAnswerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClosedAnswer getById(Integer id) {
        Query query = entityManager.createQuery("SELECT ca FROM ClosedAnswer AS ca WHERE ca.closedAnswerId = :id");
        query.setParameter("id", id);
        return (ClosedAnswer) query.getSingleResult();
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
