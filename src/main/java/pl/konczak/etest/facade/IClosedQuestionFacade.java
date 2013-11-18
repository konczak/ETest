package pl.konczak.etest.facade;

import java.util.List;

import pl.konczak.etest.entity.ClosedQuestion;

public interface IClosedQuestionFacade {

    List<ClosedQuestion> searchAll();

    List<ClosedQuestion> searchAllWithQuestionLike(String question);

    ClosedQuestion find(Integer id);

    ClosedQuestion add(ClosedQuestion closedQuestion);

    void modify(ClosedQuestion closedQuestion);

    void remove(Integer id);
}
