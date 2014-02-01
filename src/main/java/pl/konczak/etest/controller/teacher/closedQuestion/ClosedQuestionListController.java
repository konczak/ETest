package pl.konczak.etest.controller.teacher.closedQuestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.dto.question.closedQuestion.ClosedQuestionListRow;
import pl.konczak.etest.service.IClosedQuestionListPrepareService;

@Controller
@RequestMapping("teacher/closedQuestion/")
public class ClosedQuestionListController {

    @Autowired
    private IClosedQuestionListPrepareService closedQuestionListPrepareService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<ClosedQuestionListRow> closedQuestionListRows =
                closedQuestionListPrepareService.findAll();

        model.addAttribute("closedQuestions", closedQuestionListRows);

        return "teacher/closedQuestion/list";
    }
}
