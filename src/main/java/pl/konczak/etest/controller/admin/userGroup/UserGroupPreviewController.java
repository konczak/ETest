package pl.konczak.etest.controller.admin.userGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.konczak.etest.assembler.admin.userGroup.UserGroupAssembler;

@Controller
@RequestMapping("admin/userGroup/{id}")
public class UserGroupPreviewController {

    private static final String OBJECT = "userGroup";
    private static final String VIEW_PREVIEW = "admin/userGroup/preview";
    @Autowired
    @Qualifier("userGroupAssembler")
    private UserGroupAssembler userGroupAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model,
            @PathVariable("id") Integer userGroupId) {

        model.addAttribute(OBJECT, userGroupAssembler.toPreview(userGroupId));

        return VIEW_PREVIEW;
    }
}
