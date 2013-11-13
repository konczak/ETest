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
import pl.konczak.etest.facade.ICategoryOfQuestionFacade;
import pl.konczak.etest.validator.impl.CategoryOfQuestionValidator;

@Controller
public class CategoryOfQuestionController {

    @Autowired
    @Qualifier("categoryOfQuestionValidator")
    private CategoryOfQuestionValidator categoryOfQuestionValidator;
    @Autowired
    private ICategoryOfQuestionFacade categoryOfQuestionFacade;

    @InitBinder("categoryOfQuestion")
    protected void initBindSecond(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(categoryOfQuestionValidator);
    }

    @RequestMapping(value = "question/category/new",
                    method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        CategoryOfQuestion categoryOfQuestion = new CategoryOfQuestion();

        //command object
        model.addAttribute("categoryOfQuestion", categoryOfQuestion);

        //return form view
        return "question/category/new";
    }

    @RequestMapping(value = "question/category/new",
                    method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(
            @Valid @ModelAttribute("categoryOfQuestion") CategoryOfQuestion categoryOfQuestion,
            BindingResult result, SessionStatus status) {
        String view = "redirect:/question/category/";
        if (result.hasErrors()) {
            //if validator failed
            view = "question/category/new";
        } else {
            categoryOfQuestionFacade.add(categoryOfQuestion);
            status.setComplete();
            //form success
        }
        return view;
    }

    @RequestMapping(value = "question/category/new",
                    method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel() {
        return "redirect:/question/category/";
    }

    @RequestMapping(value = "question/category/",
                    method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<CategoryOfQuestion> categories = categoryOfQuestionFacade.searchAll();
        model.addAttribute("categories", categories);

        return "question/category/list";
    }

    @RequestMapping(value = "question/category/delete",
                    method = RequestMethod.GET,
                    params = {"id"})
    public String delete(@RequestParam("id") Integer categoryOfQuestionId) {
        categoryOfQuestionFacade.remove(categoryOfQuestionId);

        return "redirect:/question/category/";
    }

    @RequestMapping(value = "question/category/edit",
                    method = RequestMethod.GET,
                    params = {"id"})
    public String editInit(@RequestParam("id") Integer categoryOfQuestionId,
            ModelMap model) {
        CategoryOfQuestion categoryOfQuestion = categoryOfQuestionFacade.find(categoryOfQuestionId);

        //command object
        model.addAttribute("categoryOfQuestion", categoryOfQuestion);

        //return form view
        return "question/category/edit";
    }

    @RequestMapping(value = "question/category/edit",
                    method = RequestMethod.POST,
                    params = "edit")
    public String editSubmit(
            @Valid @ModelAttribute("categoryOfQuestion") CategoryOfQuestion categoryOfQuestion,
            BindingResult result, SessionStatus status) {
        String view = "redirect:/question/category/";
        if (result.hasErrors()) {
            //if validator failed
            view = "question/category/edit";
        } else {
            categoryOfQuestionFacade.modify(categoryOfQuestion);
            status.setComplete();
            //form success
        }
        return view;
    }

    @RequestMapping(value = "question/category/edit",
                    method = RequestMethod.POST,
                    params = "cancel")
    public String editCancel() {
        return "redirect:/question/category/";
    }
}
