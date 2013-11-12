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
    public List<ClosedQuestion> findAll() {
        Query query = entityManager.createQuery("SELECT cq FROM ClosedQuestion AS cq");
        List<ClosedQuestion> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public ClosedQuestion getById(Integer id) {
        Query query = entityManager.createQuery(
                "SELECT cq FROM ClosedQuestion AS cq WHERE cq.closedQuestionId = :id");
        query.setParameter("id", id);
        return (ClosedQuestion) query.getSingleResult();
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
