package pl.konczak.etest.facade;

import java.util.List;
import pl.konczak.etest.entity.ClosedAnswer;

public interface IClosedAnswerFacade {

    List<ClosedAnswer> searchAll();

    List<ClosedAnswer> searchAllWithAnswerLike(String answer);

    ClosedAnswer find(Integer id);

    void modify(ClosedAnswer closedAnswer);

    void remove(Integer id);
}
