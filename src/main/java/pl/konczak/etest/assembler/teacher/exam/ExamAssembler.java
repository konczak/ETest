package pl.konczak.etest.assembler.teacher.exam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.teacher.exam.ExamListRow;
import pl.konczak.etest.dto.teacher.exam.ExamPreview;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IExamRepository;

@Component
public class ExamAssembler {

    @Autowired
    private IExamRepository examRepository;

    @Transactional(readOnly = true)
    public ExamPreview toPreview(Integer id) {
        ExamEntity examEntity = examRepository.getById(id);
        TestTemplateEntity testTemplateEntity = examEntity.getTestTemplate();
        UserGroupEntity userGroupEntity = examEntity.getUserGroup();
        UserEntity examiner = examEntity.getExaminer();
        UserPersonalDataEntity examinerPersonalDataEntity = examiner.getUserPersonalData();

        ExamPreview examPreview = new ExamPreview();

        examPreview.setId(examEntity.getId());
        examPreview.setSuffix(examEntity.getTitleSuffix());

        examPreview.setTestTemplateId(testTemplateEntity.getId());
        examPreview.setTestTampleteSubject(testTemplateEntity.getSubject());

        examPreview.setUserGroupId(userGroupEntity.getId());
        examPreview.setUserGroupTitle(userGroupEntity.getTitle());

        examPreview.setExaminerId(examiner.getId());
        examPreview.setExaminerFirstname(examinerPersonalDataEntity.getFirstname());
        examPreview.setExaminerLastname(examinerPersonalDataEntity.getLastname());

        examPreview.setActiveFrom(examEntity.getActiveFrom());
        examPreview.setActiveTo(examEntity.getActiveTo());

        for (UserExamEntity userExamEntity : examEntity.getGeneratedExams()) {
            UserEntity examined = userExamEntity.getExamined();
            UserPersonalDataEntity examinedPersonalDataEntity = examined.getUserPersonalData();
            if (examEntity.isChecked()) {
                UserExamEntity.UserExamResult userExamResult = userExamEntity.getResults();
                examPreview.addExaminedUser(examined.getId(),
                        examinedPersonalDataEntity.getFirstname(), examinedPersonalDataEntity.getLastname(),
                        userExamResult.getResultingPoints(), userExamResult.getMaximalPoints());
            } else {
                examPreview.addExaminedUser(examined.getId(),
                        examinedPersonalDataEntity.getFirstname(), examinedPersonalDataEntity.getLastname(),
                        0, 0);
            }

        }

        if (examEntity.isChecked()) {
            examPreview.markAsChecked();
        }

        return examPreview;
    }

    @Transactional(readOnly = true)
    public List<ExamListRow> toList() {
        List<ExamListRow> examList = new ArrayList<ExamListRow>();

        List<ExamEntity> exams = examRepository.findAll();

        for (ExamEntity exam : exams) {
            ExamListRow examListRow = new ExamListRow();

            examListRow.setId(exam.getId());
            examListRow.setSuffix(exam.getTitleSuffix());

            TestTemplateEntity testTemplate = exam.getTestTemplate();
            UserGroupEntity userGroup = exam.getUserGroup();

            examListRow.setTestTemplateId(testTemplate.getId());
            examListRow.setTestTemplateSubject(testTemplate.getSubject());

            examListRow.setUserGroupId(userGroup.getId());
            examListRow.setUserGroupTitle(userGroup.getTitle());

            UserEntity examiner = exam.getExaminer();
            UserPersonalDataEntity examinerPersonalData = examiner.getUserPersonalData();

            examListRow.setExaminerId(examiner.getId());
            examListRow.setExaminerFirstname(examinerPersonalData.getFirstname());
            examListRow.setExaminerLastname(examinerPersonalData.getLastname());

            examListRow.setActiveFrom(exam.getActiveFrom());
            examListRow.setActiveTo(exam.getActiveTo());

            examList.add(examListRow);
        }

        return examList;
    }
}
