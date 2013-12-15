package pl.konczak.etest.controller.teacher.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.konczak.etest.assembler.teacher.exam.ExamAssembler;

@Controller
@RequestMapping("teacher/exam/")
public class ExamListController {

    @Autowired
    @Qualifier("examAssembler")
    private ExamAssembler examAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {

        model.addAttribute("exams", examAssembler.toList());

        return "teacher/exam/list";
    }
}
