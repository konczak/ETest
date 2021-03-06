package pl.konczak.etest.controller.student.userExam;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.konczak.etest.assembler.student.userExam.UserExamAssembler;
import pl.konczak.etest.bo.IUserExamBO;
import pl.konczak.etest.dto.student.userExam.UserExamClosedQuestion;
import pl.konczak.etest.dto.student.userExam.UserExamQuestionHeader;
import pl.konczak.etest.entity.ExamEntity;

import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.exception.ResourceAccessDeniedException;
import pl.konczak.etest.exception.ValidationException;

import pl.konczak.etest.repository.IUserExamRepository;
import pl.konczak.etest.repository.IUserRepository;

import pl.konczak.etest.vo.UserExamClosedQuestionWithAnswersVO;

@Controller
@RequestMapping("student/userExam/{id}")
public class UserExamSheetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserExamSheetController.class);
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
    @Autowired
    @Qualifier("userExamClosedQuestionValidator")
    private Validator userExamClosedQuestionValidator;
    @Autowired
    private IUserExamBO userExamBO;

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET)
    public String getById(ModelMap modelMap,
            @PathVariable("id") Integer id) throws IOException {
        UserExamEntity userExam = userExamRepository.getById(id);
        if (!isUserExamIdBelongsToLoggedUser(userExam)) {
            throw new ResourceAccessDeniedException("Sorry searched resource does not exists");
        }
        String view;
        LocalDateTime now = LocalDateTime.now();
        ExamEntity exam = userExam.getExam();

        modelMap.addAttribute("testTemplateSubject", exam.getTestTemplate().getSubject());
        modelMap.addAttribute("testTemplateSuffix", exam.getTitleSuffix());

        if (isUserExamAlreadyFinished(exam, now)) {
            modelMap.addAttribute(OBJECT, userExamAssembler.toUserExamAlreadyFinished(id));
            view = VIEW_USEREXAM_ALREADYFINISHED;
        } else if (isUserExamNotActiveYet(exam, now)) {
            modelMap.addAttribute(OBJECT, userExamAssembler.toUserExamNotActiveYet(id));
            view = VIEW_USEREXAM_NOTACTIVEYET;
        } else if (isUserExamActiveNow(exam, now)) {
            List<UserExamQuestionHeader> userExamQuestionHeaders =
                    userExamAssembler.toUserExamQuestionHeaders(id);
            Writer strWriter = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(strWriter, userExamQuestionHeaders);
            modelMap.addAttribute("userExamId", userExam.getId());
            modelMap.addAttribute("questionHeaders", strWriter.toString());

            Integer inactiveInSeconds = Seconds.secondsBetween(now, exam.getActiveTo()).getSeconds();
            modelMap.addAttribute("inactiveInSeconds", inactiveInSeconds);
            view = VIEW_USEREXAM_ACTIVENOW;
        } else {
            String msg = String.format("Unrecognized date for UserExam <%s>", userExam.getId());
            LOGGER.error(msg);
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

    @Transactional(readOnly = true)
    @RequestMapping(value = "closedQuestion",
                    produces = "application/json",
                    method = RequestMethod.GET)
    @ResponseBody
    public UserExamClosedQuestion get(@PathVariable("id") Integer id,
            @RequestParam("closedQuestionId") Integer closedQuestionId) throws InterruptedException {
        UserExamEntity userExam = userExamRepository.getById(id);
        if (!isUserExamIdBelongsToLoggedUser(userExam)) {
            throw new ResourceAccessDeniedException("Sorry searched resource does not exists");
        }
        Thread.sleep(400);

        return userExamAssembler.toUserExamClosedQuestion(id, closedQuestionId);
    }

    @RequestMapping(value = "closedQuestion",
                    consumes = "application/json",
                    method = RequestMethod.POST)
    @ResponseBody
    public void submit(@RequestBody UserExamClosedQuestion userExamClosedQuestion,
            BindingResult result) {
        userExamClosedQuestionValidator.validate(userExamClosedQuestion, result);

        if (result.hasErrors()) {
            String msg = "UserExamClosedQuestion contains errors :(";
            throw new ValidationException(msg);
        }
        UserExamClosedQuestionWithAnswersVO vo = userExamClosedQuestion.toVO();

        userExamBO.solve(vo);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    public void handleCustomException(ValidationException ex) {
        LOGGER.error(ex.getMessage());
    }
}
