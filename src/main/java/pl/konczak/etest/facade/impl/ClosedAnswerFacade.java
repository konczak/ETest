package pl.konczak.etest.facade.impl;

import java.util.List;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.ClosedAnswerEntity;

import pl.konczak.etest.facade.IClosedAnswerFacade;
import pl.konczak.etest.repository.IClosedAnswerRepository;

@Service
public class ClosedAnswerFacade implements IClosedAnswerFacade {

    @Autowired
    private IClosedAnswerRepository closedAnswerRepository;

    @Transactional
    @Override
    public List<ClosedAnswerEntity> searchAll() {
        return closedAnswerRepository.findAll();
    }

    @Transactional
    @Override
    public List<ClosedAnswerEntity> searchAllWithAnswerLike(String answer) {
        return closedAnswerRepository.findAllWithMatchingAnswer(answer);
    }

    @Transactional
    @Override
    public ClosedAnswerEntity find(Integer id) {
        return closedAnswerRepository.getById(id);
    }

    @Transactional
    @Override
    public ClosedAnswerEntity add(ClosedAnswerEntity closedAnswer) {
        Validate.notNull(closedAnswer);
        Validate.isTrue(closedAnswer.getId() == null);

        closedAnswerRepository.save(closedAnswer);

        return closedAnswer;
    }

    @Transactional
    @Override
    public void modify(ClosedAnswerEntity closedAnswer) {
        Validate.notNull(closedAnswer.getId());

        ClosedAnswerEntity entity = closedAnswerRepository.getById(closedAnswer.getId());
        entity.setAnswer(closedAnswer.getAnswer());

        closedAnswerRepository.save(entity);
    }

    @Transactional
    @Override
    public void remove(Integer id) {
        ClosedAnswerEntity closedAnswer = closedAnswerRepository.getById(id);
        closedAnswerRepository.delete(closedAnswer);
    }
}
