package pl.konczak.etest.controller.admin.user;

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

import pl.konczak.etest.bo.IUserBO;
import pl.konczak.etest.dto.user.UserNew;

@Controller
@RequestMapping("admin/user/new")
public class UserNewController {

    private static final String OBJECT = "user";
    private static final String VIEW_NEW = "admin/user/new";
    private static final String VIEW_SUCCESS = "admin/user/success";
    @Autowired
    @Qualifier("userNewValidator")
    private Validator userNewValidator;
    @Autowired
    private IUserBO userBO;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userNewValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        UserNew userNew = new UserNew();

        //command object
        model.addAttribute(OBJECT, userNew);

        //return form view
        return VIEW_NEW;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "register")
    public String processSubmit(
            @Valid @ModelAttribute(OBJECT) UserNew userRegistration,
            BindingResult result, SessionStatus status) {

        String view = VIEW_SUCCESS;
        if (result.hasErrors()) {
            //if validator failed
            view = VIEW_NEW;
        } else {
            userBO.register(userRegistration.getEmail(),
                    userRegistration.getPassword(),
                    userRegistration.getFirstname(),
                    userRegistration.getLastname());
            status.setComplete();
            //form success
        }
        return view;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel(
            @Valid @ModelAttribute(OBJECT) UserNew userRegistration,
            BindingResult result, SessionStatus status) {
        return "redirect:/";
    }
}
