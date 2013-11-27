package pl.konczak.etest.assembler.user;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.dto.user.UserPreview;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class UserAssembler {

    @Autowired
    private IUserRepository userRepository;

    @Transactional(readOnly = true)
    public UserPreview toPreview(Integer id) {
        UserPreview userPreview = new UserPreview();

        UserEntity userEntity = userRepository.getById(id);

        userPreview.setId(userEntity.getId());
        userPreview.setEmail(userEntity.getEmail());
        userPreview.setLocked(userEntity.isLocked());
        userPreview.setRegisteredAt(userEntity.getRegisteredAt());

        UserPersonalDataEntity userPersonalDataEntity =
                userEntity.getUserPersonalData();

        userPreview.setFirstname(userPersonalDataEntity.getFirstname());
        userPreview.setLastname(userPersonalDataEntity.getLastname());

        Set<UserGroupEntity> groups = userEntity.getGroups();

        for (UserGroupEntity userGroupEntity : groups) {
            userPreview.addUserGroup(userGroupEntity.getTitle());
        }

        return userPreview;
    }
}
