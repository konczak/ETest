package pl.konczak.etest.controller.teacher.exam;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import pl.konczak.etest.bo.IExamBO;
import pl.konczak.etest.dto.teacher.exam.ExamNew;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.repository.ITestTemplateRepository;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.repository.IUserRepository;

@Controller
@RequestMapping("teacher/exam/new")
public class ExamNewController {

    private static final String OBJECT = "exam";
    private static final String VIEW_NEW = "teacher/exam/new";
    private static final String REDIRECT_TO_PREVIEW = "redirect:/teacher/exam/%s";
    private static final String REDIRECT_TO_LIST = "redirect:/teacher/exam/";
    private static final String DATE_PATTERN = "YYYY-MM-dd HH:mm";
    private static final String DURATION_SEPARATOR = " - ";
    @Autowired
    private ITestTemplateRepository testTemplateRepository;
    @Autowired
    private IUserGroupRepository userGroupRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IExamBO examBO;
    @Autowired
    @Qualifier("examNewValidator")
    private Validator examNewValidator;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(examNewValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        ExamNew examNew = new ExamNew();

        //command object
        model.addAttribute(OBJECT, examNew);

        //return form view
        return VIEW_NEW;
    }

    @Transactional(readOnly = true)
    @ModelAttribute("testTemplateList")
    public Map<Integer, String> populateTestTemplateList() {
        List<TestTemplateEntity> testTemplateEntities = testTemplateRepository.findAll();
        Map<Integer, String> testTemplates = new LinkedHashMap<Integer, String>();

        for (TestTemplateEntity testTemplateEntity : testTemplateEntities) {
            testTemplates.put(testTemplateEntity.getId(), testTemplateEntity.getSubject());
        }

        return testTemplates;
    }

    @Transactional(readOnly = true)
    @ModelAttribute("userGroupList")
    public Map<Integer, String> populateUserGroupList() {
        List<UserGroupEntity> userGroupEntities = userGroupRepository.findAll();
        Map<Integer, String> userGroups = new LinkedHashMap<Integer, String>();

        for (UserGroupEntity userGroupEntity : userGroupEntities) {
            userGroups.put(userGroupEntity.getId(), userGroupEntity.getTitle());
        }

        return userGroups;
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(
            @Valid @ModelAttribute(OBJECT) ExamNew examNew,
            BindingResult result, SessionStatus status) {
        String action = null;
        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_NEW;
        } else {
            LocalDateTime activeFrom = parseActiveFrom(examNew.getDuration());
            LocalDateTime activeTo = parseActiveTo(examNew.getDuration());

            ExamEntity examEntity = examBO.add(examNew.getTestTemplateId(),
                    examNew.getUserGroupId(),
                    examNew.getSuffix(),
                    getIdOfAuthenticatedUser(),
                    activeFrom,
                    activeTo);
            status.setComplete();
            //form success
            action = String.format(REDIRECT_TO_PREVIEW, examEntity.getId());
        }
        return action;
    }

    private Integer getIdOfAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.getByEmail(email);

        return user.getId();
    }

    @RequestMapping(method = RequestMethod.POST,
                    params = "cancel")
    public String processCancel() {
        return REDIRECT_TO_LIST;
    }

    private LocalDateTime parseActiveFrom(String duration) {
        String input = duration.split(DURATION_SEPARATOR)[0];

        return LocalDateTime.parse(input, DateTimeFormat.forPattern(DATE_PATTERN));
    }

    private LocalDateTime parseActiveTo(String duration) {
        String input = duration.split(DURATION_SEPARATOR)[1];

        return LocalDateTime.parse(input, DateTimeFormat.forPattern(DATE_PATTERN));
    }
}
