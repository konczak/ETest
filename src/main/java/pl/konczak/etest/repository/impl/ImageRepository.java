package pl.konczak.etest.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.repository.IImageRepository;

@Transactional
@Repository
public class ImageRepository
        implements IImageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public ImageEntity getById(Integer id) {
        return entityManager.find(ImageEntity.class, id);
    }

    @Override
    public void save(ImageEntity image) {
        entityManager.persist(image);
    }
}
