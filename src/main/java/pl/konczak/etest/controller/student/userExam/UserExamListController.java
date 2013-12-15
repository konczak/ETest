package pl.konczak.etest.controller.student.userExam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.konczak.etest.assembler.student.userExam.UserExamAssembler;

import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.IUserRepository;

@Controller
@RequestMapping("student/userExam/")
public class UserExamListController {

    private static final String VIEW_LIST = "student/list";
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    @Qualifier("userExamAssembler")
    private UserExamAssembler userExamAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public String list(ModelMap model) {
        Integer userId = getIdOfAuthenticatedUser();
        model.addAttribute("userExams", userExamAssembler.toList(userId));

        return VIEW_LIST;
    }

    private Integer getIdOfAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.getByEmail(email);

        return user.getId();
    }
}
