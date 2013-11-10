package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.Role;
import pl.konczak.etest.repository.IRoleRepository;

@Transactional
@Repository
public class RoleRepository
        implements IRoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> findAll() {
        Query query = entityManager.createQuery("SELECT r FROM Role AS r");
        List<Role> resultList = query.getResultList();
        return resultList;
    }
}
