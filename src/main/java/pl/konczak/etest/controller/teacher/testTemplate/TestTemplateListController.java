package pl.konczak.etest.controller.teacher.testTemplate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.konczak.etest.dto.teacher.TestTemplateListRow;
import pl.konczak.etest.service.IDTORowListPrepareService;

@Controller
@RequestMapping("teacher/testTemplate/")
public class TestTemplateListController {

    @Autowired
    @Qualifier("testTemplateListPrepareService")
    private IDTORowListPrepareService<TestTemplateListRow> testTemplateListPrepareService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<TestTemplateListRow> testTemplateListRows =
                testTemplateListPrepareService.findAll();

        model.addAttribute("testTemplates", testTemplateListRows);

        return "teacher/testTemplate/list";
    }
}
