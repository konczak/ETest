package com.cybercom.telep.etest.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.cybercom.telep.etest.dto.UserRegistration;
import com.cybercom.telep.etest.facade.IUserRegisterFacade;
import com.cybercom.telep.etest.validator.impl.UserRegistrationValidator;

@Controller
@RequestMapping("user/register")
public class UserRegisterController {

    @Autowired
    @Qualifier("userRegistrationValidator")
    private UserRegistrationValidator userRegistrationValidator;
    @Autowired
    private IUserRegisterFacade userRegisterFacade;

    @InitBinder("userRegistration")
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userRegistrationValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        UserRegistration userRegistration = new UserRegistration();

        //command object
        model.addAttribute("userRegistration", userRegistration);

        //return form view
        return "user/new";
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "register")
    public String processSubmit(
            @Valid @ModelAttribute("userRegistration") UserRegistration userRegistration,
            BindingResult result, SessionStatus status) {

        if (result.hasErrors()) {
            //if validator failed
            return "user/new";
        } else {
            status.setComplete();
            userRegisterFacade.register(userRegistration);
            //form success
            return "user/success";
        }
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel(
            @Valid @ModelAttribute("userRegistration") UserRegistration userRegistration,
            BindingResult result, SessionStatus status) {
        return "redirect:/";
    }
}
