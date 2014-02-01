package pl.konczak.etest.controller.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.dto.user.UserListRow;
import pl.konczak.etest.service.IUserListPrepareService;

@Controller
@RequestMapping("admin/user/")
public class UserListController {

    private static final String VIEW_LIST = "admin/user/list";
    @Autowired
    private IUserListPrepareService userListPrepareService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<UserListRow> userRowList =
                userListPrepareService.findAll();

        model.addAttribute("users", userRowList);

        return VIEW_LIST;
    }
}
