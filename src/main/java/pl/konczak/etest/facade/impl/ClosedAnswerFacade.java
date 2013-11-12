package pl.konczak.etest.facade.impl;

import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.entity.ClosedAnswer;
import pl.konczak.etest.facade.IClosedAnswerFacade;
import pl.konczak.etest.repository.IClosedAnswerRepository;

@Service
public class ClosedAnswerFacade implements IClosedAnswerFacade {

    @Autowired
    private IClosedAnswerRepository closedAnswerRepository;

    @Transactional
    @Override
    public List<ClosedAnswer> searchAll() {
        return closedAnswerRepository.findAll();
    }

    @Transactional
    @Override
    public ClosedAnswer find(Integer id) {
        return closedAnswerRepository.getById(id);
    }

    @Transactional
    @Override
    public List<ClosedAnswer> searchAllWithAnswerLike(String answer) {
        return closedAnswerRepository.findAllWithMatchingAnswer(answer);
    }

    @Transactional
    @Override
    public void modify(ClosedAnswer closedAnswer) {
        Validate.notNull(closedAnswer.getId());
        closedAnswerRepository.save(closedAnswer);
    }

    @Transactional
    @Override
    public void remove(Integer id) {
        ClosedAnswer closedAnswer = closedAnswerRepository.getById(id);
        closedAnswerRepository.delete(closedAnswer);
    }
}
