package com.cybercom.telep.etest.repository.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cybercom.telep.etest.entity.User;
import com.cybercom.telep.etest.repository.IUserRepository;

@Transactional
@Repository
public class UserRepository
        implements IUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByEmail(String email) {
        System.out.println("szukam email <" + email + ">");
        Query query = entityManager.createQuery("SELECT u FROM User AS u WHERE u.email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
