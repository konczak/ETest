package pl.konczak.etest.repository;

import java.util.List;
import pl.konczak.etest.entity.ClosedQuestion;

public interface IClosedQuestionRepository {

    ClosedQuestion getById(Integer id);

    List<ClosedQuestion> findAll();

    List<ClosedQuestion> findAllWithMatchingQuestion(String partOfQuestion);

    void save(ClosedQuestion closedQuestion);

    void delete(ClosedQuestion closedQuestion);
}
