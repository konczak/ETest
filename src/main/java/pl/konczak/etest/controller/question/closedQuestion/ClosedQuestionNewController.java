package pl.konczak.etest.controller.question.closedQuestion;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import pl.konczak.etest.bo.IClosedQuestionBO;
import pl.konczak.etest.dto.question.closedQuestion.ClosedQuestionNew;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.IUserRepository;
import pl.konczak.etest.validator.question.ClosedQuestionNewValidator;

@Controller
@RequestMapping("question/closedQuestion/new")
public class ClosedQuestionNewController {

    private static final String OBJECT = "closedQuestion";
    private static final String VIEW_NEW = "question/closedQuestion/new";
    private static final String REDIRECT_TO_LIST = "redirect:/question/closedQuestion/";
    private static final String REDIRECT_TO_PREVIEW = "redirect:/question/closedQuestion/%s";
    @Autowired
    @Qualifier("closedQuestionNewValidator")
    private ClosedQuestionNewValidator closedQuestionNewValidator;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IClosedQuestionBO closedQuestionBO;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(closedQuestionNewValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        ClosedQuestionNew closedQuestionNew = new ClosedQuestionNew();

        //command object
        model.addAttribute(OBJECT, closedQuestionNew);

        //return form view
        return VIEW_NEW;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "save")
    public String processSubmit(@Valid @ModelAttribute(OBJECT) ClosedQuestionNew closedQuestionNew,
            BindingResult result, SessionStatus status)
            throws IOException {
        String action = REDIRECT_TO_PREVIEW;

        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_NEW;
        } else {
            ClosedQuestionEntity closedQuestionEntity =
                    closedQuestionBO.add(closedQuestionNew.getQuestion(), getIdOfAuthenticatedUser());
            MultipartFile multipartFile = closedQuestionNew.getMultipartFile();
            if (multipartFile != null) {
                closedQuestionBO.addPicture(closedQuestionEntity.getId(),
                        multipartFile.getBytes());
            }
            status.setComplete();
            //form success
            action = String.format(REDIRECT_TO_PREVIEW, closedQuestionEntity.getId());
        }
        return action;
    }

    private Integer getIdOfAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.getByEmail(email);

        return user.getId();
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel() {
        return REDIRECT_TO_LIST;
    }
}
