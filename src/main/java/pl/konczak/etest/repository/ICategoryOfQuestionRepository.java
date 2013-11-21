package pl.konczak.etest.repository;

import java.util.List;

import pl.konczak.etest.entity.CategoryOfQuestionEntity;

public interface ICategoryOfQuestionRepository {

    CategoryOfQuestionEntity getById(Integer id);

    List<CategoryOfQuestionEntity> findAll();

    CategoryOfQuestionEntity findByTitle(String title);

    void save(CategoryOfQuestionEntity categoryOfQuestion);

    void delete(CategoryOfQuestionEntity categoryOfQuestion);
}
