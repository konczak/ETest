package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.IUserRepository;

@Transactional
@Repository
public class UserRepository
        implements IUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public UserEntity getById(Integer id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserEntity getByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM UserEntity AS u WHERE u.email = :email");
        query.setParameter("email", email);
        return (UserEntity) query.getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public UserEntity findByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM UserEntity AS u WHERE u.email = :email");
        query.setParameter("email", email);
        List<UserEntity> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void save(UserEntity user) {
        entityManager.persist(user);
    }
}
