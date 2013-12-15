package pl.konczak.etest.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.UserExamEntity;

import pl.konczak.etest.repository.IUserExamRepository;

@Transactional
@Repository
public class UserExamRepository
        implements IUserExamRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public UserExamEntity getById(Integer id) {
        return entityManager.find(UserExamEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserExamEntity> findAll() {
        Query query = entityManager.createQuery("SELECT ue FROM UserExamEntity ue");
        return (List<UserExamEntity>) query.getResultList();
    }

    @Override
    public void save(UserExamEntity userExam) {
        entityManager.persist(userExam);
    }

    @Override
    public void delete(UserExamEntity userExam) {
        entityManager.remove(userExam);
    }
}
