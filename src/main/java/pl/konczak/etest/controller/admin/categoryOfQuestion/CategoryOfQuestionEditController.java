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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import pl.konczak.etest.assembler.admin.categoryOfQuestion.CategoryOfQuestionAssembler;
import pl.konczak.etest.bo.ICategoryOfQuestionBO;
import pl.konczak.etest.dto.question.category.CategoryOfQuestionEdit;
import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;

@Controller
@RequestMapping("admin/categoryOfQuestion/edit/{id}")
public class CategoryOfQuestionEditController {

    private static final String OBJECT = "categoryOfQuestion";
    private static final String VIEW_EDIT = "admin/categoryOfQuestion/edit";
    private static final String REDIRECT_TO_LIST = "redirect:/admin/categoryOfQuestion/";
    @Autowired
    @Qualifier("categoryOfQuestionEditValidator")
    private Validator categoryOfQuestionEditValidator;
    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;
    @Autowired
    @Qualifier("categoryOfQuestionAssembler")
    private CategoryOfQuestionAssembler categoryOfQuestionAssembler;
    @Autowired
    private ICategoryOfQuestionBO categoryOfQuestionBO;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(categoryOfQuestionEditValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String editInit(@PathVariable("id") Integer categoryOfQuestionId,
            ModelMap model) {
        CategoryOfQuestionEntity categoryOfQuestionEntity =
                categoryOfQuestionRepository.getById(categoryOfQuestionId);
        CategoryOfQuestionEdit categoryOfQuestionEdit =
                categoryOfQuestionAssembler.toEdit(categoryOfQuestionEntity);
        //command object
        model.addAttribute(OBJECT, categoryOfQuestionEdit);

        //return form view
        return VIEW_EDIT;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "edit")
    public String editSubmit(
            @Valid @ModelAttribute(OBJECT) CategoryOfQuestionEdit categoryOfQuestion,
            BindingResult result, SessionStatus status) {
        String action = REDIRECT_TO_LIST;
        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_EDIT;
        } else {
            categoryOfQuestionBO.changeTitle(categoryOfQuestion.getId(), categoryOfQuestion.getTitle());
            status.setComplete();
            //form success
        }
        return action;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "cancel")
    public String editCancel() {
        return REDIRECT_TO_LIST;
    }
}
