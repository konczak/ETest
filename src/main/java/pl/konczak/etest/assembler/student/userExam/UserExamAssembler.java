package pl.konczak.etest.assembler.student.userExam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.dto.student.userExam.UserExamAlreadyFinished;
import pl.konczak.etest.dto.student.userExam.UserExamClosedQuestion;
import pl.konczak.etest.dto.student.userExam.UserExamListRow;
import pl.konczak.etest.dto.student.userExam.UserExamNotActiveYet;
import pl.konczak.etest.dto.student.userExam.UserExamQuestionHeader;
import pl.konczak.etest.dto.student.userExam.UserExamSheet;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserExamClosedAnswerEntity;
import pl.konczak.etest.entity.UserExamClosedQuestionEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IUserExamRepository;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class UserExamAssembler {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserExamRepository userExamRepository;

    @Transactional(readOnly = true)
    public UserExamAlreadyFinished toUserExamAlreadyFinished(Integer id) {
        UserExamEntity userExamEntity = userExamRepository.getById(id);
        ExamEntity examEntity = userExamEntity.getExam();
        TestTemplateEntity testTemplateEntity = examEntity.getTestTemplate();

        return new UserExamAlreadyFinished(testTemplateEntity.getSubject(),
                examEntity.getTitleSuffix(),
                examEntity.getActiveTo());
    }

    @Transactional(readOnly = true)
    public UserExamNotActiveYet toUserExamNotActiveYet(Integer id) {
        UserExamEntity userExamEntity = userExamRepository.getById(id);
        ExamEntity examEntity = userExamEntity.getExam();
        TestTemplateEntity testTemplateEntity = examEntity.getTestTemplate();
        LocalDateTime now = LocalDateTime.now();

        Integer activeInSeconds = null;
        if (isUserExamStartToday(examEntity, now)) {
            activeInSeconds = Seconds.secondsBetween(now, examEntity.getActiveFrom())
                    .getSeconds();
        }

        return new UserExamNotActiveYet(testTemplateEntity.getSubject(),
                examEntity.getTitleSuffix(),
                examEntity.getActiveFrom(),
                activeInSeconds);
    }

    private boolean isUserExamStartToday(ExamEntity exam, LocalDateTime now) {
        boolean isUserExamStartToday = false;
        LocalDateTime activeFrom = exam.getActiveFrom();
        Days difference = Days.daysBetween(activeFrom, now);
        if (difference.getDays() == 0) {
            isUserExamStartToday = true;
        }
        return isUserExamStartToday;
    }

    @Transactional(readOnly = true)
    public UserExamSheet toExamSheet(Integer id) {
        UserExamSheet userExamSheet = new UserExamSheet();

        UserExamEntity userExamEntity = userExamRepository.getById(id);
        ExamEntity examEntity = userExamEntity.getExam();
        TestTemplateEntity testTemplateEntity = examEntity.getTestTemplate();

        LocalDateTime activeFrom = examEntity.getActiveFrom();
        LocalDateTime activeTo = examEntity.getActiveTo();
        LocalDateTime now = LocalDateTime.now();

        int activeInSeconds = Seconds.secondsBetween(now, activeFrom).getSeconds();
        int inactiveInSeconds = Seconds.secondsBetween(activeTo, now).getSeconds();

        userExamSheet.setId(userExamEntity.getId());
        userExamSheet.setTestTemplateSubject(testTemplateEntity.getSubject());
        userExamSheet.setTestTemplateSuffix(examEntity.getTitleSuffix());
        userExamSheet.setActiveFrom(activeFrom);
        userExamSheet.setActiveTo(activeTo);
        userExamSheet.setActiveInSeconds(activeInSeconds);
        userExamSheet.setInactiveInSeconds(inactiveInSeconds);

        for (UserExamClosedQuestionEntity closedQuestion : userExamEntity.getClosedQuestions()) {
            userExamSheet.addClosedQuestion(closedQuestion.getId());
        }

        return userExamSheet;
    }

    @Transactional(readOnly = true)
    public List<UserExamListRow> toList(Integer userId) {
        UserEntity userEntity = userRepository.getById(userId);
        List<UserExamListRow> userExamListRows = new ArrayList<UserExamListRow>();

        for (UserExamEntity userExam : userEntity.getExams()) {
            UserExamListRow userExamListRow = new UserExamListRow();
            ExamEntity examEntity = userExam.getExam();
            TestTemplateEntity testTemplateEntity = examEntity.getTestTemplate();
            UserEntity examiner = examEntity.getExaminer();
            UserPersonalDataEntity examinerPersonalData = examiner.getUserPersonalData();

            userExamListRow.setId(userExam.getId());
            userExamListRow.setTestTemplateSubject(testTemplateEntity.getSubject());
            userExamListRow.setTitleSuffix(examEntity.getTitleSuffix());
            userExamListRow.setExaminerId(examiner.getId());
            userExamListRow.setExaminerFirstname(examinerPersonalData.getFirstname());
            userExamListRow.setExaminerLastname(examinerPersonalData.getLastname());

            userExamListRows.add(userExamListRow);
        }

        return userExamListRows;
    }

    @Transactional(readOnly = true)
    public List<UserExamQuestionHeader> toUserExamQuestionHeaders(Integer id) {
        UserExamEntity userExamEntity = userExamRepository.getById(id);
        List<UserExamQuestionHeader> userExamQuestionHeaders = new ArrayList<UserExamQuestionHeader>();

        for (UserExamClosedQuestionEntity userExamClosedQuestionEntity : userExamEntity.getClosedQuestions()) {
            if (!userExamClosedQuestionEntity.isSubbmited()) {
                userExamQuestionHeaders.add(new UserExamQuestionHeader(userExamClosedQuestionEntity.getId(),
                        userExamClosedQuestionEntity.getOrderNumber()));
            }
        }

        Collections.sort(userExamQuestionHeaders);

        return userExamQuestionHeaders;
    }

    @Transactional(readOnly = true)
    public UserExamClosedQuestion toUserExamClosedQuestion(Integer userExamId, Integer userExamClosedQuestionId) {
        UserExamEntity userExamEntity = userExamRepository.getById(userExamId);
        UserExamClosedQuestionEntity userExamClosedQuestionEntity =
                userExamEntity.getUserExamClosedQuestionEntity(userExamClosedQuestionId);
        ClosedQuestionEntity closedQuestionEntity = userExamClosedQuestionEntity.getClosedQuestion();
        ImageEntity closedQuestionImage = closedQuestionEntity.getImage();

        UserExamClosedQuestion userExamClosedQuestion = new UserExamClosedQuestion(userExamClosedQuestionEntity.getId(),
                closedQuestionEntity.getQuestion(),
                closedQuestionImage == null ? null : closedQuestionImage.getId());

        for (UserExamClosedAnswerEntity userExamClosedAnswerEntity : userExamClosedQuestionEntity.getClosedAnswers()) {
            ClosedAnswerEntity closedAnswerEntity = userExamClosedAnswerEntity.getClosedAnswer();
            ImageEntity closedAnswerImage = closedAnswerEntity.getImage();
            userExamClosedQuestion.addClosedAnswer(userExamClosedAnswerEntity.getId(),
                    closedAnswerEntity.getAnswer(),
                    closedAnswerImage == null ? null : closedAnswerImage.getId());
        }

        return userExamClosedQuestion;
    }
}
