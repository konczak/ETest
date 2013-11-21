package pl.konczak.etest.controller.question.closedAnswer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import pl.konczak.etest.bo.IClosedAnswerBO;
import pl.konczak.etest.dto.question.closedAnswer.ClosedAnswerNew;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.repository.IClosedQuestionRepository;

@Controller
@RequestMapping("question/closedAnswer/new/{closedQuestionId}")
public class ClosedAnswerNewController {

    private static final String OBJECT = "closedAnswer";
    private static final String VIEW_NEW = "question/closedAnswer/new";
    private static final String REDIRECT_TO_PREVIEW = "redirect:/question/closedQuestion/%s";
    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;
    @Autowired
    private IClosedAnswerBO closedAnswerBO;

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model,
            @PathVariable("closedQuestionId") Integer closedQuestionId) {
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository
                .getById(closedQuestionId);

        ClosedAnswerNew closedAnswerNew = new ClosedAnswerNew();

        closedAnswerNew.setClosedQuestionId(closedQuestionEntity.getId());
        closedAnswerNew.setQuestion(closedQuestionEntity.getQuestion());

        //command object
        model.addAttribute(OBJECT, closedAnswerNew);

        //return form view
        return VIEW_NEW;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(
            @Valid @ModelAttribute(OBJECT) ClosedAnswerNew closedAnswerNew,
            @PathVariable("closedQuestionId") Integer closedQuestionId,
            BindingResult result, SessionStatus status) {
        String action = String.format(REDIRECT_TO_PREVIEW, closedQuestionId);
        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_NEW;
        } else {
            closedAnswerBO.add(closedAnswerNew.getClosedQuestionId(),
                    closedAnswerNew.getAnswer(),
                    closedAnswerNew.isCorrect());
            status.setComplete();
            //form success
        }
        return action;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel(@PathVariable("closedQuestionId") Integer closedQuestionId) {
        return String.format(REDIRECT_TO_PREVIEW, closedQuestionId);
    }
}
