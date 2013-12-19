package pl.konczak.etest.controller.student.userExam;

import org.apache.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
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
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;

import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.exception.ResourceAccessDeniedException;

import pl.konczak.etest.repository.IUserExamRepository;
import pl.konczak.etest.repository.IUserRepository;

@Controller
@RequestMapping("student/userExam/{id}")
public class UserExamSheetController {

    private static final Logger LOGGER = Logger.getLogger(UserExamSheetController.class);
    private static final String OBJECT = "userExam";
    private static final String VIEW_USEREXAM_NOTACTIVEYET = "student/userExam/notActiveYet";
    private static final String VIEW_USEREXAM_ACTIVENOW = "student/userExam/activeNow";
    private static final String VIEW_USEREXAM_ALREADYFINISHED = "student/userExam/alreadyFinished";
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
        String view;
        LocalDateTime now = LocalDateTime.now();
        ExamEntity exam = userExam.getExam();

        if (isUserExamAlreadyFinished(exam, now)) {
            modelMap.addAttribute(OBJECT, userExamAssembler.toUserExamAlreadyFinished(id));
            view = VIEW_USEREXAM_ALREADYFINISHED;
        } else if (isUserExamNotActiveYet(exam, now)) {
            modelMap.addAttribute(OBJECT, userExamAssembler.toUserExamNotActiveYet(id));
            view = VIEW_USEREXAM_NOTACTIVEYET;
        } else if (isUserExamActiveNow(exam, now)) {
            modelMap.addAttribute(OBJECT, userExamAssembler.toExamSheet(id));
            view = VIEW_USEREXAM_ACTIVENOW;
        } else {
            String msg = String.format("Unrecognized date for UserExam <%s>", userExam.getId());
            LOGGER.fatal(msg);
            throw new RuntimeException(msg);
        }

        return view;
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

    private boolean isUserExamAlreadyFinished(ExamEntity exam, LocalDateTime now) {
        boolean isUserExamAlreadyFinished = false;
        if (exam.getActiveTo().isBefore(now)) {
            isUserExamAlreadyFinished = true;
        }
        return isUserExamAlreadyFinished;
    }

    private boolean isUserExamNotActiveYet(ExamEntity exam, LocalDateTime now) {
        //System.out.println("isUserExamNotActiveYet <" + exam.getActiveFrom() +"> now <" + now +">");
        boolean isUserExamNotActiveYet = false;
        if (exam.getActiveFrom().isAfter(now)) {
            isUserExamNotActiveYet = true;
        }
        return isUserExamNotActiveYet;
    }

    private boolean isUserExamActiveNow(ExamEntity exam, LocalDateTime now) {
        boolean isUserExamActiveNow = false;
        if (exam.getActiveFrom().isBefore(now)
                && exam.getActiveTo().isAfter(now)) {
            isUserExamActiveNow = true;
        }
        return isUserExamActiveNow;
    }
}
