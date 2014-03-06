package pl.konczak.etest.controller.admin.category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.konczak.etest.assembler.admin.category.CategoryAssembler;
import pl.konczak.etest.dto.admin.category.CategoryDTO;
import pl.konczak.etest.dto.admin.category.CategoryRead;

@Controller
@RequestMapping("admin/category/read")
public class CategoryController {

    @Autowired
    @Qualifier("categoryAssembler")
    private CategoryAssembler categoryAssembler;

    @RequestMapping(method = RequestMethod.GET,
                    produces = "application/json")
    @ResponseBody
    public CategoryRead getCategories() {
        CategoryRead categoryRead = new CategoryRead(categoryAssembler.toTree());
        return categoryRead;
    }
}
