package pl.konczak.etest.bo;

import pl.konczak.etest.entity.ClosedAnswerEntity;

public interface IClosedAnswerBO {

    ClosedAnswerEntity add(Integer closedQuestionId, String answer, boolean correct);

    ClosedAnswerEntity addPicture(Integer closedAnswerId, byte[] image);

    void remove(Integer closedAnswerId);
}
