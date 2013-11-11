package pl.konczak.etest.facade;

import java.util.List;
import pl.konczak.etest.dto.QuestionCategory;
import pl.konczak.etest.entity.CategoryOfQuestion;

public interface ICategoryOfQuestionFacade {

    List<CategoryOfQuestion> searchAll();

    CategoryOfQuestion add(QuestionCategory questionCategory);

    void remove(Integer id);
}
