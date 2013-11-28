package pl.konczak.etest.controller.user;

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
import pl.konczak.etest.assembler.user.UserAssembler;
import pl.konczak.etest.bo.IUserBO;

@Controller
@RequestMapping("user/{id}/groups")
public class UserGroupsController {

    private static final String OBJECT = "user";
    private static final String VIEW_PREVIEW = "user/groups";
    @Autowired
    @Qualifier("userAssembler")
    private UserAssembler userAssembler;
    @Autowired
    private IUserBO userBO;

    @RequestMapping(method = RequestMethod.GET)
    public String get(ModelMap model,
            @PathVariable("id") Integer userId) {

        model.addAttribute(OBJECT, userAssembler.toGroups(userId));

        return VIEW_PREVIEW;
    }

    @RequestMapping(value = "/add",
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addUserAsMember(@PathVariable("id") Integer userId,
            @RequestParam("userId") Integer userGroupId) {
        userBO.addUserToGroup(userId, userGroupId);
    }

    @RequestMapping(value = "/delete",
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void removeUserFromMembers(@PathVariable("id") Integer userId,
            @RequestParam("userId") Integer userGroupId) {
        userBO.removeUserFromGroup(userId, userGroupId);
    }
}
