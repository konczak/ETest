package pl.konczak.etest.assembler.student.userExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.dto.student.userExam.UserExamListRow;
import pl.konczak.etest.dto.student.userExam.UserExamSheet;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
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
    public UserExamSheet toExamSheet(Integer id) {
        UserExamSheet userExamSheet = new UserExamSheet();

        UserExamEntity userExamEntity = userExamRepository.getById(id);
        ExamEntity examEntity = userExamEntity.getExam();
        TestTemplateEntity testTemplateEntity = examEntity.getTestTemplate();

        LocalDateTime activeFrom = examEntity.getActiveFrom();
        LocalDateTime activeTo = examEntity.getActiveTo();
        LocalDateTime now = LocalDateTime.now();

        int activeInSeconds = Seconds.secondsBetween(activeFrom, activeTo).getSeconds();
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
}
