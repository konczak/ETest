package pl.konczak.etest.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.repository.IUserGroupRepository;

@Transactional
@Repository
public class UserGroupRepository
        implements IUserGroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public UserGroupEntity getById(Integer id) {
        return entityManager.find(UserGroupEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserGroupEntity findByTitle(String title) {
        Query query = entityManager
                .createQuery("SELECT ug FROM UserGroupEntity AS ug WHERE ug.title = :title");
        query.setParameter("title", title);
        List<UserGroupEntity> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserGroupEntity> findAll() {
        Query query = entityManager.createQuery("SELECT ug FROM UserGroupEntity AS ug");
        return (List<UserGroupEntity>) query.getResultList();
    }

    @Override
    public void delete(UserGroupEntity userGroup) {
        entityManager.remove(userGroup);
    }

    @Override
    public void save(UserGroupEntity userGroup) {
        entityManager.persist(userGroup);
    }
}
