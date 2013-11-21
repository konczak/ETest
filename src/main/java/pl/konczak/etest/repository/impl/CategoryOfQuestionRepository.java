package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;

@Transactional
@Repository
public class CategoryOfQuestionRepository
        implements ICategoryOfQuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public CategoryOfQuestionEntity getById(Integer id) {
        return entityManager.find(CategoryOfQuestionEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryOfQuestionEntity> findAll() {
        Query query = entityManager.createQuery("SELECT coq FROM CategoryOfQuestionEntity AS coq");
        List<CategoryOfQuestionEntity> resultList = query.getResultList();
        return resultList;
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryOfQuestionEntity findByTitle(String title) {
        Query query = entityManager.createQuery(
                "SELECT coq FROM CategoryOfQuestionEntity AS coq WHERE coq.title = :title");
        query.setParameter("title", title);
        List<CategoryOfQuestionEntity> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void save(CategoryOfQuestionEntity categoryOfQuestion) {
        entityManager.persist(categoryOfQuestion);
    }

    @Override
    public void delete(CategoryOfQuestionEntity categoryOfQuestion) {
        entityManager.remove(categoryOfQuestion);
    }
}
