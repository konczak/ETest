package pl.konczak.etest.controller.teacher.closedAnswer;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import pl.konczak.etest.bo.IClosedAnswerBO;
import pl.konczak.etest.dto.teacher.closedAnswer.ClosedAnswerNew;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.repository.IClosedQuestionRepository;
import pl.konczak.etest.validator.teacher.closedAnswer.ClosedAnswerNewValidator;

@Controller
@RequestMapping("teacher/closedAnswer/new/{closedQuestionId}")
public class ClosedAnswerNewController {

    private static final String OBJECT = "closedAnswer";
    private static final String VIEW_NEW = "teacher/closedAnswer/new";
    private static final String REDIRECT_TO_PREVIEW = "redirect:/teacher/closedQuestion/%s";
    @Autowired
    @Qualifier("closedAnswerNewValidator")
    private ClosedAnswerNewValidator closedAnswerNewValidator;
    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;
    @Autowired
    private IClosedAnswerBO closedAnswerBO;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(closedAnswerNewValidator);
    }

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
            BindingResult result, SessionStatus status)
            throws IOException {
        String action = String.format(REDIRECT_TO_PREVIEW, closedQuestionId);
        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_NEW;
        } else {
            ClosedAnswerEntity closedAnswerEntity = closedAnswerBO.add(closedAnswerNew.getClosedQuestionId(),
                    closedAnswerNew.getAnswer(),
                    closedAnswerNew.isCorrect());
            MultipartFile multipartFile = closedAnswerNew.getMultipartFile();
            if (!multipartFile.isEmpty()) {
                closedAnswerBO.addPicture(closedAnswerEntity.getId(),
                        multipartFile.getBytes());
            }
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
