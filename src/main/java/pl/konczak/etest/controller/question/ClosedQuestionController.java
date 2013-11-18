package pl.konczak.etest.controller.question;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pl.konczak.etest.editor.UserPropertyEditor;
import pl.konczak.etest.entity.ClosedQuestion;
import pl.konczak.etest.entity.User;
import pl.konczak.etest.facade.IClosedQuestionFacade;
import pl.konczak.etest.repository.IUserRepository;

@Controller
public class ClosedQuestionController {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IClosedQuestionFacade closedQuestionFacade;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, "author", new UserPropertyEditor(userRepository));
    }

    @RequestMapping(value = "question/closedQuestion/new",
                    method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        ClosedQuestion closedQuestion = new ClosedQuestion();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getByEmail(email);
        closedQuestion.setAuthor(user);

        //command object
        model.addAttribute("closedQuestion", closedQuestion);

        //return form view
        return "question/closedQuestion/new";
    }

    @RequestMapping(value = "question/closedQuestion/new",
                    method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(
            @Valid @ModelAttribute("closedQuestion") ClosedQuestion closedQuestion,
            BindingResult result, SessionStatus status) {
        String view = "redirect:/question/closedQuestion/";
        if (result.hasErrors()) {
            //if validator failed
            view = "question/closedQuestion/new";
        } else {
            closedQuestionFacade.add(closedQuestion);
            status.setComplete();
            //form success
        }
        return view;
    }

    @RequestMapping(value = "question/closedQuestion/new",
                    method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel() {
        return "redirect:/question/closedQuestion/";
    }

    @RequestMapping(value = "question/closedQuestion/",
                    method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<ClosedQuestion> closedQuestions = closedQuestionFacade.searchAll();
        model.addAttribute("closedQuestions", closedQuestions);

        return "question/closedQuestion/list";
    }

    @RequestMapping(value = "question/closedQuestion/delete",
                    method = RequestMethod.GET,
                    params = {"id"})
    public String delete(@RequestParam("id") Integer closedQuestionId) {
        closedQuestionFacade.remove(closedQuestionId);

        return "redirect:/question/closedQuestion/";
    }

    @RequestMapping(value = "question/closedQuestion/edit",
                    method = RequestMethod.GET,
                    params = {"id"})
    public String editInit(@RequestParam("id") Integer closedQuestionId,
            ModelMap model) {
        ClosedQuestion closedQuestion = closedQuestionFacade.find(closedQuestionId);

        //command object
        model.addAttribute("closedQuestion", closedQuestion);

        //return form view
        return "question/closedQuestion/edit";
    }

    @RequestMapping(value = "question/closedQuestion/edit",
                    method = RequestMethod.POST,
                    params = "edit")
    public String editSubmit(
            @Valid @ModelAttribute("closedQuestion") ClosedQuestion closedQuestion,
            BindingResult result, SessionStatus status) {
        String view = "redirect:/question/closedQuestion/";
        if (result.hasErrors()) {
            //if validator failed
            view = "question/closedQuestion/edit";
        } else {
            closedQuestionFacade.modify(closedQuestion);
            status.setComplete();
            //form success
        }
        return view;
    }

    @RequestMapping(value = "question/closedQuestion/edit",
                    method = RequestMethod.POST,
                    params = "cancel")
    public String editCancel() {
        return "redirect:/question/closedQuestion/";
    }
}
