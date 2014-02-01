package pl.konczak.etest.controller.admin.categoryOfQuestion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.assembler.admin.categoryOfQuestion.CategoryOfQuestionAssembler;
import pl.konczak.etest.dto.question.category.CategoryOfQuestionListRow;
import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;

@Controller
@RequestMapping("admin/categoryOfQuestion/")
public class CategoryOfQuestionListController {

    private static final String VIEW_LIST = "admin/categoryOfQuestion/list";
    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;
    @Autowired
    @Qualifier("categoryOfQuestionAssembler")
    private CategoryOfQuestionAssembler categoryOfQuestionAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<CategoryOfQuestionEntity> categoryOfQuestionEntities = categoryOfQuestionRepository.findAll();
        List<CategoryOfQuestionListRow> categoriesOfQuestion = new ArrayList<CategoryOfQuestionListRow>();

        for (CategoryOfQuestionEntity entity : categoryOfQuestionEntities) {
            CategoryOfQuestionListRow row =
                    categoryOfQuestionAssembler.toListRow(entity);
            categoriesOfQuestion.add(row);
        }
        model.addAttribute("categoriesOfQuestion", categoriesOfQuestion);

        return VIEW_LIST;
    }
}
