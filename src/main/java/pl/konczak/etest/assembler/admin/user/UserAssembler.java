package pl.konczak.etest.assembler.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.dto.user.UserGroups;
import pl.konczak.etest.dto.user.UserPreview;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserExamEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class UserAssembler {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserGroupRepository userGroupRepository;

    @Transactional(readOnly = true)
    public UserPreview toPreview(Integer userId) {
        UserPreview userPreview = new UserPreview();

        UserEntity userEntity = userRepository.getById(userId);

        userPreview.setId(userEntity.getId());
        userPreview.setEmail(userEntity.getEmail());
        userPreview.setLocked(userEntity.isLocked());
        userPreview.setRegisteredAt(userEntity.getRegisteredAt());

        UserPersonalDataEntity userPersonalDataEntity =
                userEntity.getUserPersonalData();

        userPreview.setFirstname(userPersonalDataEntity.getFirstname());
        userPreview.setLastname(userPersonalDataEntity.getLastname());

        for (UserGroupEntity userGroupEntity : userEntity.getGroups()) {
            userPreview.addUserGroup(userGroupEntity.getTitle());
        }

        for (UserExamEntity userExam : userEntity.getExams()) {
            ExamEntity exam = userExam.getExam();
            TestTemplateEntity testTemplate = exam.getTestTemplate();
            userPreview.addUserExam(userExam.getId(),
                    format(testTemplate.getSubject(), exam.getTitleSuffix()),
                    exam.getActiveFrom(),
                    exam.getActiveTo());
        }


        return userPreview;
    }

    @Transactional(readOnly = true)
    public UserGroups toGroups(Integer userId) {
        UserEntity userEntity = userRepository.getById(userId);
        UserPersonalDataEntity userPersonalDataEntity =
                userEntity.getUserPersonalData();

        UserGroups userGroups = new UserGroups(userEntity.getId(),
                userEntity.getEmail(),
                userPersonalDataEntity.getFirstname(),
                userPersonalDataEntity.getLastname());

        for (UserGroupEntity userGroupEntity : userGroupRepository.findAll()) {
            userGroups.addGroup(userGroupEntity.getId(),
                    userGroupEntity.getTitle());
        }

        for (UserGroupEntity userGroupEntity : userEntity.getGroups()) {
            userGroups.markGroupAsAlreadyMember(userGroupEntity.getId());
        }

        return userGroups;
    }

    private String format(String subject, String titleSuffix) {
        String formatted;
        if (titleSuffix == null
                || titleSuffix.isEmpty()) {
            formatted = subject;
        } else {
            formatted = String.format("%s %s", subject, titleSuffix);
        }
        return formatted;
    }
}
