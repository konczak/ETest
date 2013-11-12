package pl.konczak.etest.facade;

import java.util.List;

import pl.konczak.etest.dto.QuestionCategory;
import pl.konczak.etest.entity.CategoryOfQuestion;

public interface ICategoryOfQuestionFacade {

    List<CategoryOfQuestion> searchAll();

    CategoryOfQuestion find(Integer id);

    CategoryOfQuestion add(QuestionCategory questionCategory);

    void modify(CategoryOfQuestion categoryOfQuestion);

    void remove(Integer id);
}
