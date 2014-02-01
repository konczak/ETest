package pl.konczak.etest.controller.teacher.closedAnswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.bo.IClosedAnswerBO;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.repository.IClosedAnswerRepository;

@Controller
@RequestMapping("teacher/closedAnswer/delete/{id}")
public class ClosedAnswerDeleteController {

    private static final String REDIRECT_TO_PREVIEW = "redirect:/teacher/closedQuestion/%s";
    @Autowired
    private IClosedAnswerRepository closedAnswerRepository;
    @Autowired
    private IClosedAnswerBO closedAnswerBO;

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer closedAnswerId) {
        ClosedAnswerEntity closedAnswerEntity = closedAnswerRepository.getById(closedAnswerId);
        ClosedQuestionEntity closedQuestionEntity = closedAnswerEntity.getClosedQuestion();

        closedAnswerBO.remove(closedAnswerId);

        return String.format(REDIRECT_TO_PREVIEW, closedQuestionEntity.getId());
    }
}
