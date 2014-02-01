package pl.konczak.etest.controller.admin.userGroup;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import pl.konczak.etest.assembler.admin.userGroup.UserGroupAssembler;
import pl.konczak.etest.bo.IUserGroupBO;
import pl.konczak.etest.dto.user.group.UserGroupEdit;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.repository.IUserGroupRepository;

@Controller
@RequestMapping("admin/userGroup/edit/{id}")
public class UserGroupEditController {

    private static final String OBJECT = "userGroup";
    private static final String VIEW_EDIT = "admin/userGroup/edit";
    private static final String REDIRECT_TO_LIST = "redirect:/admin/userGroup/";
    @Autowired
    @Qualifier("userGroupEditValidator")
    private Validator userGroupEditValidator;
    @Autowired
    private IUserGroupRepository userGroupRepository;
    @Autowired
    @Qualifier("userGroupAssembler")
    private UserGroupAssembler userGroupAssembler;
    @Autowired
    private IUserGroupBO userGroupBO;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userGroupEditValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String editInit(@PathVariable("id") Integer userGroupId,
            ModelMap model) {
        UserGroupEntity userGroupEntity =
                userGroupRepository.getById(userGroupId);
        UserGroupEdit userGroupEdit =
                userGroupAssembler.toEdit(userGroupEntity);
        //command object
        model.addAttribute(OBJECT, userGroupEdit);

        //return form view
        return VIEW_EDIT;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "edit")
    public String editSubmit(
            @Valid @ModelAttribute(OBJECT) UserGroupEdit userGroupEdit,
            BindingResult result, SessionStatus status) {
        String action = REDIRECT_TO_LIST;
        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_EDIT;
        } else {
            userGroupBO.changeTitle(userGroupEdit.getId(), userGroupEdit.getTitle());
            status.setComplete();
            //form success
        }
        return action;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "cancel")
    public String editCancel() {
        return REDIRECT_TO_LIST;
    }
}
