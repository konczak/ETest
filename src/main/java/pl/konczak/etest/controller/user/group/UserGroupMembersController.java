package pl.konczak.etest.controller.user.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.konczak.etest.assembler.user.group.UserGroupAssembler;
import pl.konczak.etest.bo.IUserGroupBO;

@Controller
@RequestMapping("user/group/{id}/members")
public class UserGroupMembersController {

    private static final String OBJECT = "userGroup";
    private static final String VIEW_PREVIEW = "user/group/members";
    @Autowired
    @Qualifier("userGroupAssembler")
    private UserGroupAssembler userGroupAssembler;
    @Autowired
    private IUserGroupBO userGroupBO;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model,
            @PathVariable("id") Integer userGroupId) {

        model.addAttribute(OBJECT, userGroupAssembler.toMembers(userGroupId));

        return VIEW_PREVIEW;
    }

    @RequestMapping(value = "/add",
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addUserAsMember(@PathVariable("id") Integer userGroupId,
            @RequestParam("userId") Integer userId) {
        userGroupBO.addUserAsMember(userId, userGroupId);
    }

    @RequestMapping(value = "/delete",
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void removeUserFromMembers(@PathVariable("id") Integer userGroupId,
            @RequestParam("userId") Integer userId) {
        userGroupBO.removeUserFromMembers(userId, userGroupId);
    }
}
