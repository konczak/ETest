package pl.konczak.etest.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.konczak.etest.assembler.admin.user.UserAssembler;

@Controller
@RequestMapping("admin/user/{id}")
public class UserPreviewController {

    private static final String OBJECT = "user";
    private static final String VIEW_PREVIEW = "admin/user/preview";
    @Autowired
    @Qualifier("userAssembler")
    private UserAssembler userAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model,
            @PathVariable("id") Integer userId) {

        model.addAttribute(OBJECT, userAssembler.toPreview(userId));

        return VIEW_PREVIEW;
    }
}
