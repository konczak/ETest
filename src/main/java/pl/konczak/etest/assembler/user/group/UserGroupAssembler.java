package pl.konczak.etest.assembler.user.group;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.user.group.UserGroupEdit;
import pl.konczak.etest.dto.user.group.UserGroupMembers;
import pl.konczak.etest.dto.user.group.UserGroupPreview;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class UserGroupAssembler {

    @Autowired
    private IUserGroupRepository userGroupRepository;
    @Autowired
    private IUserRepository userRepository;

    @Transactional(readOnly = true)
    public UserGroupPreview toPreview(Integer id) {
        UserGroupEntity userGroupEntity = userGroupRepository.getById(id);

        UserGroupPreview userGroupPreview = new UserGroupPreview(
                userGroupEntity.getId(),
                userGroupEntity.getTitle());

        for (UserEntity userEntity : userGroupEntity.getMembers()) {
            UserPersonalDataEntity userPersonalDataEntity =
                    userEntity.getUserPersonalData();

            userGroupPreview.addMember(userEntity.getId(),
                    userPersonalDataEntity.getFirstname(),
                    userPersonalDataEntity.getLastname());
        }

        return userGroupPreview;
    }

    public UserGroupEdit toEdit(UserGroupEntity userGroupEntity) {
        return new UserGroupEdit(userGroupEntity.getId(),
                userGroupEntity.getTitle());
    }

    @Transactional(readOnly = true)
    public UserGroupMembers toMembers(Integer id) {
        UserGroupEntity userGroupEntity = userGroupRepository.getById(id);

        UserGroupMembers userGroupMembers = new UserGroupMembers(
                userGroupEntity.getId(),
                userGroupEntity.getTitle());

        for (UserEntity userEntity : userRepository.findAll()) {
            UserPersonalDataEntity userPersonalDataEntity =
                    userEntity.getUserPersonalData();

            userGroupMembers.addMember(userEntity.getId(),
                    userPersonalDataEntity.getFirstname(),
                    userPersonalDataEntity.getLastname());
        }

        for (UserEntity userEntity : userGroupEntity.getMembers()) {
            userGroupMembers.markMemberAsAlreadyIn(userEntity.getId());
        }

        return userGroupMembers;
    }
}
