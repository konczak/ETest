package pl.konczak.etest.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.entity.ClosedQuestion;
import pl.konczak.etest.repository.IClosedQuestionRepository;

@Transactional
@Repository
public class ClosedQuestionRepository
        implements IClosedQuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ClosedQuestion getById(Integer id) {
        return entityManager.find(ClosedQuestion.class, id);
    }

    @Override
    public List<ClosedQuestion> findAll() {
        Query query = entityManager.createQuery("SELECT cq FROM ClosedQuestion AS cq");
        return (List<ClosedQuestion>) query.getResultList();
    }

    @Override
    public List<ClosedQuestion> findAllWithMatchingQuestion(String partOfQuestion) {
        Query query = entityManager.createQuery("SELECT cq FROM ClosedQuestion AS cq WHERE cq.question LIKE :partOfQuestion");
        query.setParameter("partOfQuestion", "%" + partOfQuestion + "%");
        return (List<ClosedQuestion>) query.getResultList();
    }

    @Override
    public void save(ClosedQuestion closedQuestion) {
        entityManager.persist(closedQuestion);
    }

    @Override
    public void delete(ClosedQuestion closedQuestion) {
        entityManager.remove(closedQuestion);
    }
}
