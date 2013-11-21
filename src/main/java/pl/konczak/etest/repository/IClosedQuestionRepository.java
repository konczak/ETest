package pl.konczak.etest.repository;

import java.util.List;

import pl.konczak.etest.entity.ClosedQuestionEntity;

public interface IClosedQuestionRepository {

    ClosedQuestionEntity getById(Integer id);

    List<ClosedQuestionEntity> findAll();

    List<ClosedQuestionEntity> findAllWithMatchingQuestion(String partOfQuestion);

    void save(ClosedQuestionEntity closedQuestion);

    void delete(ClosedQuestionEntity closedQuestion);
}
