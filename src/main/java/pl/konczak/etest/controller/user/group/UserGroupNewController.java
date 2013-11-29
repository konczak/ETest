package pl.konczak.etest.controller.user.group;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.joda.time.LocalDateTime;

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
        userGroupNew.setClassPrefix("Klasa");

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

    @ModelAttribute("classNumberList")
    public List<String> populateClassNumberList() {
        //Data referencing for number radiobuttons
        List<String> classNumberList = new ArrayList<String>();
        classNumberList.add("I");
        classNumberList.add("II");
        classNumberList.add("III");

        return classNumberList;
    }

    @ModelAttribute("classLetterList")
    public List<String> populateClassLetterList() {
        //Data referencing for number radiobuttons
        List<String> classLetterList = new ArrayList<String>();
        classLetterList.add("A");
        classLetterList.add("B");
        classLetterList.add("C");
        classLetterList.add("D");
        classLetterList.add("E");
        classLetterList.add("K");

        return classLetterList;
    }

    @ModelAttribute("classYearList")
    public List<String> populateClassYearList() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        //Data referencing for number radiobuttons
        List<String> classYearList = new ArrayList<String>();
        classYearList.add(yearFormat(year, 0));
        classYearList.add(yearFormat(year, 1));
        classYearList.add(yearFormat(year, 2));
        classYearList.add(yearFormat(year, 3));

        return classYearList;
    }

    private String yearFormat(int year, int shift) {
        return String.format("%s-%s", year + shift, year - 2000 + shift + 1);
    }
}
