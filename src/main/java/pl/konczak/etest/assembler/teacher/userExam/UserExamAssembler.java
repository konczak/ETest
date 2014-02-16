package pl.konczak.etest.assembler.teacher.userExam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.dto.teacher.userExam.UserExamPreview;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserExamClosedAnswerEntity;
import pl.konczak.etest.entity.UserExamClosedQuestionEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IUserExamRepository;

@Component(value = "teacherUserExamAssembler")
public class UserExamAssembler {

    @Autowired
    private IUserExamRepository userExamRepository;

    @Transactional(readOnly = true)
    public UserExamPreview toPreview(Integer id) {
        UserExamEntity userExamEntity = userExamRepository.getById(id);
        if (isNotCheckedYet(userExamEntity)) {
            throw new IllegalArgumentException("Searched UserExam is not checked yet");
        }
        ExamEntity examEntity = userExamEntity.getExam();
        TestTemplateEntity testTemplateEntity = examEntity.getTestTemplate();
        UserEntity examinedEntity = userExamEntity.getExamined();
        UserPersonalDataEntity examinedPersonalDataEntity = examinedEntity.getUserPersonalData();
        UserExamEntity.UserExamResult userExamResult = userExamEntity.getResults();
        UserExamPreview userExamPreview = new UserExamPreview(userExamEntity.getId(),
                testTemplateEntity.getSubject(),
                examinedPersonalDataEntity.getFirstname(),
                examinedPersonalDataEntity.getLastname(),
                userExamResult.getResultingPoints(),
                userExamResult.getMaximalPoints());

        for (UserExamClosedQuestionEntity userExamClosedQuestionEntity : userExamEntity.getClosedQuestions()) {
            addClosedQuestionData(userExamPreview, userExamClosedQuestionEntity);
        }

        return userExamPreview;
    }

    private boolean isNotCheckedYet(UserExamEntity userExamEntity) {
        return !userExamEntity.isChecked();
    }

    private void addClosedQuestionData(UserExamPreview userExamPreview, UserExamClosedQuestionEntity userExamClosedQuestionEntity) {
        ClosedQuestionEntity closedQuestionEntity = userExamClosedQuestionEntity.getClosedQuestion();
        UserExamPreview.UserExamClosedQuestion userExamClosedQuestionDTO =
                userExamPreview.addUserExamClosedQuestion(userExamClosedQuestionEntity.getId(),
                closedQuestionEntity.getId(),
                closedQuestionEntity.getQuestion(),
                userExamClosedQuestionEntity.getPoints(),
                userExamClosedQuestionEntity.getPointsMax());
        for (UserExamClosedAnswerEntity userExamClosedAnswerEntity : userExamClosedQuestionEntity.getClosedAnswers()) {
            //this is dirty write to DTO :(
            addClosedAnswerData(userExamClosedQuestionDTO, userExamClosedAnswerEntity);
        }
    }

    private void addClosedAnswerData(UserExamPreview.UserExamClosedQuestion userExamClosedQuestionDTO,
            UserExamClosedAnswerEntity userExamClosedAnswerEntity) {
        ClosedAnswerEntity closedAnswerEntity = userExamClosedAnswerEntity.getClosedAnswer();
        userExamClosedQuestionDTO.addUserExamClosedAnswer(userExamClosedAnswerEntity.getId(),
                closedAnswerEntity.getId(),
                closedAnswerEntity.getAnswer(),
                closedAnswerEntity.isCorrect(),
                userExamClosedAnswerEntity.isMarkedByUser());
    }
}
