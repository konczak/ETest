package pl.konczak.etest.controller.teacher.closedQuestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.bo.IClosedQuestionBO;

@Controller
@RequestMapping("teacher/closedQuestion/delete/{id}")
public class ClosedQuestionDeleteController {

    private static final String REDIRECT_TO_LIST = "redirect:/teacher/closedQuestion/";
    @Autowired
    private IClosedQuestionBO closedQuestionBO;

    @RequestMapping(method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer closedQuestionId) {
        closedQuestionBO.remove(closedQuestionId);

        return REDIRECT_TO_LIST;
    }
}
