package pl.konczak.etest.controller.admin.userGroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.dto.user.group.UserGroupListRow;
import pl.konczak.etest.service.IUserGroupListPrepareService;

@Controller
@RequestMapping("admin/userGroup/")
public class UserGroupListController {

    private static final String VIEW_LIST = "admin/userGroup/list";
    @Autowired
    private IUserGroupListPrepareService userGroupListPrepareService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<UserGroupListRow> userGroupRowList =
                userGroupListPrepareService.findAll();

        model.addAttribute("userGroups", userGroupRowList);

        return VIEW_LIST;
    }
}
