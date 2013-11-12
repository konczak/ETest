package pl.konczak.etest.facade.impl;

import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.entity.ClosedQuestion;
import pl.konczak.etest.facade.IClosedQuestionFacade;
import pl.konczak.etest.repository.IClosedQuestionRepository;

@Service
public class ClosedQuestionFacade implements IClosedQuestionFacade {

    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;

    @Transactional
    @Override
    public List<ClosedQuestion> searchAll() {
        return closedQuestionRepository.findAll();
    }

    @Transactional
    @Override
    public List<ClosedQuestion> searchAllWithQuestionLike(String question) {
        return closedQuestionRepository.findAllWithMatchingQuestion(question);
    }

    @Transactional
    @Override
    public ClosedQuestion find(Integer id) {
        return closedQuestionRepository.getById(id);
    }

    @Transactional
    @Override
    public void modify(ClosedQuestion closedQuestion) {
        Validate.notNull(closedQuestion.getId());
        closedQuestionRepository.save(closedQuestion);
    }

    @Transactional
    @Override
    public void remove(Integer id) {
        ClosedQuestion closedQuestion = closedQuestionRepository.getById(id);
        closedQuestionRepository.delete(closedQuestion);
    }
}
