package pl.konczak.etest.facade;

import java.util.List;

import pl.konczak.etest.entity.ClosedAnswerEntity;

public interface IClosedAnswerFacade {

    List<ClosedAnswerEntity> searchAll();

    List<ClosedAnswerEntity> searchAllWithAnswerLike(String answer);

    ClosedAnswerEntity find(Integer id);
    
    ClosedAnswerEntity add(ClosedAnswerEntity closedAnswer);

    void modify(ClosedAnswerEntity closedAnswer);

    void remove(Integer id);
}
