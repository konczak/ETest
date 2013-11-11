package pl.konczak.etest.controller.question;

import java.util.ArrayList;
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
import pl.konczak.etest.dto.QuestionCategory;
import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.facade.ICategoryOfQuestionFacade;
import pl.konczak.etest.validator.impl.QuestionCategoryValidator;

@Controller
@RequestMapping("question/category/")
public class QuestionCategoryController {

    @Autowired
    @Qualifier("questionCategoryValidator")
    private QuestionCategoryValidator questionCategoryValidator;
    @Autowired
    private ICategoryOfQuestionFacade categoryOfQuestionFacade;

    @InitBinder("questionCategory")
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(questionCategoryValidator);
    }

    @RequestMapping(value = "question/category/new",
                    method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        QuestionCategory questionCategory = new QuestionCategory();

        //command object
        model.addAttribute("questionCategory", questionCategory);

        //return form view
        return "question/category/new";
    }

    @RequestMapping(value = "question/category/new",
                    method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(
            @Valid @ModelAttribute("questionCategory") QuestionCategory questionCategory,
            BindingResult result, SessionStatus status) {
        String view = "redirect:/question/category/";
        if (result.hasErrors()) {
            //if validator failed
            view = "question/category/new";
        } else {
            categoryOfQuestionFacade.add(questionCategory);
            status.setComplete();
            //form success
        }
        return view;
    }

    @RequestMapping(value = "question/category/new",
                    method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel(
            @Valid @ModelAttribute("questionCategory") QuestionCategory questionCategory,
            BindingResult result, SessionStatus status) {
        return "redirect:/question/category/";
    }

    @RequestMapping(value = "question/category/",
                    method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<CategoryOfQuestion> categories = categoryOfQuestionFacade.searchAll();
        model.addAttribute("categories", categories);

        return "question/category/list";
    }

    @RequestMapping(value = "question/category/delete/{id}",
                    method = RequestMethod.GET)
    public String delete(@RequestParam("id") Integer categoryOfQuestionId) {
        categoryOfQuestionFacade.remove(categoryOfQuestionId);

        return "redirect:/question/category/";
    }
}
