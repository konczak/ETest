package pl.konczak.etest.controller.admin.categoryOfQuestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.bo.ICategoryOfQuestionBO;

@Controller
@RequestMapping("admin/categoryOfQuestion/delete/{id}")
public class CategoryOfQuestionDeleteController {

    private static final String REDIRECT_TO_LIST = "redirect:/admin/categoryOfQuestion/";
    @Autowired
    private ICategoryOfQuestionBO categoryOfQuestionBO;

    @RequestMapping(method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer categoryOfQuestionId) {
        categoryOfQuestionBO.remove(categoryOfQuestionId);

        return REDIRECT_TO_LIST;
    }
}
