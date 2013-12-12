package pl.konczak.etest.controller.teacher.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.assembler.teacher.exam.ExamAssembler;

@Controller
@RequestMapping("teacher/exam/{id}")
public class ExamPreviewController {

    private static final String OBJECT = "exam";
    private static final String VIEW_PREVIEW = "teacher/exam/preview";
    @Autowired
    @Qualifier("examAssembler")
    private ExamAssembler examAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model,
            @PathVariable("id") Integer id) {

        model.addAttribute(OBJECT, examAssembler.toPreview(id));

        return VIEW_PREVIEW;
    }
}
