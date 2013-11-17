package pl.konczak.etest.controller.question;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.entity.ClosedAnswer;
import pl.konczak.etest.facade.ICategoryOfQuestionFacade;
import pl.konczak.etest.facade.IClosedAnswerFacade;
import pl.konczak.etest.validator.impl.CategoryOfQuestionValidator;
import pl.konczak.etest.validator.impl.ClosedAnswerValidator;

@Controller
public class ClosedAnswerController {

    @Autowired
    @Qualifier("closedAnswerValidator")
    private ClosedAnswerValidator closedAnswerValidator;
    @Autowired
    private IClosedAnswerFacade closedAnswerFacade;

    @InitBinder("closedAnswer")
    protected void initBindSecond(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(closedAnswerValidator);
    }

    @RequestMapping(value = "question/closedAnswer/new",
                    method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        ClosedAnswer closedAnswer = new ClosedAnswer();

        //command object
        model.addAttribute("closedAnswer", closedAnswer);

        //return form view
        return "question/closedAnswer/new";
    }

    @RequestMapping(value = "question/closedAnswer/new",
                    method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(
            @Valid @ModelAttribute("closedAnswer") ClosedAnswer closedAnswer,
            BindingResult result, SessionStatus status) {
        String view = "redirect:/question/closedAnswer/";
        if (result.hasErrors()) {
            //if validator failed
            view = "question/closedAnswer/new";
        } else {
            closedAnswerFacade.add(closedAnswer);
            status.setComplete();
            //form success
        }
        return view;
    }

    @RequestMapping(value = "question/closedAnswer/new",
                    method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel() {
        return "redirect:/question/closedAnswer/";
    }

    @RequestMapping(value = "question/closedAnswer/",
                    method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<ClosedAnswer> closedAnswers = closedAnswerFacade.searchAll();
        model.addAttribute("closedAnswers", closedAnswers);

        return "question/closedAnswer/list";
    }

    @RequestMapping(value = "question/closedAnswer/delete",
                    method = RequestMethod.GET,
                    params = {"id"})
    public String delete(@RequestParam("id") Integer closedAnswerId) {
        closedAnswerFacade.remove(closedAnswerId);

        return "redirect:/question/closedAnswer/";
    }

    @RequestMapping(value = "question/closedAnswer/edit",
                    method = RequestMethod.GET,
                    params = {"id"})
    public String editInit(@RequestParam("id") Integer closedAnswerId,
            ModelMap model) {
        ClosedAnswer closedAnswer = closedAnswerFacade.find(closedAnswerId);

        //command object
        model.addAttribute("closedAnswer", closedAnswer);

        //return form view
        return "question/closedAnswer/edit";
    }

    @RequestMapping(value = "question/closedAnswer/edit",
                    method = RequestMethod.POST,
                    params = "edit")
    public String editSubmit(
            @Valid @ModelAttribute("closedAnswer") ClosedAnswer closedAnswer,
            BindingResult result, SessionStatus status) {
        String view = "redirect:/question/closedAnswer/";
        if (result.hasErrors()) {
            //if validator failed
            view = "question/closedAnswer/edit";
        } else {
            closedAnswerFacade.modify(closedAnswer);
            status.setComplete();
            //form success
        }
        return view;
    }

    @RequestMapping(value = "question/closedAnswer/edit",
                    method = RequestMethod.POST,
                    params = "cancel")
    public String editCancel() {
        return "redirect:/question/closedAnswer/";
    }
}
