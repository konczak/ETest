package pl.konczak.etest.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IClosedAnswerBO;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.repository.IClosedAnswerRepository;
import pl.konczak.etest.repository.IClosedQuestionRepository;

@Service
public class ClosedAnswerBO
        implements IClosedAnswerBO {

    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;
    @Autowired
    private IClosedAnswerRepository closedAnswerRepository;

    @Transactional
    @Override
    public ClosedAnswerEntity add(Integer closedQuestionId, String answer, boolean correct) {
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository.getById(closedQuestionId);

        ClosedAnswerEntity closedAnswerEntity = new ClosedAnswerEntity(closedQuestionEntity, answer, correct);

        closedQuestionEntity.addClosedAnswer(closedAnswerEntity);
        closedAnswerRepository.save(closedAnswerEntity);

        return closedAnswerEntity;
    }

    @Override
    public ClosedAnswerEntity addPicture(Integer closedAnswerId, byte[] image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public void remove(Integer closedAnswerId) {
        ClosedAnswerEntity closedAnswerEntity = closedAnswerRepository.getById(closedAnswerId);

        closedAnswerRepository.delete(closedAnswerEntity);
    }
}
