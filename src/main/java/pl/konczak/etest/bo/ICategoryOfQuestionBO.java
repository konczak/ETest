package pl.konczak.etest.bo;

import pl.konczak.etest.entity.CategoryOfQuestionEntity;

public interface ICategoryOfQuestionBO {

    CategoryOfQuestionEntity add(String title);

    CategoryOfQuestionEntity changeTitle(Integer categoryOfQuestionId, String title);

    void remove(Integer categoryOfQuestionId);
}
