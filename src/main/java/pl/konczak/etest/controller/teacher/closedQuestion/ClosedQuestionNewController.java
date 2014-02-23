package pl.konczak.etest.controller.teacher.closedQuestion;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import pl.konczak.etest.bo.IClosedQuestionBO;
import pl.konczak.etest.dto.question.closedQuestion.ClosedQuestionNew;
import pl.konczak.etest.entity.CategoryEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.ICategoryRepository;
import pl.konczak.etest.repository.IUserRepository;
import pl.konczak.etest.validator.teacher.closedQuestion.ClosedQuestionNewValidator;

@Controller
@RequestMapping("teacher/closedQuestion/new")
public class ClosedQuestionNewController {

    private static final String OBJECT = "closedQuestion";
    private static final String VIEW_NEW = "teacher/closedQuestion/new";
    private static final String REDIRECT_TO_LIST = "redirect:/teacher/closedQuestion/";
    private static final String REDIRECT_TO_PREVIEW = "redirect:/teacher/closedQuestion/%s";
    @Autowired
    @Qualifier("closedQuestionNewValidator")
    private ClosedQuestionNewValidator closedQuestionNewValidator;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IClosedQuestionBO closedQuestionBO;
    @Autowired
    private ICategoryRepository categoryRepository;

    @InitBinder(OBJECT)
    protected void initBind(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(closedQuestionNewValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String initForm(ModelMap model) {
        ClosedQuestionNew closedQuestionNew = new ClosedQuestionNew();

        //command object
        model.addAttribute(OBJECT, closedQuestionNew);

        //return form view
        return VIEW_NEW;
    }

    @Transactional(readOnly = true)
    @ModelAttribute("categoryList")
    public Map<Integer, String> populateCategoryList() {
        List<CategoryEntity> categoryEntites = categoryRepository.findAll();
        Map<Integer, String> categories = new LinkedHashMap<Integer, String>();

        for (CategoryEntity categoryEntity : categoryEntites) {
            categories.put(categoryEntity.getId(), categoryEntity.getName());
        }

        return categories;
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST,
                    params = "add")
    public String processSubmit(@Valid @ModelAttribute(OBJECT) ClosedQuestionNew closedQuestionNew,
            BindingResult result, SessionStatus status)
            throws IOException {
        String action = REDIRECT_TO_PREVIEW;

        if (result.hasErrors()) {
            //if validator failed
            action = VIEW_NEW;
        } else {
            ClosedQuestionEntity closedQuestionEntity =
                    closedQuestionBO.add(closedQuestionNew.getQuestion(), getIdOfAuthenticatedUser(), closedQuestionNew.getCategoryId());
            MultipartFile multipartFile = closedQuestionNew.getMultipartFile();
            if (!multipartFile.isEmpty()) {
                closedQuestionBO.addPicture(closedQuestionEntity.getId(),
                        multipartFile.getBytes());
            }
            status.setComplete();
            //form success
            action = String.format(REDIRECT_TO_PREVIEW, closedQuestionEntity.getId());
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
}
