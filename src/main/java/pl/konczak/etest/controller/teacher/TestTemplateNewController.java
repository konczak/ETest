package pl.konczak.etest.controller.teacher;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import pl.konczak.etest.bo.ITestTemplateBO;
import pl.konczak.etest.dto.teacher.TestTemplateNew;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.IUserRepository;

@Controller
@RequestMapping("teacher/testTemplate/new")
public class TestTemplateNewController {

    private static final String OBJECT = "testTemplate";
    private static final String VIEW_NEW = "teacher/testTemplate/new";
    private static final String REDIRECT_TO_LIST = "redirect:/teacher/testTemplate/";
    @Autowired
    @Qualifier("testTemplateNewValidator")
    private Validator testTemplateNewValidator;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ITestTemplateBO testTemplateBO;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(testTemplateNewValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        TestTemplateNew testTemplateNew = new TestTemplateNew();

        //command object
        model.addAttribute(OBJECT, testTemplateNew);

        //return form view
        return VIEW_NEW;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(
            @Valid @ModelAttribute(OBJECT) TestTemplateNew testTemplateNew,
            BindingResult result, SessionStatus status) {
        String action = REDIRECT_TO_LIST;
        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_NEW;
        } else {
            testTemplateBO.add(testTemplateNew.getSubject(),
                    getIdOfAuthenticatedUser());
            status.setComplete();
            //form success
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
