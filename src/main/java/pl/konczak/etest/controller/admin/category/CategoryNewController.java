package pl.konczak.etest.controller.admin.category;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.konczak.etest.assembler.ValidationErrorAssembler;
import pl.konczak.etest.dto.admin.category.CategoryNew;

@Controller
@RequestMapping("admin/category/new")
public class CategoryNewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryNewController.class);
    @Autowired
    @Qualifier("validationErrorAssembler")
    private ValidationErrorAssembler validationErrorAssembler;

    @RequestMapping(method = RequestMethod.POST,
                    consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void processSubmit(@Valid @RequestBody CategoryNew categoryNew) {
        LOGGER.info("processSubmit <{}>", categoryNew);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            LOGGER.error("unable to sleep thread", ex);
        }
    }


}
