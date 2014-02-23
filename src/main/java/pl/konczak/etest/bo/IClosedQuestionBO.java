package pl.konczak.etest.bo;

import pl.konczak.etest.entity.ClosedQuestionEntity;

public interface IClosedQuestionBO {

    ClosedQuestionEntity add(String question, Integer authorId, Integer categoryId);

    ClosedQuestionEntity addPicture(Integer closedQuestionId, byte[] image);

    void remove(Integer closedQuestionId);
}
