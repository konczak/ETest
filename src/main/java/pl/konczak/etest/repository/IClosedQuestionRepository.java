package pl.konczak.etest.repository;

import java.util.List;
import pl.konczak.etest.entity.ClosedQuestion;

public interface IClosedQuestionRepository {

    List<ClosedQuestion> findAll();

    ClosedQuestion getById(Integer id);

    void save(ClosedQuestion closedQuestion);

    void delete(ClosedQuestion closedQuestion);
}
