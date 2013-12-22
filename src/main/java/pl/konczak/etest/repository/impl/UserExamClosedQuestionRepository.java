package pl.konczak.etest.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.UserExamClosedQuestionEntity;

import pl.konczak.etest.repository.IUserExamClosedQuestionRepository;

@Transactional
@Repository
public class UserExamClosedQuestionRepository
        implements IUserExamClosedQuestionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public UserExamClosedQuestionEntity getById(Integer id) {
        return entityManager.find(UserExamClosedQuestionEntity.class, id);
    }

    @Override
    public void save(UserExamClosedQuestionEntity userExamClosedQuestion) {
        entityManager.persist(userExamClosedQuestion);
    }
}
