package pl.konczak.etest.repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.entity.CategoryEntity;
import pl.konczak.etest.repository.ICategoryRepository;

@Transactional
@Repository
public class CategoryRepository
        implements ICategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public CategoryEntity getById(Integer id) {
        return entityManager.find(CategoryEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryEntity> findAll() {
        Query query = entityManager.createQuery("SELECT c FROM CategoryEntity AS c");
        List<CategoryEntity> resultList = query.getResultList();
        return resultList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryEntity> findAllWithoutParent() {
        Query query = entityManager.createQuery("SELECT c FROM CategoryEntity AS c WHERE c.parent IS NULL ORDER BY c.name ASC");
        List<CategoryEntity> resultList = query.getResultList();
        return resultList;
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryEntity findByName(String name) {
        Query query = entityManager.createQuery(
                "SELECT c FROM CategoryEntity AS c WHERE c.name = :name");
        query.setParameter("name", name);
        List<CategoryEntity> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Transactional(readOnly = true)
    @Override
    public void save(CategoryEntity categoryEntity) {
        entityManager.persist(categoryEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public void delete(CategoryEntity categoryEntity) {
        entityManager.remove(categoryEntity);
    }
}
