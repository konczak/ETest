package pl.konczak.etest.repository;

import java.util.List;
import pl.konczak.etest.entity.CategoryEntity;

public interface ICategoryRepository {

    CategoryEntity getById(Integer id);

    List<CategoryEntity> findAll();
    
    List<CategoryEntity> findAllWithoutParent();

    CategoryEntity findByName(String name);

    void save(CategoryEntity categoryEntity);

    void delete(CategoryEntity categoryEntity);
}
