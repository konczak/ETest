package pl.konczak.etest.repository;

import java.util.List;

import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;

public interface IClosedAnswerRepository {

    ClosedAnswerEntity getById(Integer id);

    List<ClosedAnswerEntity> findAll();

    List<ClosedAnswerEntity> findAllWithMatchingAnswer(String partOfAnswer);

    void save(ClosedAnswerEntity closedAnswer);

    void delete(ClosedAnswerEntity closedAnswer);
}
