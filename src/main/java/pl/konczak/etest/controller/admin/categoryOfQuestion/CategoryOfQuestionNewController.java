package pl.konczak.etest.controller.admin.categoryOfQuestion;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import pl.konczak.etest.bo.ICategoryOfQuestionBO;
import pl.konczak.etest.dto.question.category.CategoryOfQuestionNew;

@Controller
@RequestMapping("admin/categoryOfQuestion/new")
public class CategoryOfQuestionNewController {

    private static final String OBJECT = "categoryOfQuestion";
    private static final String VIEW_NEW = "admin/categoryOfQuestion/new";
    private static final String REDIRECT_TO_LIST = "redirect:/admin/categoryOfQuestion/";
    @Autowired
    @Qualifier("categoryOfQuestionNewValidator")
    private Validator categoryOfQuestionNewValidator;
    @Autowired
    private ICategoryOfQuestionBO categoryOfQuestionBO;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(categoryOfQuestionNewValidator);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        CategoryOfQuestionNew categoryOfQuestionNew = new CategoryOfQuestionNew();

        //command object
        model.addAttribute(OBJECT, categoryOfQuestionNew);

        //return form view
        return VIEW_NEW;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(
            @Valid @ModelAttribute(OBJECT) CategoryOfQuestionNew categoryOfQuestionNew,
            BindingResult result, SessionStatus status) {
        String action = REDIRECT_TO_LIST;
        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_NEW;
        } else {
            categoryOfQuestionBO.add(categoryOfQuestionNew.getTitle());
            status.setComplete();
            //form success
        }
        return action;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel() {
        return REDIRECT_TO_LIST;
    }
}
