package pl.konczak.etest.controller.admin.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.konczak.etest.assembler.ValidationErrorAssembler;
import pl.konczak.etest.assembler.admin.category.CategoryAssembler;
import pl.konczak.etest.dto.ValidationErrorDTO;

@Controller
@RequestMapping("admin/category/")
public class CategoryTreeController {

    private static final String VIEW_LIST = "admin/category/tree";
    @Autowired
    @Qualifier("categoryAssembler")
    private CategoryAssembler categoryAssembler;
    @Autowired
    @Qualifier("validationErrorAssembler")
    private ValidationErrorAssembler validationErrorAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String tree(ModelMap model) {
        model.addAttribute("categories", categoryAssembler.toTree());

        return VIEW_LIST;
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
        return validationErrorAssembler.toValidationErrorDTO(ex);
    }
}
