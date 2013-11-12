package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;

@Transactional
@Repository
public class CategoryOfQuestionRepository
        implements ICategoryOfQuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CategoryOfQuestion getById(Integer id) {
        return entityManager.find(CategoryOfQuestion.class, id);
    }

    @Override
    public List<CategoryOfQuestion> findAll() {
        Query query = entityManager.createQuery("SELECT coq FROM CategoryOfQuestion AS coq");
        List<CategoryOfQuestion> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public CategoryOfQuestion findByTitle(String title) {
        Query query = entityManager.createQuery(
                "SELECT coq FROM CategoryOfQuestion AS coq WHERE coq.title = :title");
        query.setParameter("title", title);
        List<CategoryOfQuestion> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void save(CategoryOfQuestion categoryOfQuestion) {
        entityManager.persist(categoryOfQuestion);
    }

    @Override
    public void delete(CategoryOfQuestion categoryOfQuestion) {
        entityManager.remove(categoryOfQuestion);
    }
}
