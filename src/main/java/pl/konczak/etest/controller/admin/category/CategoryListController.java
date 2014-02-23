package pl.konczak.etest.controller.admin.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.konczak.etest.assembler.admin.category.CategoryAssembler;

@Controller
@RequestMapping("admin/category/")
public class CategoryListController {

    private static final String VIEW_LIST = "admin/category/list";
    @Autowired
    @Qualifier("categoryAssembler")
    private CategoryAssembler categoryAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("categories", categoryAssembler.toList());

        return VIEW_LIST;
    }
}
