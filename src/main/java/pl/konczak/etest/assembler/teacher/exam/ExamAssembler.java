package pl.konczak.etest.assembler.teacher.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.teacher.exam.ExamPreview;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
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

        for (UserEntity member : userGroupEntity.getMembers()) {
            UserPersonalDataEntity memberPersonalDataEntity = member.getUserPersonalData();

            examPreview.addMember(member.getId(),
                    memberPersonalDataEntity.getFirstname(),
                    memberPersonalDataEntity.getLastname());
        }

        return examPreview;
    }
}
