package com.cybercom.telep.etest.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user/settings")
public class UserSettingsController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "user/settings/index";
    }
}
