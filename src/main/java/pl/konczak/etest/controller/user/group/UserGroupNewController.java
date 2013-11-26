package pl.konczak.etest.controller.user.group;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import pl.konczak.etest.bo.IUserGroupBO;
import pl.konczak.etest.dto.user.group.UserGroupNew;

@Controller
@RequestMapping("user/group/new")
public class UserGroupNewController {

    private static final String OBJECT = "userGroup";
    private static final String VIEW_NEW = "user/group/new";
    private static final String REDIRECT_TO_LIST = "redirect:/user/group/";
    @Autowired
    @Qualifier("userGroupNewValidator")
    private Validator userGroupNewValidator;
    @Autowired
    private IUserGroupBO userGroupBO;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userGroupNewValidator);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        UserGroupNew userGroupNew = new UserGroupNew();

        //command object
        model.addAttribute(OBJECT, userGroupNew);

        //return form view
        return VIEW_NEW;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(
            @Valid @ModelAttribute(OBJECT) UserGroupNew userGroupNew,
            BindingResult result, SessionStatus status) {
        String action = REDIRECT_TO_LIST;
        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_NEW;
        } else {
            userGroupBO.add(userGroupNew.getTitle());
            status.setComplete();
            //form success
        }
        return action;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel() {
        return REDIRECT_TO_LIST;
    }
}
