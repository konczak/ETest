package pl.konczak.etest.controller.user.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.dto.user.group.UserGroupListRow;
import pl.konczak.etest.service.IUserGroupListPrepareService;

@Controller
@RequestMapping("user/group/")
public class UserGroupListController {

    @Autowired
    private IUserGroupListPrepareService userGroupListPrepareService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<UserGroupListRow> userGroupRowList =
                userGroupListPrepareService.findAll();

        model.addAttribute("userGroups", userGroupRowList);

        return "user/group/list";
    }
}
