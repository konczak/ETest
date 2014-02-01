package pl.konczak.etest.controller.admin.userGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.bo.IUserGroupBO;

@Controller
@RequestMapping("admin/userGroup/delete/{id}")
public class UserGroupDeleteController {

    private static final String REDIRECT_TO_LIST = "redirect:/admin/userGroup/";
    @Autowired
    private IUserGroupBO userGroupBO;

    @RequestMapping(method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer userGroupId) {
        userGroupBO.remove(userGroupId);

        return REDIRECT_TO_LIST;
    }
}
