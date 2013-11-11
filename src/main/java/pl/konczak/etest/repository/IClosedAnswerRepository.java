package pl.konczak.etest.repository;

import java.util.List;
import pl.konczak.etest.entity.ClosedAnswer;
import pl.konczak.etest.entity.ClosedQuestion;

public interface IClosedAnswerRepository {

    ClosedAnswer getById(Integer id);

    void save(ClosedAnswer closedAnswer);

    void delete(ClosedAnswer closedAnswer);
}
