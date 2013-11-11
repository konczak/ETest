package pl.konczak.etest.repository;

import java.util.List;
import pl.konczak.etest.entity.CategoryOfQuestion;

public interface ICategoryOfQuestionRepository {

    CategoryOfQuestion getById(Integer id);

    List<CategoryOfQuestion> findAll();

    CategoryOfQuestion findByTitle(String title);

    void save(CategoryOfQuestion categoryOfQuestion);

    void delete(CategoryOfQuestion categoryOfQuestion);
}
