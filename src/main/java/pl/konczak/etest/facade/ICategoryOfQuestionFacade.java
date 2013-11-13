package pl.konczak.etest.facade;

import java.util.List;

import pl.konczak.etest.entity.CategoryOfQuestion;

public interface ICategoryOfQuestionFacade {

    List<CategoryOfQuestion> searchAll();

    CategoryOfQuestion find(Integer id);

    CategoryOfQuestion add(CategoryOfQuestion categoryOfQuestion);

    void modify(CategoryOfQuestion categoryOfQuestion);

    void remove(Integer id);
}
