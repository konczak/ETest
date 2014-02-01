package pl.konczak.etest.controller.teacher.closedQuestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.assembler.teacher.closedQuestion.ClosedQuestionAssembler;

@Controller
@RequestMapping("teacher/closedQuestion/{id}")
public class ClosedQuestionPreviewController {

    private static final String OBJECT = "closedQuestion";
    private static final String VIEW_PREVIEW = "teacher/closedQuestion/preview";
    @Autowired
    @Qualifier("closedQuestionAssembler")
    private ClosedQuestionAssembler closedQuestionAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model,
            @PathVariable("id") Integer closedQuestionId) {

        model.addAttribute(OBJECT, closedQuestionAssembler.toPreview(closedQuestionId));

        return VIEW_PREVIEW;
    }
}
