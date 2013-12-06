package pl.konczak.etest.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.konczak.etest.assembler.teacher.TestTemplateAssembler;

@Controller
@RequestMapping("teacher/testTemplate/{id}")
public class TestTemplatePreviewController {

    private static final String OBJECT = "testTemplate";
    private static final String VIEW_PREVIEW = "teacher/testTemplate/preview";
    @Autowired
    @Qualifier("testTemplateAssembler")
    private TestTemplateAssembler testTemplateAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model,
            @PathVariable("id") Integer id) {

        model.addAttribute(OBJECT, testTemplateAssembler.toPreview(id));

        return VIEW_PREVIEW;
    }
}
