package pl.konczak.etest.controller.teacher.userExam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.konczak.etest.assembler.teacher.userExam.UserExamAssembler;

@Controller
@RequestMapping("teacher/userExam/{id}")
public class UserExamPreviewController {

    private static final String OBJECT = "userExam";
    private static final String VIEW_PREVIEW = "teacher/userExam/preview";
    @Autowired
    @Qualifier("teacherUserExamAssembler")
    private UserExamAssembler userExamAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model,
            @PathVariable("id") Integer userExamId) {

        model.addAttribute(OBJECT, userExamAssembler.toPreview(userExamId));
        
        return VIEW_PREVIEW;
    }
}
