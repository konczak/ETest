package pl.konczak.etest.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.konczak.etest.assembler.teacher.TestTemplateAssembler;
import pl.konczak.etest.bo.ITestTemplateBO;

@Controller
@RequestMapping("teacher/testTemplate/{id}/closedQuestions")
public class TestTemplateClosedQuestionsController {

    private static final String OBJECT = "testTemplate";
    private static final String VIEW_PREVIEW = "teacher/testTemplate/closedQuestions";
    @Autowired
    @Qualifier("testTemplateAssembler")
    private TestTemplateAssembler testTemplateAssembler;
    @Autowired
    private ITestTemplateBO testTemplateBO;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model,
            @PathVariable("id") Integer testTemplateId) {

        model.addAttribute(OBJECT, testTemplateAssembler.toClosedQuestions(testTemplateId));

        return VIEW_PREVIEW;
    }

    @RequestMapping(value = "/add",
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addClosedQuestionToTestTemplate(@PathVariable("id") Integer testTemplateId,
            @RequestParam("closedQuestionId") Integer closedQuestionId) {
        testTemplateBO.addClosedQuestion(testTemplateId, closedQuestionId);
    }

    @RequestMapping(value = "/delete",
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void removeClosedQuestionFromTestTemplate(@PathVariable("id") Integer testTemplateId,
            @RequestParam("closedQuestionId") Integer closedQuestionId) {
        testTemplateBO.removeClosedQuestion(testTemplateId, closedQuestionId);
    }

    @RequestMapping(value = "/mandatory",
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void changeClosedQuestionMandatoryStatus(@PathVariable("id") Integer testTemplateId,
            @RequestParam("closedQuestionId") Integer closedQuestionId,
            @RequestParam("mandatory") boolean mandatory) {
        testTemplateBO.changeClosedQuestionStatusOfMandatory(testTemplateId, closedQuestionId, mandatory);
    }
}
