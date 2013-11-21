package pl.konczak.etest.controller.question.closedQuestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.bo.IClosedQuestionBO;

@Controller
@RequestMapping("question/closedQuestion/delete/{id}")
public class ClosedQuestionDeleteController {

    private static final String REDIRECT_TO_LIST = "redirect:/question/closedQuestion/";
    @Autowired
    private IClosedQuestionBO closedQuestionBO;

    @RequestMapping(method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer closedQuestionId) {
        closedQuestionBO.remove(closedQuestionId);

        return REDIRECT_TO_LIST;
    }
}
