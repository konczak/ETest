package com.cybercom.telep.etest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.cybercom.telep.etest.dto.UserRegistration;
import com.cybercom.telep.etest.facade.IUserRegisterFacade;

@Controller
@RequestMapping("user/register")
public class UserRegisterController {

    @Autowired
    private IUserRegisterFacade userRegisterFacade;

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(
            @ModelAttribute("userRegistration") UserRegistration userRegistration,
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

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        UserRegistration userRegistration = new UserRegistration();

        //command object
        model.addAttribute("userRegistration", userRegistration);

        //return form view
        return "user/new";
    }
}
