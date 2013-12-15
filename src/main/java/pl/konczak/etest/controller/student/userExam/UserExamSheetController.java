package pl.konczak.etest.controller.student.userExam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.konczak.etest.assembler.student.userExam.UserExamAssembler;

import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.exception.ResourceAccessDeniedException;

import pl.konczak.etest.repository.IUserExamRepository;
import pl.konczak.etest.repository.IUserRepository;

@Controller
@RequestMapping("student/userExam/{id}")
public class UserExamSheetController {

    private static final String OBJECT = "userExam";
    private static final String VIEW_USEREXAMSHEET = "student/userExamSheet";
    @Autowired
    private IUserExamRepository userExamRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    @Qualifier("userExamAssembler")
    private UserExamAssembler userExamAssembler;

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET)
    public String getById(ModelMap modelMap,
            @PathVariable("id") Integer id) {
        UserExamEntity userExam = userExamRepository.getById(id);
        if (!isUserExamIdBelongsToLoggedUser(userExam)) {
            throw new ResourceAccessDeniedException("Sorry searched resource does not exists");
        }

        modelMap.addAttribute(OBJECT, userExamAssembler.toExamSheet(id));

        return VIEW_USEREXAMSHEET;
    }

    private boolean isUserExamIdBelongsToLoggedUser(UserExamEntity userExam) {
        Integer userId = getIdOfAuthenticatedUser();
        return userExam.getExamined().getId().equals(userId);
    }

    private Integer getIdOfAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.getByEmail(email);

        return user.getId();
    }
}
