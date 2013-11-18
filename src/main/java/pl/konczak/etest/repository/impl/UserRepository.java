package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.User;
import pl.konczak.etest.repository.IUserRepository;

@Transactional
@Repository
public class UserRepository
        implements IUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public User getById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User AS u WHERE u.email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM User AS u WHERE u.email = :email");
        query.setParameter("email", email);
        List<User> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
